package net.aplat.stocks.service;

import net.aplat.stocks.bo.TushareDataBO;
import net.aplat.stocks.dto.TushareRequestDTO;
import net.aplat.stocks.dto.TushareResponseDTO;
import net.aplat.stocks.dto.daily.DailyRequestParamsDTO;
import net.aplat.stocks.entity.DailyEntity;
import net.aplat.stocks.mapper.MapToObjectMapper;
import net.aplat.stocks.repo.DailyRepository;
import net.aplat.stocks.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

@Service
public class DailyService {

    private static final Logger logger = LoggerFactory.getLogger(DailyService.class);

    @Autowired
    private TushareService tushareService;

    @Autowired
    private DailyRepository dailyRepository;


    public void fetchDaily(LocalDate startDate, LocalDate endDate, boolean force) {
        // tushare 一次最多可以获取 6000 条数据, 所以需要分批获取
        long numOfDays = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        LocalDate now = LocalDate.now();
        for (int i = 0; i < numOfDays; i++) {
            LocalDate date = startDate.plusDays(i);

            if (date.isAfter(now)) {
                logger.info("{} 是未来日期, 跳过请求", date);
                continue;
            }

            if (DateUtil.isWeekend(date)) {
                logger.info("{} 是周末, 跳过请求", date);
                continue;
            }

            // 检查数据库中是否有当天的数据
            if (force) {
                logger.info("强制更新 {} 的数据.", date);
                dailyRepository.deleteAllByTradeDate(date);
            } else {
                if (dailyRepository.existsByTradeDate(date)) {
                    logger.info("已存在 {} 的数据, 跳过请求.", date);
                    continue;
                }
            }

            DailyRequestParamsDTO params = new DailyRequestParamsDTO();
            params.setTradeDate(date);
            List<DailyEntity> dailyEntityList = fetchDaily(params);
            dailyRepository.saveAll(dailyEntityList);
        }
    }

    public List<DailyEntity> fetchDaily(DailyRequestParamsDTO searchBO) {
        logger.info("获取日行情数据: {}", searchBO);

        TushareRequestDTO requestDTO = new TushareRequestDTO();
        requestDTO.setApiName("daily");
        requestDTO.setParams(searchBO);

        TushareResponseDTO response = tushareService.fetchData(requestDTO);

        TushareDataBO tushareDataBO = new TushareDataBO(response.getData());

        return tushareDataBO.getItems().stream().map(this::toDailyEntity).toList();
    }

    public DailyEntity toDailyEntity(Map<String, Object> map) {
        return MapToObjectMapper.INSTANCE.toDailyEntity(map);
    }
}

package net.aplat.stocks.service;

import net.aplat.stocks.bo.TushareDataBO;
import net.aplat.stocks.dto.TushareRequestDTO;
import net.aplat.stocks.dto.TushareResponseDTO;
import net.aplat.stocks.dto.daily.DailyRequestParamsDTO;
import net.aplat.stocks.entity.DailyEntity;
import net.aplat.stocks.mapper.MapToObjectMapper;
import net.aplat.stocks.repo.DailyRepository;
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


    public void fetchDaily(LocalDate startDate, LocalDate endDate) {
        // tushare 一次最多可以获取 6000 条数据, 所以需要分批获取
        long numOfDays = ChronoUnit.DAYS.between(startDate, endDate);
        for (int i = 0; i < numOfDays; i++) {
            DailyRequestParamsDTO params = new DailyRequestParamsDTO();
            params.setTradeDate(startDate.plusDays(i));
            List<DailyEntity> dailyEntityList = fetchDaily(params);
            dailyRepository.saveAll(dailyEntityList);
        }
    }

    public List<DailyEntity> fetchDaily(DailyRequestParamsDTO searchBO) {
        logger.info("fetching data by: {}", searchBO);

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

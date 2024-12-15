package net.aplat.stocks.dto.daily;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.aplat.stocks.dto.TushareRequestParamsDTO;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DailyRequestParamsDTO implements TushareRequestParamsDTO {
    @JsonProperty("ts_code")
    private String tsCode; // 股票代码（支持多个股票同时提取，逗号分隔）
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    @JsonProperty("trade_date")
    private LocalDate tradeDate; // 交易日期（YYYYMMDD）
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    @JsonProperty("start_date")
    private LocalDate startDate; // 开始日期(YYYYMMDD)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    @JsonProperty("end_date")
    private LocalDate endDate; // 结束日期(YYYYMMDD)
}

package net.aplat.stocks.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "daily")
public class DailyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tsCode; // 股票代码
    private LocalDate tradeDate; // 交易日期
    private Float open; // 开盘价
    private Float high; // 最高价
    private Float low; // 最低价
    private Float close; // 收盘价
    private Float preClose; // 昨收价【除权价，前复权】
    @Column(name = "`change`")
    private Float change; // 涨跌额
    private Float pctChg; // 涨跌幅 【基于除权后的昨收计算的涨跌幅：（今收-除权昨收）/除权昨收 】
    private Float vol; // 成交量 （手）
    private Float amount; // 成交额 （千元）

}

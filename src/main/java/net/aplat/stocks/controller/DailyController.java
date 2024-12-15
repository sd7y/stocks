package net.aplat.stocks.controller;

import net.aplat.stocks.service.DailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;

@RestController()
@RequestMapping("/daily")
public class DailyController {

    @Autowired
    private DailyService dailyService;

    @GetMapping("/fetch/byDate")
    public String fetchByDate(@RequestParam @DateTimeFormat(pattern = "yyyyMMdd") LocalDate startDate,
                              @RequestParam @DateTimeFormat(pattern = "yyyyMMdd") LocalDate endDate) {
        dailyService.fetchDaily(startDate, endDate);
        return "ok";
    }

    @GetMapping("/fetch/byTsCode")
    public String fetchByTsCode(@RequestParam String tsCode) {
        return "Hello, Spring Boot!";
    }
}

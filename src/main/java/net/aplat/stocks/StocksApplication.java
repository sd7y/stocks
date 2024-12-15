package net.aplat.stocks;

import net.aplat.stocks.service.DailyService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class StocksApplication implements CommandLineRunner {

    private final DailyService dailyService;

    public StocksApplication(DailyService dailyService) {
        this.dailyService = dailyService;
    }

    public static void main(String[] args) {
        SpringApplication.run(StocksApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello, Spring Boot!");
        Map<String, Object> map = new HashMap<>();
        map.put("tsCode", "000001.SZ");
        map.put("tradeDate", "20210101");

        System.out.println(dailyService.toDailyEntity(map));;
    }

}

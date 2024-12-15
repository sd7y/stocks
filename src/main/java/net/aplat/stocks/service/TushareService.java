package net.aplat.stocks.service;

import net.aplat.stocks.dto.TushareRequestDTO;
import net.aplat.stocks.dto.TushareResponseDTO;
import net.aplat.stocks.dto.daily.DailyRequestParamsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class TushareService {


    private final static String TUSHARE_PRO_API = "http://api.tushare.pro";

    @Value("${tushare.token}")
    private String token;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public TushareResponseDTO fetchData(TushareRequestDTO requestDTO) {
        requestDTO.setToken(token);
        return webClientBuilder.build()
                .post()
                .uri(TUSHARE_PRO_API)
                .header("Content-Type", "application/json")
                .bodyValue(requestDTO)
                .retrieve()
                .bodyToMono(TushareResponseDTO.class)
                .block();
    }
}

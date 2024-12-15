package net.aplat.stocks.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TushareRequestDTO {
    @JsonProperty("api_name")
    private String apiName;
    private String token;
    private TushareRequestParamsDTO params;
}

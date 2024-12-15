package net.aplat.stocks.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TushareResponseDTO {
    private String requestId;
    private Integer code;
    private TushareResponseDataDTO data;
    private String msg;
}

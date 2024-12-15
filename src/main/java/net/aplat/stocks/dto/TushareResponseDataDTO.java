package net.aplat.stocks.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TushareResponseDataDTO {
    private List<String> fields;
    private List<List<Object>> items;
}

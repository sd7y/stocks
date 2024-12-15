package net.aplat.stocks.bo;

import lombok.Data;
import net.aplat.stocks.dto.TushareResponseDTO;
import net.aplat.stocks.dto.TushareResponseDataDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class TushareDataBO {
    private List<String> fields;
    private List<Map<String, Object>> items;

    public TushareDataBO() {
    }

    public TushareDataBO(TushareResponseDataDTO data) {
        this.fields = data.getFields();
        this.items = new ArrayList<>();
        for (List<Object> item : data.getItems()) {
            Map<String, Object> map = new HashMap<>();
            for (int i = 0; i < item.size(); i++) {
                Object value = item.get(i);
                // 将下划线命名转换为驼峰命名, 方便交给 MapStruct 处理
                map.put(convertToCamelCase(fields.get(i)), value);
            }
            this.items.add(map);
        }
    }

    private String convertToCamelCase(String propertyName) {
        StringBuilder result = new StringBuilder();
        boolean nextIsUpper = false;
        for (char c : propertyName.toCharArray()) {
            if (c == '_') {
                nextIsUpper = true;
            } else {
                if (nextIsUpper) {
                    result.append(Character.toUpperCase(c));
                    nextIsUpper = false;
                } else {
                    result.append(c);
                }
            }
        }
        return result.toString();
    }
}

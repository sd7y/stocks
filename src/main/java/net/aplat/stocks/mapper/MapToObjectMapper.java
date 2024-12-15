package net.aplat.stocks.mapper;

import net.aplat.stocks.entity.DailyEntity;
import net.aplat.stocks.util.SnakeToCamelNamingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

// 这里的 SnakeToCamelNamingStrategy 不能作用于 Map 对象, 只能提前处理好 map 的 key 了.
// 这里留下它只是作为一个参考使用
@Mapper(uses = {SnakeToCamelNamingStrategy.class})
public interface MapToObjectMapper {

    MapToObjectMapper INSTANCE = Mappers.getMapper(MapToObjectMapper.class);

    DailyEntity toDailyEntity(Map<String, Object> map);

    default Long mapToLong(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Integer) {
            return ((Integer) value).longValue();
        } else if (value instanceof Long) {
            return (Long) value;
        }
        throw new RuntimeException("Can't convert " + value + " to Long");
    }

    default Float mapToFloat(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Integer) {
            return ((Integer) value).floatValue();
        } else if (value instanceof Double) {
            return ((Double) value).floatValue();
        } else if (value instanceof Float) {
            return (Float) value;
        }
        throw new RuntimeException("Can't convert " + value + " to Float");
    }

    default Date mapToDate(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Date) {
            return (Date) value;
        } else if (value instanceof String) {
            try {
                return new SimpleDateFormat("yyyyMMdd").parse((String) value);
            } catch (ParseException e) {
                throw new RuntimeException("Can't convert " + value + " to Date");
            }
        }
        throw new RuntimeException("Can't convert " + value + " to Date");
    }

    default LocalDate mapToLocalDate(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof LocalDate) {
            return (LocalDate) value;
        } else if (value instanceof String) {
            return LocalDate.parse((String) value, DateTimeFormatter.ofPattern("yyyyMMdd"));
        }
        throw new RuntimeException("Can't convert " + value + " to LocalDate");
    }

    default String mapToString(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof String) {
            return (String) value;
        }
        throw new RuntimeException("Can't convert " + value + " to String");
    }

}

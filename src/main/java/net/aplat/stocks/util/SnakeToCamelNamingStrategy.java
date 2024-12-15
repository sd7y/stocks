package net.aplat.stocks.util;

import org.mapstruct.ap.spi.DefaultAccessorNamingStrategy;

import javax.lang.model.element.ExecutableElement;

public class SnakeToCamelNamingStrategy extends DefaultAccessorNamingStrategy {

    @Override
    public String getPropertyName(ExecutableElement getterOrSetterMethod) {
        String propertyName = super.getPropertyName(getterOrSetterMethod);
        return convertToCamelCase(propertyName);
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

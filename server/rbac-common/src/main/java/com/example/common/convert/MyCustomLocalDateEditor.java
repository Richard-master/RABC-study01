package com.example.common.convert;

import com.example.common.util.DateUtil;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;

/**
 * 自定义日期装换
 *
 * @author Jack
 * @date 2022/05/10
 */
public class MyCustomLocalDateEditor extends PropertyEditorSupport {


    @Override
    public String getAsText() {
        LocalDate value = (LocalDate) getValue();
        return DateUtil.getLocalDateStr(value);
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (!StringUtils.hasText(text)) {
            // Treat empty String as null value.
            setValue(null);
        } else {
            setValue(DateUtil.parseLocalDate(text));
        }
    }


}



package com.vandu.helper;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String source) {
        if (source == null || source.equals("null") ||source.equals("")) {
            return null;
        }
        // Chuyển đổi chuỗi thành Date bằng phương thức phù hợp
        // Ví dụ: return DateUtils.parse(source);
        // Trong trường hợp không cần chuyển đổi, có thể trả về null
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
			return format.parse(source);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
}

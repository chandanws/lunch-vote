package io.philipg.lunchvote.util;

import org.springframework.util.StringUtils;

import java.time.LocalTime;

public class DateTimeUtil {

    private DateTimeUtil() {}

    public static LocalTime parseLocalTime(String str) {
        return StringUtils.isEmpty(str) ? null : LocalTime.parse(str);
    }
}

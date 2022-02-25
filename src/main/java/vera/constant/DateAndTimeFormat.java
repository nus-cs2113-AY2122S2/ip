package vera.constant;

import java.time.format.DateTimeFormatter;

public class DateAndTimeFormat {

    public static final DateTimeFormatter withTimeFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
    public static final DateTimeFormatter noTimeFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd");
}

package vera.constant;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class DateAndTimeFormat {

    public static final DateTimeFormatter optionalTimeFormat = new DateTimeFormatterBuilder()
            .appendPattern("yyyy/MM/dd")
            .optionalStart()
            .appendPattern(" HHmm")
            .optionalEnd()
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .toFormatter();
    public static final DateTimeFormatter withTimeFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
    public static final DateTimeFormatter noTimeFormat = DateTimeFormatter.ofPattern("uuuu/MM/dd");
}

package times;

import java.time.LocalDateTime;
import java.time.Month;

import exceptions.DukeException;
import exceptions.WrongTimeFormatDukeException;



public class DukeTime {
    private static final int DEFAULT_DAY = 1;
    private static final int DEFAULT_HOUR = 23;
    private static final int DEFAULT_MIN = 59;
    private static final int CONTAIN_HM = 1;
    private static final int NOT_CONTAIN_HM = 2;
    private LocalDateTime time;


    /**
     * Initializes a todo task with task description and task type.
     *
     * @param timeString time String
     * @throws DukeException Duke Exception
     */
    public DukeTime(String timeString) throws DukeException {
        String[] sequence = timeString.split(" ");
        try {
            int[] dateIntElements;
            switch (sequence.length) {
            case CONTAIN_HM:
                dateIntElements = parseDate(sequence[0]);
                time = LocalDateTime.of(dateIntElements[2], Month.of(dateIntElements[1]),
                        dateIntElements[0], DEFAULT_HOUR, DEFAULT_MIN);
            case NOT_CONTAIN_HM:
                dateIntElements = parseDate(sequence[0]);
                int[] clockIntElements = parseClock(sequence[1]);
                time = LocalDateTime.of(dateIntElements[2], Month.of(dateIntElements[1]),
                        dateIntElements[0], clockIntElements[0], clockIntElements[1]);
            default:
                throw new WrongTimeFormatDukeException();
            }

        } catch (Exception e) {
            throw new WrongTimeFormatDukeException();
        }

    }

    private int[] parseDate(String dateString) throws WrongTimeFormatDukeException {
        try {
            String[] dateStringElements = new String[3];
            if (dateString.contains("/")) {
                dateStringElements = dateString.split("/");
                int[] dateIntElements = new int[3];
                dateIntElements[0] = Integer.parseInt(dateStringElements[0]);
                dateIntElements[1] = Integer.parseInt(dateStringElements[1]);
                dateIntElements[2] = Integer.parseInt(dateStringElements[2]);
                return dateIntElements;
            }
            if (dateString.contains("-")) {
                dateStringElements = dateString.split("-");
                int[] dateIntElements = new int[3];
                dateIntElements[0] = Integer.parseInt(dateStringElements[2]);
                dateIntElements[1] = Integer.parseInt(dateStringElements[1]);
                dateIntElements[2] = Integer.parseInt(dateStringElements[0]);
                return dateIntElements;
            }
            throw new WrongTimeFormatDukeException();

        } catch (Exception e) {
            throw new WrongTimeFormatDukeException();
        }
    }

    private int[] parseClock(String clockString) throws WrongTimeFormatDukeException {
        int[] clockIntElements = new int[2];
        try {
            if (clockString.contains(":")) {
                clockIntElements[0] = Integer.parseInt(clockString.split(":")[0]);
                clockIntElements[1] = Integer.parseInt(clockString.split(":")[1]);
                return clockIntElements;
            }
            clockIntElements[0] = Integer.parseInt(clockString.substring(0, 2));
            clockIntElements[1] = Integer.parseInt(clockString.substring(2));
            return clockIntElements;
        } catch (Exception e) {
            throw new WrongTimeFormatDukeException();
        }
    }

    private String formatTime(int minute) {

        if (minute < 10) {
            return "0" + String.valueOf(minute);
        } else {
            return String.valueOf(minute);
        }

    }

    @Override
    public String toString() {
        return String.format("%s-%s-%s %s:%s", time.getYear(), formatTime(time.getMonthValue()),
                formatTime(time.getDayOfMonth()), formatTime(time.getHour()), formatTime(time.getMinute()));

    }


}

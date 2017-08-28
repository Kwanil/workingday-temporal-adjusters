package time;

import java.time.DayOfWeek;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.Temporal;
import java.util.*;

/**
 * Created by Kwanil-lee on 2017. 8. 8..
 */
public final class Holidays {
    private static final List<DayOfWeek> WEEKENDS = Arrays.asList(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);

    private final List<ChronoLocalDate> holidays = new ArrayList<>();

    public static Holidays of(Collection<? extends ChronoLocalDate> holidays) {
        return new Holidays(holidays);
    }

    private Holidays(Collection<? extends ChronoLocalDate> holidays) {
        this.holidays.addAll(Objects.requireNonNull(holidays));
    }

    public boolean isWorkingDay(Temporal temporal) {
        return !isWeekEndOrHoliday(temporal);
    }

    public boolean isWeekEndOrHoliday(Temporal temporal){
        return Holidays.isWeekEnd(temporal) || isHoliday(temporal);
    }

    public static boolean isWeekEnd(Temporal temporal){
        DayOfWeek dayOfWeek = DayOfWeek.from(temporal);
        return WEEKENDS.contains(dayOfWeek);
    }

    public boolean isHoliday(Temporal temporal) {
        return holidays.stream().anyMatch(cl -> cl.compareTo(ChronoLocalDate.from(temporal)) == 0 );
    }
}

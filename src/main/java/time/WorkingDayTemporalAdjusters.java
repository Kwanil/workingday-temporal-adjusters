package time;

import java.time.DayOfWeek;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.util.*;
import java.util.function.Predicate;

public class WorkingDayTemporalAdjusters {
    private final List<DayOfWeek> weekEnds = Arrays.asList(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
    private final List<ChronoLocalDate> holidays = new ArrayList<>();

    private WorkingDayTemporalAdjusters(Collection<? extends ChronoLocalDate> holidays) {
        this.holidays.addAll(Objects.requireNonNull(holidays));
    }

    public static WorkingDayTemporalAdjusters onlyWeekEnd() {
        return new WorkingDayTemporalAdjusters(Collections.emptyList());
    }

    public static WorkingDayTemporalAdjusters withHolidays(Collection<? extends ChronoLocalDate> holidays){
        return new WorkingDayTemporalAdjusters(holidays);
    }

    public TemporalAdjuster nextHoliday(){
        return temporal -> findIterate(temporal.plus(1, ChronoUnit.DAYS), this::isWeekEndOrHoliday, 1);
    }

    public TemporalAdjuster previousHoliday(){
        return temporal -> findIterate(temporal.minus(1, ChronoUnit.DAYS), this::isWeekEndOrHoliday, -1);
    }

    public TemporalAdjuster nextWorkingDay(){
        return temporal -> findIterate(temporal.plus(1, ChronoUnit.DAYS), this::isWorkingDay, 1);
    }

    public TemporalAdjuster previousWorkingDay(){
        return temporal -> findIterate(temporal.minus(1, ChronoUnit.DAYS), this::isWorkingDay, -1);
    }

    private Temporal findIterate(Temporal temporal, Predicate<Temporal> filter, int step) {
        Objects.requireNonNull(temporal);
        for(Temporal t= temporal;; t=t.plus(step, ChronoUnit.DAYS)) {
            if(filter.test(t)) {
                return t;
            }
        }
    }

    boolean isWorkingDay(Temporal temporal) {
        return !isWeekEndOrHoliday(temporal);
    }

    boolean isWeekEndOrHoliday(Temporal temporal){
        return isWeekEnd(temporal) || isHoliday(temporal);
    }

    boolean isWeekEnd(Temporal temporal){
        DayOfWeek dayOfWeek = DayOfWeek.from(temporal);
        return weekEnds.contains(dayOfWeek);
    }

    boolean isHoliday(Temporal temporal) {
        return holidays.stream().anyMatch(cl -> cl.compareTo(ChronoLocalDate.from(temporal)) == 0 );
    }
}

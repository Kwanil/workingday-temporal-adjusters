package time;

import java.time.chrono.ChronoLocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.function.Predicate;

public class WorkingDayTemporalAdjusters {
    private final Holidays holidays;

    private WorkingDayTemporalAdjusters(Collection<? extends ChronoLocalDate> holidays) {
        this.holidays = Holidays.of(holidays);
    }

    public static WorkingDayTemporalAdjusters onlyWeekEnd() {
        return new WorkingDayTemporalAdjusters(Collections.emptyList());
    }

    public static WorkingDayTemporalAdjusters withHolidays(Collection<? extends ChronoLocalDate> holidays){
        return new WorkingDayTemporalAdjusters(holidays);
    }

    public TemporalAdjuster nextHoliday(){
        return temporal -> findIterate(temporal.plus(1, ChronoUnit.DAYS), holidays::isWeekEndOrHoliday, Direction.NEXT);
    }

    public TemporalAdjuster previousHoliday(){
        return temporal -> findIterate(temporal.minus(1, ChronoUnit.DAYS), holidays::isWeekEndOrHoliday, Direction.PREVIOUS);
    }

    public TemporalAdjuster nextOrSameHoliday(){
        return temporal -> findIterate(temporal, holidays::isWeekEndOrHoliday, Direction.NEXT);
    }

    public TemporalAdjuster previousOrSameHoliday(){
        return temporal -> findIterate(temporal, holidays::isWeekEndOrHoliday, Direction.PREVIOUS);
    }

    public TemporalAdjuster nextWorkingDay(){
        return temporal -> findIterate(temporal.plus(1, ChronoUnit.DAYS), holidays::isWorkingDay, Direction.NEXT);
    }

    public TemporalAdjuster previousWorkingDay(){
        return temporal -> findIterate(temporal.minus(1, ChronoUnit.DAYS), holidays::isWorkingDay, Direction.PREVIOUS);
    }

    public TemporalAdjuster nextOrSameWorkingDay(){
        return temporal -> findIterate(temporal, holidays::isWorkingDay, Direction.NEXT);
    }

    public TemporalAdjuster previousOrSameWorkingDay(){
        return temporal -> findIterate(temporal, holidays::isWorkingDay, Direction.PREVIOUS);
    }

    private Temporal findIterate(Temporal temporal, Predicate<Temporal> filter, Direction direction) {
        Objects.requireNonNull(temporal);
        for(Temporal t= temporal;; t=t.plus(direction.amount, ChronoUnit.DAYS)) {
            if(filter.test(t)) {
                return t;
            }
        }
    }

    private enum Direction{
        NEXT(1), PREVIOUS(-1);
        private final int amount;
        Direction(int amount){
            this.amount = amount;
        }
    }
}

package time;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class OnlyWeekEndTemporalAdjustersTest {
    WorkingDayTemporalAdjusters temporalAdjusters = WorkingDayTemporalAdjusters.onlyWeekEnd();

    @Test
    public void nextHoliday() throws Exception {
        LocalDate friday = LocalDate.of(2017, 9, 29);
        LocalDate nextDay = friday.with(temporalAdjusters.nextHoliday());
        assertEquals(LocalDate.of(2017, 9, 30), nextDay);
    }

    @Test
    public void previousHoliday() throws Exception {
        LocalDate friday = LocalDate.of(2017, 9, 29);
        LocalDate nextDay = friday.with(temporalAdjusters.previousHoliday());
        assertEquals(LocalDate.of(2017, 9, 24), nextDay);
    }

    @Test
    public void nextWorkingDay() throws Exception {
        LocalDate friday = LocalDate.of(2017, 9, 29);
        LocalDate nextDay = friday.with(temporalAdjusters.nextWorkingDay());
        assertEquals(LocalDate.of(2017, 10, 2), nextDay);
    }

    @Test
    public void previousWorkingDay() throws Exception {
        LocalDate friday = LocalDate.of(2017, 9, 29);
        LocalDate nextDay = friday.with(temporalAdjusters.previousWorkingDay());
        assertEquals(LocalDate.of(2017, 9, 28), nextDay);
    }


}
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
        LocalDate previousDay = friday.with(temporalAdjusters.previousHoliday());
        assertEquals(LocalDate.of(2017, 9, 24), previousDay);
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
        LocalDate previousDay = friday.with(temporalAdjusters.previousWorkingDay());
        assertEquals(LocalDate.of(2017, 9, 28), previousDay);
    }


    @Test
    public void nextOrSameHoliday() throws Exception {
        LocalDate sunday = LocalDate.of(2017, 10, 1);
        assertEquals(sunday, sunday.with(temporalAdjusters.nextOrSameHoliday()));
        LocalDate friday = LocalDate.of(2017, 9, 29);
        assertEquals(LocalDate.of(2017,9,30), friday.with(temporalAdjusters.nextOrSameHoliday()));
    }

    @Test
    public void previousOrSameHoliday() throws Exception {
        LocalDate sunday = LocalDate.of(2017, 10, 1);
        assertEquals(sunday, sunday.with(temporalAdjusters.previousOrSameHoliday()));
        LocalDate monday = LocalDate.of(2017, 10, 2);
        assertEquals(LocalDate.of(2017,10,1), monday.with(temporalAdjusters.previousOrSameHoliday()));
    }

    @Test
    public void nextOrSameWorkingDay() throws Exception {
        LocalDate sunday = LocalDate.of(2017, 10, 1);
        assertEquals(LocalDate.of(2017,10,2), sunday.with(temporalAdjusters.nextOrSameWorkingDay()));
        LocalDate friday = LocalDate.of(2017, 9, 29);
        assertEquals(friday, friday.with(temporalAdjusters.nextOrSameWorkingDay()));
    }

    @Test
    public void previousOrSameWorkingDay() throws Exception {
        LocalDate sunday = LocalDate.of(2017, 10, 1);
        assertEquals(LocalDate.of(2017,9,29), sunday.with(temporalAdjusters.previousOrSameWorkingDay()));
        LocalDate friday = LocalDate.of(2017, 9, 29);
        assertEquals(friday, friday.with(temporalAdjusters.previousOrSameWorkingDay()));
    }
}
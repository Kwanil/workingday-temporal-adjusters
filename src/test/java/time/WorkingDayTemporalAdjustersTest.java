package time;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class WorkingDayTemporalAdjustersTest {
    WorkingDayTemporalAdjusters onlyWeekEndAdjusters = WorkingDayTemporalAdjusters.onlyWeekEnd();
    WorkingDayTemporalAdjusters withHolidaysAdjusters = WorkingDayTemporalAdjusters.withHolidays(
            Arrays.asList(
                    LocalDate.of(2017, 10, 3),//the National foundation Day
                    LocalDate.of(2017, 10, 4),//Korean Thanksgiving Day
                    LocalDate.of(2017, 10, 5),//Korean Thanksgiving Day
                    LocalDate.of(2017, 10, 6),//Korean Thanksgiving Day
                    LocalDate.of(2017, 10, 9) //Hangul Day
            )
    );

    @Test
    public void nextHolidayOnOnlyWeekEnd() throws Exception {
        LocalDate friday = LocalDate.of(2017, 9, 29);
        LocalDate actual = friday.with(onlyWeekEndAdjusters.nextHoliday());
        assertEquals(LocalDate.of(2017, 9, 30), actual);
    }

    @Test
    public void previousHolidayOnOnlyWeekEnd() throws Exception {
        LocalDate friday = LocalDate.of(2017, 9, 29);
        LocalDate actual = friday.with(onlyWeekEndAdjusters.previousHoliday());
        assertEquals(LocalDate.of(2017, 9, 24), actual);
    }

    @Test
    public void nextWorkingDayOnOnlyWeekEnd() throws Exception {
        LocalDate friday = LocalDate.of(2017, 9, 29);
        LocalDate actual = friday.with(onlyWeekEndAdjusters.nextWorkingDay(1));
        assertEquals(LocalDate.of(2017, 10, 2), actual);
    }

    @Test
    public void previousWorkingDayOnOnlyWeekEnd() throws Exception {
        LocalDate friday = LocalDate.of(2017, 9, 29);
        LocalDate actual = friday.with(onlyWeekEndAdjusters.previousWorkingDay(1));
        assertEquals(LocalDate.of(2017, 9, 28), actual);
    }

    @Test
    public void nextHolidayOnWithHoliday() throws Exception {
        LocalDate friday = LocalDate.of(2017, 10, 2);
        LocalDate actual = friday.with(withHolidaysAdjusters.nextHoliday());
        assertEquals(LocalDate.of(2017, 10, 3), actual);
    }

    @Test
    public void previousHolidayOnWithHoliday() throws Exception {
        LocalDate friday = LocalDate.of(2017, 10, 2);
        LocalDate actual = friday.with(withHolidaysAdjusters.previousHoliday());
        assertEquals(LocalDate.of(2017, 10, 1), actual);
    }

    @Test
    public void nextWorkingDayOnWithHoliday() throws Exception {
        LocalDate friday = LocalDate.of(2017, 10, 2);
        LocalDate actual = friday.with(withHolidaysAdjusters.nextWorkingDay(1));
        assertEquals(LocalDate.of(2017, 10, 10), actual);
    }

    @Test
    public void previousWorkingDayOnWithHoliday() throws Exception {
        LocalDate friday = LocalDate.of(2017, 10, 2);
        LocalDate actual = friday.with(withHolidaysAdjusters.previousWorkingDay(1));
        assertEquals(LocalDate.of(2017, 9, 29), actual);
    }

}
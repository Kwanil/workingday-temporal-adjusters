package time;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by naver on 2017. 8. 7..
 */
public class WithHolidaysTemporalAdjustersTest {
    WorkingDayTemporalAdjusters temporalAdjusters = WorkingDayTemporalAdjusters.withHolidays(
            Arrays.asList(
                    LocalDate.of(2017, 10, 3),//the National foundation Day
                    LocalDate.of(2017, 10, 4),//Korean Thanksgiving Day
                    LocalDate.of(2017, 10, 5),//Korean Thanksgiving Day
                    LocalDate.of(2017, 10, 6),//Korean Thanksgiving Day
                    LocalDate.of(2017, 10, 9) //Hangul Day
            )
    );
    
    @Test
    public void nextHoliday() throws Exception {
        LocalDate friday = LocalDate.of(2017, 10, 2);
        LocalDate nextDay = friday.with(temporalAdjusters.nextHoliday());
        assertEquals(LocalDate.of(2017, 10, 3), nextDay);
    }

    @Test
    public void previousHoliday() throws Exception {
        LocalDate friday = LocalDate.of(2017, 10, 2);
        LocalDate nextDay = friday.with(temporalAdjusters.previousHoliday());
        assertEquals(LocalDate.of(2017, 10, 1), nextDay);
    }

    @Test
    public void nextWorkingDay() throws Exception {
        LocalDate friday = LocalDate.of(2017, 10, 2);
        LocalDate nextDay = friday.with(temporalAdjusters.nextWorkingDay());
        assertEquals(LocalDate.of(2017, 10, 10), nextDay);
    }

    @Test
    public void previousWorkingDay() throws Exception {
        LocalDate friday = LocalDate.of(2017, 10, 2);
        LocalDate nextDay = friday.with(temporalAdjusters.previousWorkingDay());
        assertEquals(LocalDate.of(2017, 9, 29), nextDay);
    }

}
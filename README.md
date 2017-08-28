##평일/휴일 계산 - Workingday TemporalAdjusters
jdk8에서 사용하고 있는 java.time을 사용한 평일/공휴일 계산하는 TemporalAdjuster

1. 추가 공휴일 없이 주말만으로 평일/공휴일 계산할때
<code>WorkingDayTemporalAdjusters.onlyWeekEnd()<code>

<pre>
<code>
@Test
public void onlyWeekEnd() throws Exception {
    WorkingDayTemporalAdjusters adjusters = WorkingDayTemporalAdjusters.onlyWeekEnd();
    
    LocalDate friday = LocalDate.of(2017, 9, 29);
    
    LocalDate saturday = friday.with(adjusters.nextHoliday());
    assertEquals(LocalDate.of(2017, 9, 30), friday.with(adjusters.nextHoliday()));
    
    
    LocalDate monday = friday.with(adjusters.nextWorkingDay());
    assertEquals(LocalDate.of(2017, 10, 2), monday);

    LocalDate thursday = friday.with(adjusters.previousWorkingDay());
    assertEquals(LocalDate.of(2017, 9, 28), thursday);
    
    LocalDate sunday = friday.with(adjusters.previousHoliday());
    assertEquals(LocalDate.of(2017, 9, 24), sunday);
}
</code>
</pre>


2. 추가 공휴일을 등록하고 평일/공휴일을 계산할때
<code>WorkingDayTemporalAdjusters.withHolidays(Collection<? extends ChronoLocalDate> holidays)<code>

<pre>
<code>
@Test
public void withHolidays() throws Exception {
    WorkingDayTemporalAdjusters adjusters = WorkingDayTemporalAdjusters.withHolidays(
        asList(
            LocalDate.of(2017, 10, 3),//the National foundation Day
            LocalDate.of(2017, 10, 4),//Korean Thanksgiving Day
            LocalDate.of(2017, 10, 5),//Korean Thanksgiving Day
            LocalDate.of(2017, 10, 6),//Korean Thanksgiving Day
            LocalDate.of(2017, 10, 9) //Hangul Day
        )
    );
    
    LocalDate monday = LocalDate.of(2017, 10, 2);
    
    LocalDate nationalFoundationDay = monday.with(adjusters.nextHoliday());
    assertEquals(LocalDate.of(2017, 10, 3), nationalFoundationDay);
    
    LocalDate nextDay = monday.with(adjusters.nextWorkingDay());
    assertEquals(LocalDate.of(2017, 10, 10), nextDay);
    
    LocalDate previousDay = monday.with(adjusters.previousWorkingDay());
    assertEquals(LocalDate.of(2017, 9, 29), previousDay);
    
    LocalDate sunday = monday.with(adjusters.previousHoliday());
    assertEquals(LocalDate.of(2017, 10, 1), sunday);
}
</code>
</pre>
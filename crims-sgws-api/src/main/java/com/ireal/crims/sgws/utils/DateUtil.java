package com.ireal.crims.sgws.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public final SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	public static long getDifferenceSecond(Date prvDate, Date nextData) throws ParseException {
	    SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	    /*天数差*/    
	    
	    Date fromDate1 = simpleFormat.parse("2018-03-01 12:00");
	    Date toDate1 = simpleFormat.parse("2018-03-12 12:00");  
	    long from1 = fromDate1.getTime();  
	    long to1 = toDate1.getTime();  
	    int days = (int) ((to1 - from1) / (1000 * 60 * 60 * 24));  
	    System.out.println("两个时间之间的天数差为：" + days);

	    /*小时差*/
	    Date fromDate2 = simpleFormat.parse("2018-03-01 12:00");  
	    Date toDate2 = simpleFormat.parse("2018-03-12 12:00");  
	    long from2 = fromDate2.getTime();  
	    long to2 = toDate2.getTime();  
	    int hours = (int) ((to2 - from2) / (1000 * 60 * 60));
	    System.out.println("两个时间之间的小时差为：" + hours);

	    /*分钟差*/
	    Date fromDate3 = simpleFormat.parse("2018-03-01 12:00");  
	    Date toDate3 = simpleFormat.parse("2018-03-12 12:00");  
	    long from3 = fromDate3.getTime();  
	    long to3 = toDate3.getTime();  
	    int minutes = (int) ((to3 - from3) / (1000 * 60));  
	    System.out.println("两个时间之间的分钟差为：" + minutes);	
		
		return 0;
	}
	/**
	 *使用Calendar对象计算时间差，可以按照需求定制自己的计算逻辑
	 * @param strDate
	 * @throws ParseException
	 */
	public static void calculateTimeDifferenceByCalendar(String strDate) throws ParseException {
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
	    Date date = formatter.parse(strDate);

	    Calendar c1 = Calendar.getInstance();   //当前日期
	    Calendar c2 = Calendar.getInstance();
	    c2.setTime(date);   					//设置为另一个时间


	    int year = c1.get(Calendar.YEAR);
	    int oldYear = c2.get(Calendar.YEAR);

	    //这里只是简单的对两个年份数字进行相减，而没有考虑月份的情况

	    System.out.println("传入的日期与今年的年份差为：" + (year - oldYear));
	}

	/**
	 * 使用java 8的Period的对象计算两个LocalDate对象的时间差，严格按照年、月、日计算，如：2018-03-12 与 2014-05-23 相差 3 年 9 个月 17 天

	 * @param year
	 * @param month
	 * @param dayOfMonth
	 */
	public static void calculateTimeDifferenceByPeriod(int year, Month month, int dayOfMonth) {
	    LocalDate today = LocalDate.now();
	    System.out.println("Today：" + today);
	    LocalDate oldDate = LocalDate.of(year, month, dayOfMonth);
	    System.out.println("OldDate：" + oldDate);

	    Period p = Period.between(oldDate, today);
	    System.out.printf("目标日期距离今天的时间差：%d 年 %d 个月 %d 天\n", p.getYears(), p.getMonths(), p.getDays());
	}
	
	public static void calculateTimeDifferenceByDuration() {
	    Instant inst1 = Instant.now();  //当前的时间

	    System.out.println("Inst1：" + inst1);
	    Instant inst2 = inst1.plus(Duration.ofSeconds(10));     //当前时间+10秒后的时间

	    System.out.println("Inst2：" + inst2);
	    Instant inst3 = inst1.plus(Duration.ofDays(125));       //当前时间+125天后的时间

	    System.out.println("inst3：" + inst3);

	    System.out.println("以毫秒计的时间差：" + Duration.between(inst1, inst2).toMillis());

	    System.out.println("以秒计的时间差：" + Duration.between(inst1, inst3).getSeconds());
	}
	
	/**
	 * 使用java 8的ChronoUnit，ChronoUnit可以以多种单位（基本涵盖了所有的，看源码发现竟然还有“FOREVER”这种单位。。）表示两个时间的时间差
	 */
	public static void calculateTimeDifferenceByChronoUnit() {
	    LocalDate startDate = LocalDate.of(2003, Month.MAY, 9);
	    System.out.println("开始时间：" + startDate);

	    LocalDate endDate = LocalDate.of(2015, Month.JANUARY, 26);
	    System.out.println("结束时间：" + endDate);

	    long daysDiff = ChronoUnit.DAYS.between(startDate, endDate);
	    System.out.println("两个时间之间的天数差为：" + daysDiff);
	//  long hoursDiff = ChronoUnit.HOURS.between(startDate, endDate);  //这句会抛出异常，因为LocalDate表示的时间中不包含时分秒等信息

	}
	/**
	* 用SimpleDateFormat计算时间差

	* @throws ParseException 
	*/
	public static void calculateTimeDifferenceBySimpleDateFormat() throws ParseException {
	    SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
	    /*天数差*/
	    Date fromDate1 = simpleFormat.parse("2018-03-01 12:00");  
	    Date toDate1 = simpleFormat.parse("2018-03-12 12:00");  
	    long from1 = fromDate1.getTime();  
	    long to1 = toDate1.getTime();  
	    int days = (int) ((to1 - from1) / (1000 * 60 * 60 * 24));  
	    System.out.println("两个时间之间的天数差为：" + days);

	    /*小时差*/
	    Date fromDate2 = simpleFormat.parse("2018-03-01 12:00");  
	    Date toDate2 = simpleFormat.parse("2018-03-12 12:00");  
	    long from2 = fromDate2.getTime();  
	    long to2 = toDate2.getTime();  
	    int hours = (int) ((to2 - from2) / (1000 * 60 * 60));
	    System.out.println("两个时间之间的小时差为：" + hours);

	    /*分钟差*/
	    Date fromDate3 = simpleFormat.parse("2018-03-01 12:00");  
	    Date toDate3 = simpleFormat.parse("2018-03-12 12:00");  
	    long from3 = fromDate3.getTime();  
	    long to3 = toDate3.getTime();  
	    int minutes = (int) ((to3 - from3) / (1000 * 60));  
	    System.out.println("两个时间之间的分钟差为：" + minutes);
	}

}

package com.hnmobile.wechat.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.util.StringUtils;

public class DateUtils {

	public static String DATE_FORMAT = "yyyy-MM-dd";
	public static String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static String LOG_DATE_FORMAT = "yyyyMMdd";
	public static String LOG_DATETIME_FORMAT = "yyyyMMddHHmmss";

	public static String formatTime(Date date) {
		if (date == null)
			return null;
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATETIME_FORMAT);
		return dateFormat.format(date);
	}
	

	public static String formatLogDate(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(LOG_DATE_FORMAT);
		return dateFormat.format(date);
	}

	public static String formatLogTime(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(LOG_DATETIME_FORMAT);
		return dateFormat.format(date);
	}

	public static Date parseLogDate(String dateStr) {
		if (StringUtils.isEmpty(dateStr))
			return null;
		SimpleDateFormat simFormat = new SimpleDateFormat(LOG_DATE_FORMAT);
		try {
			return simFormat.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
	}

	public static Date parseDatetimeWithTimezone(String dateStr, String timeZone) {
		if (StringUtils.isEmpty(dateStr))
			return null;
		try {
			SimpleDateFormat simFormat = new SimpleDateFormat(DATETIME_FORMAT);
			if ( !StringUtils.isEmpty(timeZone) ) {
				simFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
			}
			return simFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date parseDate(String dateStr) {
		if (StringUtils.isEmpty(dateStr))
			return null;
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		try {
			return formatter.parse(dateStr);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	

	public static Date parseDatetime(String dateStr) {
		if (StringUtils.isEmpty(dateStr))
			return null;
		try {
			SimpleDateFormat simFormat = new SimpleDateFormat(DATETIME_FORMAT);
			simFormat.setTimeZone(Calendar.getInstance().getTimeZone());
			return simFormat.parse(dateStr);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public static Date getYesterday() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

	public static Date getThisMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public static Date getNextMonth(Date month) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(month);
		calendar.add(Calendar.MONTH, 1);
		return calendar.getTime();
	}

	public static Date getLastMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public static String format(Date date, String pattern) {
		if (date == null)
			throw new IllegalArgumentException("date is null");
		if (pattern == null)
			throw new IllegalArgumentException("pattern is null");

		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(date);
	}

	public static Date parse(String str, String pattern) throws ParseException {
		if (str == null)
			throw new IllegalArgumentException("str is null");
		if (pattern == null)
			throw new IllegalArgumentException("pattern is null");

		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.parse(str);
	}

	/**
	 * 
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String formatDateTime(Date date) {
		if (date == null)return "";
		SimpleDateFormat simFormat = new SimpleDateFormat(DATETIME_FORMAT);
		simFormat.setTimeZone(Calendar.getInstance().getTimeZone());
		return simFormat.format(date);
	}

	public static String formatDateTime(XMLGregorianCalendar date) {
		if (date == null)
			return "";
		SimpleDateFormat simFormat = new SimpleDateFormat(DATETIME_FORMAT);
		simFormat.setTimeZone(Calendar.getInstance().getTimeZone());
		return simFormat.format(date.toGregorianCalendar().getTime());
	}

	public static String formatDateTimeWithTimezone(Date date, String timeZone) {
		if (date == null)
			return "";
		SimpleDateFormat simFormat = new SimpleDateFormat(DATETIME_FORMAT);
		if ( !StringUtils.isEmpty(timeZone) ) {
			simFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
		}
		return simFormat.format(date);
	}

	/**
	 * 
	 * @param date
	 * @return yyyy-MM-dd
	 */
	public static String formatDate(Date date) {
		if (date == null)
			return "";
		SimpleDateFormat simFormat = new SimpleDateFormat(DATE_FORMAT);
		simFormat.setTimeZone(Calendar.getInstance().getTimeZone());
		return simFormat.format(date);
	}
	
	
	public static String formatDate(Date date,String pattern) {
		if (date == null)
			return "";
		SimpleDateFormat simFormat = new SimpleDateFormat(pattern);
		simFormat.setTimeZone(Calendar.getInstance().getTimeZone());
		return simFormat.format(date);
	}
	
	public static String formatDate(XMLGregorianCalendar date) {
		if (date == null)
			return "";
		SimpleDateFormat simFormat = new SimpleDateFormat(DATE_FORMAT);
		simFormat.setTimeZone(Calendar.getInstance().getTimeZone());
		return simFormat.format(date.toGregorianCalendar().getTime());
	}

	public static Date parseDatetime(String datetime, String pattern) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.parse(datetime);
	}

	public static Date parseDatetime(String datetime, String pattern,
			Locale locale) {
		if (StringUtils.isEmpty(datetime))
			return null;
		SimpleDateFormat formatter = new SimpleDateFormat(pattern, locale);
		try {
			return formatter.parse(datetime);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public static boolean isTimeIn(Date time, Date start, Date end) {
		long t = time.getTime();
		long s = start.getTime();
		long e = end.getTime();
		return (t > s && t <= e);
	}

	public static Date getCurrentSyncDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static Date formatGMT8toUTC(Date date) {
		if (date == null) {
			return null;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR, -8);

		return cal.getTime();
	}

	public static String formatGMT8toUTC(Date date, String pattern) {
		if (date == null) {
			return null;
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(formatGMT8toUTC(date).getTime());
	}

	public static XMLGregorianCalendar convertToXMLGregorianCalendar(Date date) {
		if (date == null) {
			return null;
		}
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		XMLGregorianCalendar gc = null;
		try {
			gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return gc;
	}

	public static Date convertToDate(XMLGregorianCalendar cal) throws Exception {
		if (cal == null) {
			return null;
		}

		GregorianCalendar ca = cal.toGregorianCalendar();
		return ca.getTime();
	}

	public static Date UTCtoGMT8(Date date) {
		if (date == null) {
			return null;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR, +8);

		return cal.getTime();
	}

	public static String getLastDateStr() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, -1);
		return formatDate(cal.getTime());
	}

	public static Date getDateByHours(int hours) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.HOUR, hours);
		return cal.getTime();
	}

	public static String getNowUtcStr() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR, -8);
		return formatDateTime(cal.getTime());
	}

	public static String getTodayUtcStr() {
		Date date = new Date();
		String pek = formatDate(date) + " 05:00:00";
		Date pedate = parseDatetime(pek);
		Calendar cal = Calendar.getInstance();
		cal.setTime(pedate);
		cal.add(Calendar.HOUR, -8);
		return formatDateTime(cal.getTime());
	}

	public static String getTommorrowUtcStr() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 1);
		String pek = formatDate(cal.getTime()) + " 04:59:59";
		Date pekDate = parseDatetime(pek);
		cal = Calendar.getInstance();
		cal.setTime(pekDate);
		cal.add(Calendar.HOUR, -8);
		return formatDateTime(cal.getTime());
	}
	
	public static String getTodayStr() {
		Date date = new Date();
		return formatDate(date) + " 05:00:00";
	}

	public static String getTommorrowStr() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 1);
		return formatDate(cal.getTime()) + " 04:59:59";

	}

	public static String convertToEndUtcToPek(String endUtcTime) {
		Date utc = DateUtils.parseDatetime(endUtcTime);
		Calendar cal = Calendar.getInstance();
		cal.setTime(utc);
		cal.add(Calendar.HOUR, 8);
		cal.add(Calendar.DATE, 1);
		return formatDate(cal.getTime()) + " 04:59:59";
	}

	public static String convertToStartUtcToPek(String startUtcTime) {
		Date utc = DateUtils.parseDatetime(startUtcTime);
		Calendar cal = Calendar.getInstance();
		cal.setTime(utc);
		cal.add(Calendar.HOUR, 8);
		return formatDate(cal.getTime()) + " 05:00:00";
	}

	public static Date getTodayZeroTime() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	/**
	 * 
	 * @param date
	 * @return yyyy-MM-dd
	 */
	public static String getNextDay( Date date ) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 1);
		return formatDate(cal.getTime());
	}
	/**
	 * 
	 * @param date
	 * @return yyyy-MM-dd
	 */
	public static String getNextDay( Date date,String pattern ) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 1);
		return formatDate(cal.getTime(),pattern);
	}
	
	/**
	 * 
	 * @param date
	 * @return yyyy-MM-dd
	 */
	public static Date getNextODay( Date date ) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 1);
		return cal.getTime();
	}
	
	/**
	 * 
	 * @param 
	 * @return yyyy-MM-dd
	 */
	public static String getToday() {
		Date date = new Date();
		return formatDate(date);
	}
	/**
	 * 
	 * @param yyyy-MM-dd
	 * @return yyyy-MM-dd
	 */
	public static String getNextDay( String sdate ) {
		Date date = parseDate(sdate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 1);
		return formatDate(cal.getTime());
	}
	
	/**
	 * 
	 * @param sdate yyyy-MM-dd ��ʽ
	 * @param i ���� ������ֵ��ʾǰ������
	 * @return
	 */
	public static String getNextNDay( String sdate,int i ) {
		Date date = parseDate(sdate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, i);
		return formatDate(cal.getTime());
	}
	
	public static Date getNdays( int n) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, n);
		calendar.set(Calendar.HOUR_OF_DAY, 00);
		calendar.set(Calendar.MINUTE, 00);
		calendar.set(Calendar.SECOND, 00);
		return calendar.getTime();
	}
	
	/**
	 * 
	 * @param unixTimestamp:long���͵�unixʱ����� formats ��"yyyy-MM-dd HH:mm:ss" ����  "yyyy-MM-dd hh:mm:ss"
	 * @return yyyy-MM-dd
	 */
//	public static String UnixTimeStamp2DateStr(long unixTimestamp, String formats){ 
//		
//		  String date = new java.text.SimpleDateFormat(formats)
//		                   .format(new java.util.Date(unixTimestamp*1000));    
//		  return date;   
//		  
//	}
	
	/**
	 * 
	 * @param unixTimestamp:long���͵�unixʱ����� formats ��"yyyy-MM-dd HH:mm:ss" 
	 * @return yyyy-MM-dd
	 */
//	public static String UnixTimeStamp2DateStr(int unixTimestamp, String formats){ 
//		
//		  String date = new java.text.SimpleDateFormat(formats)
//		                   .format(new java.util.Date(Long.parseLong(unixTimestamp+"")*1000));    
//		  return date;   
//		  
//	}
	
	public static Date UnixTimeStamp2Date(int unixTimestamp, String formats) throws ParseException{ 
		 long ts = Long.parseLong(unixTimestamp+"");
		 String dateStr = new java.text.SimpleDateFormat(formats)
                              .format(new java.util.Date(ts*1000)); 
		 return parse(dateStr, formats);   
		  
	}
	
	public static Date UnixTimeStamp2Date(long timestamp, String formats) throws ParseException{ 
		 String dateStr = new java.text.SimpleDateFormat(formats)
                             .format(new java.util.Date(timestamp)); 
		 return parse(dateStr, formats);   
		  
	}
	
	public static Date UnixTimeStamp2Date(int unixTimestamp) throws ParseException{ 
		 long ts = Long.parseLong(unixTimestamp+"");
		 String dateStr = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                             .format(new java.util.Date(ts*1000)); 
		 return parse(dateStr, "yyyy-MM-dd HH:mm:ss");   
		  
	}
	
	public static String UnixTimeStamp2DateStr(int unixTimestamp){ 
		 long ts = Long.parseLong(unixTimestamp+"");
		 String dateStr = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                            .format(new java.util.Date(ts*1000)); 
		 return dateStr;
		  
	}
	
	public static int getUnixTimeStampByDate(String date,String formate) throws ParseException{
		
		return (int) (DateUtils.parseDatetime(date,formate).getTime()/1000);
		
	}
	
    public static int getUnixTimeStampByDateStr(String date) throws ParseException{
		
		return getUnixTimeStampByDate( date,"yyyy-MM-dd HH:mm:ss");
		
	}
	
	/**
	 * ��������ʱ���������룬���  biggerTime ��  littleTimeС����littelTime���ᱻ��һ��֮�����ж�
	 * @param biggerTime hh:mm
	 * @param time hh:mm
	 * @return ��������
	 */
	public static int compareTime( int biggerTime,int littleTime ){
		
		if( biggerTime<littleTime ){
			littleTime = littleTime - 24*60*60;
		}
		return biggerTime - littleTime;
	}
	
	/**
	 * ����unixʱ���������������
	 * @param date
	 * @return
	 */
    public static int getUnixTimeStampByDate(Date date){
		
    	int ts =  (int) (date.getTime()/1000);
		return ts;
		
	}
    
    /**
	 * ����unixʱ���������������
	 * @param dateStr ���ڸ�ʽΪ yyyy-MM-dd HH:mm:ss
	 * @return
	 */
    public static int getUnixTimeStampByDate(String dateStr){
		
    	Date date = parseDatetime(dateStr);
		int ts =  (int) (date.getTime()/1000);
		return ts;
		
	}
    
    /**
     * �� getUnixTimeStampMs ���ص�ֵ��ת���ɷ���,����СʱΪ 2000-01-01 0ʱ
     * @param ms
     * @return
     * @throws ParseException 
     */
    public static String getMSByShort( short msShort ) throws ParseException{
    	
    	int date = 946656000+msShort;//946656000 = 2000-01-01 00:00:00
    	
    	Date dt = UnixTimeStamp2Date(date, "yyyy-MM-dd HH:mm:ss");
    	
    	String dateStr = format(dt, "yyyy-MM-dd HH:mm:ss");
    	
    	String[] hms = dateStr.split(" ")[1].split(":");
    	
    	String ms = hms[1]+":"+hms[2];
    	
    	return ms;
    	
    }
    
    /**
     * ��ȡUnix ʱ�����   yyyy-MM-dd HH
     * @param date ��������
     * @return
     */
    public static int getUnixTimeStampHour( int dateTs){
    	return dateTs - dateTs%3600;
	}
    
    public static int addNDay( int today,int n){
    	
    	return today += n*24*60*60;
    	
    }
    
    /**
     * �ж� dateTs ��Сʱ���Ƿ�����  beginHhmmss ��  endHhmmss ֮�䣬��߽�
     * @param dateTs unixʱ���
     * @param beginHhmmss ��ʼʱ�䣬��ʽΪ HH:mm:ss
     * @param endHhmmss ����ʱ�䣬��ʽΪ HH:mm:ss
     * @return
     * @throws ParseException 
     */
    public static boolean betweenTime( int dateTs,String beginHhmmss,String endHhmmss ) throws ParseException{
    	
    	Date date = UnixTimeStamp2Date(dateTs,"yyyy-MM-dd HH:mm:ss");
    	String dateStr = format(date, "yyyy-MM-dd HH:mm:ss");
    	String day = dateStr.split(" ")[0];
    	int beginDate = getUnixTimeStampByDate(day+" "+beginHhmmss, "yyyy-MM-dd HH:mm:ss");
    	int endDate = getUnixTimeStampByDate(day+" "+endHhmmss, "yyyy-MM-dd HH:mm:ss");
    	if( beginDate<=dateTs&&dateTs<=endDate ){
    		return true;
    	}
    	return false;
    }
    
    public static String unixTimeStamp2Date(String timestampString, String formats){  
    	  Long timestamp = Long.parseLong(timestampString)*1000;  
    	  String date = new java.text.SimpleDateFormat(formats).format(new java.util.Date(timestamp));  
    	  return date;  
    }
    
    public static String unixTimeStamp2Date(String timestampString){  
  	  Long timestamp = Long.parseLong(timestampString)*1000;  
  	  String date = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(timestamp));  
  	  return date;  
    }
    
    /**  
     * 计算两个日期之间相差的天数  
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
     * @throws ParseException  
     */    
    public static int daysBetween(Date smdate,Date bdate) throws ParseException{    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));           
    }
    
}

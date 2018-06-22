package com.comm.util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
/**
 * 日期时间工具类
 */
public class DateUtils {
	private static final SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
	private static final SimpleDateFormat fileFormatDir = new SimpleDateFormat("yyyyMM");
	private static final SimpleDateFormat datetimeFormatSSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
	
	/**
	 * 获得当前时间的毫秒数
	 * 时间戳
	 * @return
	 */
	public static long currentTimeMillis() {
		return System.currentTimeMillis();
	}
	/**
	 * 获得当前日期时间
	 * 日期时间格式yyyy-MM-dd HH:mm:ss.fff
	 * @return
	 */
	public static String currentDatetimeSSS() {
		return datetimeFormatSSS.format(now());
	}
    public static String FormatDir(Date dt){
    	return fileFormatDir.format(dt);
    }
	/**
	 * 获得当前日期时间
	 * 日期时间格式yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String currentDatetime() {
		return datetimeFormat.format(now());
	}

	/**
	 * 格式化日期时间
	 * 日期时间格式yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String formatDatetime(Date date) {
		return datetimeFormat.format(date);
	}

	/**
	 * 格式化日期时间
	 * 
	 * @param date
	 * @param pattern
	 *            格式化模式，详见{@link SimpleDateFormat}构造器
	 *            <code>SimpleDateFormat(String pattern)</code>
	 * @return
	 */
	public static String formatDatetime(Date date, String pattern) {
		SimpleDateFormat customFormat = (SimpleDateFormat) datetimeFormat.clone();
		customFormat.applyPattern(pattern);
		return customFormat.format(date);
	}

	/**
	 * 格式化当前日期时间
	 * 
	 * @param date
	 * @param pattern
	 *            格式化模式，详见{@link SimpleDateFormat}构造器
	 *            <code>SimpleDateFormat(String pattern)</code>
	 * @return
	 */
	public static String formatNow(String pattern) {
		SimpleDateFormat customFormat = (SimpleDateFormat) datetimeFormat.clone();
		customFormat.applyPattern(pattern);
		return customFormat.format(now());
	}

	/**
	 * 获得当前日期
	 * 日期格式yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String currentDate() {
		return dateFormat.format(now());
	}

	/**
	 * 格式化日期
	 * 日期格式yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String formatDate(Date date) {
		return dateFormat.format(date);
	}

	/**
	 * 获得当前时间
	 * 时间格式HH:mm:ss
	 * 
	 * @return
	 */
	public static String currentTime() {
		return timeFormat.format(now());
	}

	/**
	 * 格式化时间
	 * 时间格式HH:mm:ss
	 * 
	 * @return
	 */
	public static String formatTime(Date date) {
		return timeFormat.format(date);
	}

	/**
	 * 获得当前时间的<code>java.util.Date</code>对象
	 * 
	 * @return
	 */
	public static Date now() {
		return new Date();
	}

	public static Calendar calendar() {
		Calendar cal = Calendar.getInstance(Locale.CHINESE);
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		return cal;
	}

	/**
	 * 
	 * 获得当前Chinese月份
	 * 
	 * @return
	 */
	public static int month() {
		return calendar().get(Calendar.MONTH) + 1;
	}

	/**
	 * 获得月份中的第几天
	 * 
	 * @return
	 */
	public static int dayOfMonth() {
		return calendar().get(Calendar.DAY_OF_MONTH);
	}
	/**
	 * 获得月份中的第几天
	 * @param date
	 * @return
	 */
	public static int dayOfMonth(Date date) {
	    Calendar cal=Calendar.getInstance(); 
	    cal.setTime(date);
		return calendar().get(Calendar.DAY_OF_MONTH);
	}
	/**
	 * 今天是星期的第几天
	 * 
	 * @return
	 */
	public static int dayOfWeek() {
		return calendar().get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 今天是年中的第几天
	 * 
	 * @return
	 */
	public static int dayOfYear() {
		return calendar().get(Calendar.DAY_OF_YEAR);
	}

	/**
	 *判断原日期是否在目标日期之前
	 * 
	 * @param src
	 * @param dst
	 * @return
	 */
	public static boolean isBefore(Date src, Date dst) {
		return src.before(dst);
	}

	/**
	 *判断原日期是否在目标日期之后
	 * @param src
	 * @param dst
	 * @return
	 */
	public static boolean isAfter(Date src, Date dst) {
		return src.after(dst);
	}

	/**
	 *判断两日期是否相同
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isEqual(Date date1, Date date2) {
		return date1.compareTo(date2) == 0;
	}

	/**
	 * 判断某个日期是否在某个日期范围
	 * 
	 * @param beginDate
	 *            日期范围开始
	 * @param endDate
	 *            日期范围结束
	 * @param src
	 *            需要判断的日期
	 * @return
	 */
	public static boolean between(Date beginDate, Date endDate, Date src) {
		if(beginDate.equals(src)){//相等开始时间
			return true;
		}
		if(endDate==null && beginDate.before(src)){//没有结束时间，但是开始时间<给定的日期
			return true;
		}
		return beginDate.before(src) && endDate.after(src);
	}

	/**
	 * 获得当前月的最后一天
	 * HH:mm:ss为0，毫秒为999
	 * 
	 * @return
	 */
	public static Date lastDayOfMonth() {
		Calendar cal = calendar();
		return lastDayOfMonth(cal);
	}

	/**
	 * 获得当前月的最后一天
	 * HH:mm:ss为0，毫秒为999
	 * 
	 * @return
	 */
	public static Date lastDayOfMonth(Calendar cal) {
		cal.set(Calendar.DAY_OF_MONTH, 0); // M月置零
		cal.set(Calendar.HOUR_OF_DAY, 0);// H置零
		cal.set(Calendar.MINUTE, 0);// m置零
		cal.set(Calendar.SECOND, 0);// s置零
		cal.set(Calendar.MILLISECOND, 0);// S置零
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);// 月份+1
		cal.set(Calendar.MILLISECOND, -1);// 毫秒-1
		return cal.getTime();
	}

	/**
	 * 获得当前月的第一天
	 * HH:mm:ss SS为零
	 * 
	 * @return
	 */
	public static Date firstDayOfMonth() {
		Calendar cal = calendar();
		return firstDayOfMonth(cal);
	}

	/**
	 * 获得当前月的第一天
	 * HH:mm:ss SS为零
	 * 
	 * @return
	 */
	public static Date firstDayOfMonth(Calendar cal) {
		cal.set(Calendar.DAY_OF_MONTH, 1); // M月置1
		cal.set(Calendar.HOUR_OF_DAY, 0);// H置零
		cal.set(Calendar.MINUTE, 0);// m置零
		cal.set(Calendar.SECOND, 0);// s置零
		cal.set(Calendar.MILLISECOND, 0);// S置零
		return cal.getTime();
	}

	private static Date weekDay(int week) {
		Calendar cal = calendar();
		cal.set(Calendar.DAY_OF_WEEK, week);
		return cal.getTime();
	}

	/**
	 * 获得周五日期
	 * 注：日历工厂方法{@link #calendar()}设置类每个星期的第一天为Monday，US等每星期第一天为sunday
	 * 
	 * @return
	 */
	public static Date friday() {
		return weekDay(Calendar.FRIDAY);
	}

	/**
	 * 获得周六日期
	 * 注：日历工厂方法{@link #calendar()}设置类每个星期的第一天为Monday，US等每星期第一天为sunday
	 * 
	 * @return
	 */
	public static Date saturday() {
		return weekDay(Calendar.SATURDAY);
	}

	/**
	 * 获得周日日期
	 * 注：日历工厂方法{@link #calendar()}设置类每个星期的第一天为Monday，US等每星期第一天为sunday
	 * 
	 * @return
	 */
	public static Date sunday() {
		return weekDay(Calendar.SUNDAY);
	}

	/**
	 * 将字符串日期时间转换成java.util.Date类型
	 * 日期时间格式yyyy-MM-dd HH:mm:ss
	 * 
	 * @param datetime
	 * @return
	 */
	public static Date parseDatetime(String datetime) throws ParseException {
		return datetimeFormat.parse(datetime);
	}
	/**
	 * 将字符串日期转换成java.util.Date类型
	 *<p>
	 * 日期时间格式yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date parseDate(String date) throws ParseException {
		return dateFormat.parse(date);
	}

	/**
	 * 将字符串日期转换成java.util.Date类型
	 *<p>
	 * 时间格式 HH:mm:ss
	 * 
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	public static Date parseTime(String time) throws ParseException {
		return timeFormat.parse(time);
	}

	/**
	 * 根据自定义pattern将字符串日期转换成<code>java.util.Date</code>类型
	 * 
	 * @param datetime
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static Date parseDatetime(String datetime, String pattern) throws ParseException {
		SimpleDateFormat format = (SimpleDateFormat) datetimeFormat.clone();
		format.applyPattern(pattern);
		return format.parse(datetime);
	}
	/**
	 * 转换yyyy-MM-dd HH:mm:ss时间标准
	 * @param date 日期
	 * @return 日期时间格式yyyy-MM-dd HH:mm:ss
	 * @throws Exception
	 */
    public static Date parseDatetimeUS(String date) throws Exception{		
    	  //Thu Nov 11 00:00:00 CST 2010时间转换成为yyyy-MM-dd HH:mm:ss格式
    	  return datetimeFormat.parse(datetimeFormat.format(new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy",Locale.US).parse(date)));
    }
    /**
	 * 转换yyyy-MM-dd HH:mm:ss时间标准
	 * @param date 日期
	 * @return 日期时间格式yyyy-MM-dd HH:mm:ss
	 * @throws Exception
	 */
    public static String parseDatetimeUSstring(String date) throws Exception{		
    	  //Thu Nov 11 00:00:00 CST 2010时间转换成为yyyy-MM-dd HH:mm:ss格式
    	  return datetimeFormat.format(new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy",Locale.US).parse(date));
    }
    /**
     * 根据输入的日期返回星期几
     * @param dt
     * @return
     */
	 public static String  getWeekOfDate(String  dt){ 
		    String[]  weekDays={"星期日","星期一","星期二","星期三","星期四","星期五","星期六"}; 
		    Calendar cal=Calendar.getInstance(); 
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		    try {
				cal.setTime(dateFormat.parse(dt));
			} catch (ParseException e) {
				e.printStackTrace();
			} 		    
		    int w=cal.get(Calendar.DAY_OF_WEEK)-1; 
		    if(w <0)w=0; 
		    return weekDays[w]; 
	}
    /**
     * 根据输入的日期返回星期几
     * @param dt
     * @return
     */
	 public static String getWeekOfDate(Date  dt){ 
		    String[]  weekDays={"星期日","星期一","星期二","星期三","星期四","星期五","星期六"}; 
		    Calendar cal=Calendar.getInstance(); 
			cal.setTime(dt);				    
		    int w=cal.get(Calendar.DAY_OF_WEEK)-1; 
		    if(w <0)w=0; 
		    return weekDays[w]; 
	}
	 /**
	  * 根据输入的日期返回星期几
	  * @param dt
	  * @return 数字1-7
	  */
	public static Integer getWeekOfNum(Date  dt){
		    Calendar cal=Calendar.getInstance(); 
		    cal.setTime(dt);
		    int w=cal.get(Calendar.DAY_OF_WEEK)-1; 
		    if(w <0)w=0; 
		    if(w==0)w=7; 
		    return w; 
	}
	 /**
	  * 根据输入的日期返回星期几
	  * @param dt
	  * @return 数字1-7
	  */
	public static Integer getWeekOfNum(String  dt){ 
		    Calendar cal=Calendar.getInstance(); 
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		    try {
				cal.setTime(dateFormat.parse(dt));
			} catch (ParseException e) {
				e.printStackTrace();
			} 		
		    //System.out.println(dt+"日期："+cal.get(Calendar.DAY_OF_WEEK));
		    int w=cal.get(Calendar.DAY_OF_WEEK)-1; 
		    if(w <0)w=0; 
		    if(w==0)w=7; 
		    return w; 
	}
	//获得任何日期所在周的日期
	public   static String  getMonday(Date date){ 
	      Calendar   c   =   Calendar.getInstance(); 
	      c.setTime(date); 
	      c.set(Calendar.DAY_OF_WEEK, 0); 
	      return   new   SimpleDateFormat( "yyyy-MM-dd ").format(c.getTime()); 
	    } 
	// 计算某个日期（譬如2010/03/20）在这一年的第几周（一般一年56周吧），是星期几？
    /**
     * 单双周【按照正常工作日算1-7，并非正常国际公约日期表】
     */
 	public static boolean displayDayOfWeek(Date date){
		   Calendar c = Calendar.getInstance();
		   c.setTime(date);
		   int  week_num =c.get(Calendar.WEEK_OF_YEAR);	  
		   int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		   if(dayOfWeek==1){week_num-=1;}
		   if(week_num%2==0) return true;
		   else return false; 
	} 
	 /**
	  * 单双周【按照正常工作日算1-7，并非正常国际公约日期表】
	  * @param dt
	  * @return true双false单
	  */
	public static boolean displayDayOfWeek(String dt){ 
		Calendar cal=Calendar.getInstance(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {cal.setTime(dateFormat.parse(dt));
		} catch (ParseException e) {e.printStackTrace();} 		
		int week_num =cal.get(Calendar.WEEK_OF_YEAR);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		if(dayOfWeek==1){week_num-=1;}
		if(week_num%2==0) return true;
		else return false; 			
	}
	/**
	 * 天减记录
	 * @param dt
	 * @param num 追加数
	 * @return
	 */
	public static Date getDaySubtraction(Date dt,int num){ 
		Calendar c = Calendar.getInstance();  
        c.setTime(dt);  
        c.set(Calendar.DATE, c.get(Calendar.DATE) - num);
        Date tomorrow = c.getTime();      
	    return tomorrow;
	}
	/**
	 * 天追加记录
	 * @param dt
	 * @param num 追加数
	 * @return
	 */
	public static Date getDayAdd(Date dt,int num){ 
		Calendar c = Calendar.getInstance();  
        c.setTime(dt);  
        c.add(Calendar.DAY_OF_MONTH,num);// 今天+1天     
        Date tomorrow = c.getTime();      
	    return tomorrow;
	}
	/**
	 * 小时追加记录
	 * @param dt
	 * @param num 追加数
	 * @return
	 */
	public static Date getHourAdd(Date dt,int num){ 
		Calendar c = Calendar.getInstance();  
        c.setTime(dt);  
        c.add(Calendar.HOUR_OF_DAY,num);// 今天+1小时
        Date tomorrow = c.getTime();      
	    return tomorrow;
	}
	/**
	 * 返回公共时间段
	 * @param ast 开始时间A
	 * @param aend 结束时间A
	 * @param bst 开始时间B
	 * @param bend 结束时间B
	 * @return
	 */
	public static Map<String,Date> getTwoStEnd(Date ast,Date aend,Date bst,Date bend){
		Map<String,Date> mp=new HashMap<String,Date>();
		if(aend==null && bend==null){//1.都无结束时间
			if(ast.before(bst))mp.put("start", bst);
			else mp.put("start", ast);
			mp.put("end", null);
		}else if(aend==null && bend!=null){//1.A无结束时间
			if(ast.before(bst))mp.put("start", bst);
			else mp.put("start", ast);
			mp.put("end", bend);
		}else if(aend!=null && bend==null){//1.B无结束时间
			if(ast.before(bst))mp.put("start", bst);
			else mp.put("start", ast);
			mp.put("end", aend);
		}else if(ast.equals(bst) && aend.equals(bend)){//AA=BB
			mp.put("start", ast);
			mp.put("end", aend);
		}else if(ast.before(bst) && aend.after(bend)){//AA<BA&&AB>BB
			mp.put("start", bst);
			mp.put("end", bend);
		}else if(ast.after(bst) && aend.before(bend)){//AA>BA&&AB<BB
			mp.put("start", ast);
			mp.put("end", aend);
		}else if(bst.before(ast) && aend.after(bend) && bend.before(ast)){//B夸AA点
			mp.put("start", ast);
			mp.put("end", bend);
		}else if(bst.after(ast) && aend.before(bend) && bst.before(aend)){//B夸AB点
			mp.put("start", bst);
			mp.put("end", aend);
		}
		return mp;
	}
	/**
	 * 返回传入类型获取时间
	 * @param style 类型yyyy|MM|dd HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String getZDTYPETIME(String style,Date date){
		SimpleDateFormat sm=new SimpleDateFormat(style);
		if(style.equals("MM") || style.equals("dd") || style.equals("HH") || style.equals("mm") || style.equals("ss")){
			return String.valueOf(Integer.parseInt(sm.format(date)));
		}
		return sm.format(date);
	}
	/**
	 * 返回当前系统时间MMDDHHmmss
	 * @return
	 */
	public static String getMMDDHHmmss(){
		String date=currentDatetime().replaceAll(" ","").replaceAll(":","").replaceAll("-","");
		return date.substring(4,date.length());
	}
	 /**得到二个日期间的间隔天数  
	  * @param maxTime
	  * @param minTime
	  * @return
	  */
	 public static Integer getTwoDay(String maxTime, String minTime) {
	     SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");   
	     Double day=0.0;   
	     try {
	      java.util.Date date = myFormatter.parse(maxTime);   
	      java.util.Date mydate = myFormatter.parse(minTime);   	     
	      day =Math.ceil((date.getTime() - mydate.getTime())/(24 * 60 * 60 * 1000));   
	     }catch(Exception e){   
	        return 0;   
	     }   
	     return day.intValue();   
	 }   
	 public static void TdisplayDayOfWeek(String dt){//测试使用单双周
		   Calendar c=Calendar.getInstance(); 
		   SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		   try {c.setTime(dateFormat.parse(dt));
		   } catch (ParseException e) {e.printStackTrace();} 	
		   int  week_num =c.get(Calendar.WEEK_OF_YEAR);
		   System.out.print("是第"+week_num+"周");
		   int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		   switch(dayOfWeek){
			   case 1:System.out.println("星期日!");break;
			   case 2:System.out.println("星期一!");break;
			   case 3:System.out.println("星期二!");break;
			   case 4:System.out.println("星期三!");break;
			   case 5:System.out.println("星期四!");break;
			   case 6:System.out.println("星期五!");break;
			   case 7:System.out.println("星期六!");break;
		   }
		   if(dayOfWeek==1){
			   week_num-=1;
		   }
		   System.out.println("正常工作周"+week_num);
	}
	/**
	* 获得当前日期
	* 日期格式yyyy-MM-dd 00:00:00
	* @return
	*/
	public static String currentDate_00_00(){
		return dateFormat.format(now())+" 00:00:00";
	}
	/**
	* 获得当前日期
	* 日期格式yyyy-MM-dd 23:59:59
	* @return
	*/
	public static String currentDate_23_59(){
		return dateFormat.format(now())+" 23:59:59";
	}
	public static void main(String[] args)throws Exception {	  
	    Calendar c = Calendar.getInstance();  
        c.setTime(DateUtils.parseDatetime("2018-03-01 15:50:16"));  
        c.add(Calendar.DAY_OF_MONTH,1);// 今天+1天     
        Date tomorrow = c.getTime();  
        String a=DateUtils.formatDatetime(tomorrow);
	    System.out.println(a);
	    Calendar d = Calendar.getInstance(); 
	    d.setTime(tomorrow);
	    d.add(Calendar.YEAR,1);
	    Date tomorrow2 = d.getTime();  
        String b=DateUtils.formatDatetime(tomorrow2);
	    System.out.println(b);
	    
	    Date now = new Date();
	    System.out.println("当前时间：" +DateUtils.formatDatetime(now));
	    long time = 10*60*1000;//30分钟
	    Date beforeDate = new Date(now .getTime() - time);//30分钟前的时间
	    System.out.println("当前时间：" +DateUtils.formatDatetime(beforeDate));
		   
	}
	
}

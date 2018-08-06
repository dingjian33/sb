package nc.bs.hzyb.plugins;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CalDate {
	
    private static Date[] getDates(int year,int month){  
    	ArrayList<Date> dates = new ArrayList<Date>();  
          
        Calendar cal = Calendar.getInstance();  
        cal.set(Calendar.YEAR, year);  
        cal.set(Calendar.MONTH,  month - 1);  
        cal.set(Calendar.DATE, 1);  
                  
        while(cal.get(Calendar.YEAR) == year &&   
                cal.get(Calendar.MONTH) < month){  
            int day = cal.get(Calendar.DAY_OF_WEEK);  
              
            if(!(day == Calendar.SUNDAY || day == Calendar.SATURDAY)){  
                dates.add((Date)cal.getTime().clone());  
            }  
            cal.add(Calendar.DATE, 1);  
        }  
        return dates.toArray(new Date[0]);   
    } 
    public String getLastWorkDay(String char10period){
    	
    	int year = Integer.parseInt(char10period.substring(0,4));
    	int mounth = Integer.parseInt(char10period.substring(5,7));
        Date[] dd = getDates(year,mounth);          
        return dd[dd.length-1].toString().substring(8, 10);  
    	
    }
}

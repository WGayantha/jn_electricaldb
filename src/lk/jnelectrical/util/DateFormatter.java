
package lk.jnelectrical.util;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DateFormatter {

    private DateFormatter(){}
    
    public static String formatDate(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
}

package petStoreBase.framework.utilities;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.time.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Utility {


    public static int getRandom(int l, int h) {
        Random rand = new Random();
        return rand.nextInt((h - l) + 1) + l;
    }
    public static String getFutureDate(int daysFromToday, String pattern){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, daysFromToday);
        Date tomorrow = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat.format(tomorrow);
    }

}




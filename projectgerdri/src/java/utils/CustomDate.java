package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author Adri
 */
public class CustomDate {
    
    public static String formatDateTimeToDb(LocalDateTime date) {
        String formattedDate = date.toString();
        formattedDate = formattedDate.replace("T", " ").substring(0, formattedDate.indexOf("."));
        return formattedDate;
    }
    
}
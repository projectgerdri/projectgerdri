package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author Adri
 */
public class CustomDate {
    
    //Del formato por defecto de LocalDateTime (YYYY-MM-DDThh:mm:ss.ddd) al usado en DB (YYYY-MM-DD hh:mm:ss)
    public static String fromJavaToDbFormat(LocalDateTime javaDate) {        
        String formattedDate = javaDate.toString();
        formattedDate = formattedDate.replace("T", " ").substring(0, formattedDate.indexOf("."));
        return formattedDate;
    }
    
    //Del formato ISO del Javascript (YYYY-MM-DDThh:mm:ss.dddZ) al por defecto de LocalDateTime (YYYY-MM-DDThh:mm:ss.ddd)
    public static LocalDateTime fromJsToJavaFormat(String jsDate, int diffTimeZone) {
        LocalDateTime formattedDate = LocalDateTime.parse(jsDate.replace("Z", ""));
        formattedDate = formattedDate.plusHours(diffTimeZone);
        return formattedDate;
    }
}
package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;

/** Clase de utilidad encargada de intercambiar formatos de fecha entre modelos, vista y controlador */
public class CustomDate {
    
    /**
     * Del formato Java establecido por la clase LocalDateTime (YYYY-MM-DDThh:mm:ss.ddd) al usado en DB por el tipo DATETIME (YYYY-MM-DD hh:mm:ss)
     * @param javaDate Fecha en formato LocalDateTime de Java
     * @return Devuelve un <b>String</b> capaz de ser interpretado por la DB como DATETIME
     */
    //Del formato por defecto de LocalDateTime (YYYY-MM-DDThh:mm:ss.ddd) al usado en DB (YYYY-MM-DD hh:mm:ss)
    public static String fromJavaToDbFormat(LocalDateTime javaDate) {        
        String formattedDate = javaDate.toString();
        formattedDate = formattedDate.replace("T", " ").substring(0, formattedDate.indexOf("."));
        return formattedDate;
    }
    
    /**
     * Del formato ISO de JavaScript retornado por la función "toISOString()" (YYYY-MM-DDThh:mm:ss.dddZ) al usado en Java por la clase LocalDateTime (YYYY-MM-DDThh:mm:ss.ddd)
     * @param jsDate Fecha en el formato ISO de JavaScript
     * @param diffTimeZone Entero con la diferencia horaria del usuario respecto a UTC+0
     * @return Devuelve un <b>LocalDateTime</b> con la conversión horaria a UTC+0 para ser manejado dentro de código Java
     */
    //Del formato ISO del Javascript (YYYY-MM-DDThh:mm:ss.dddZ) al por defecto de LocalDateTime (YYYY-MM-DDThh:mm:ss.ddd)
    public static LocalDateTime fromJsToJavaFormat(String jsDate, int diffTimeZone) {
        LocalDateTime formattedDate = LocalDateTime.parse(jsDate.replace("Z", ""));
        formattedDate = formattedDate.plusHours(diffTimeZone);
        return formattedDate;
    }
}
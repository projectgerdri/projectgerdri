package utils;
/**
 *
 * @author Adri
 */
public class DateFormats {

    private String date;

    public DateFormats (String date){ 
        this.date = date;                                                     
    }
    
    public String simpleDate (){//2018-03-03 --> YYYY-MM-DD
        String finalDate = "";
        
        return finalDate;
    }
    
    public String complexDate (){//2018-03-03 14:00:25 --> YYYY-MM-DD HH:MM:SS
       //2018-03-24
       String finalDate = "";
        
       return finalDate;
    }
    
    //getters and setters 
    public String getDate() {
        return date;
    }

    public void setDate(String username) {
        this.date = date;
    }
}
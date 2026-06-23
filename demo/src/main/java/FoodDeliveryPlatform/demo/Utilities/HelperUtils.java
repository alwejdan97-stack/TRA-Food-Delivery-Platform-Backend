package FoodDeliveryPlatform.demo.Utilities;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.UUID;

public class HelperUtils {

    public static String generateCode(String prefix){
        return generateCode(prefix,4);
    }

    public static String generateCode(String prefix, int length){
        String randomCode= UUID.randomUUID().toString().replace("-","").substring(0,length).toUpperCase();
        return prefix+"-"+randomCode;
    }

    public static Double calculateDistance(double lat1, double lng1, double lat2, double lng2){
        //convert degree to radians
        Double dlat=Math.toRadians(lat2-lat1);
        Double lng=Math.toRadians(lng2-lng1);

        //Raduius for earth in kilometers=6371
        double radius=6371;

        // Haversine formula
        Double a=(Math.pow(Math.sin(dlat/2),2)+Math.cos(lat1)*Math.cos(lat2)) * Math.pow(Math.sin(dlat/2),2);

        //calculate Central angle
        Double c=2* Math.asin(Math.sqrt(a));

        //calculate the result
        Double result=c*radius;

        return result;
    }

    public static Double calculateTotal(double subtotal, double fee){
        Double total=subtotal+fee;
        return total;
    }

    public static Double calculateTotal(double subtotal, double fee, double discount){
        Double total=subtotal+fee-discount;
        return total;
    }

    public static String formatCurrency(double amount){
        NumberFormat format=NumberFormat.getCurrencyInstance();
        //format.setCurrency(Currency.getInstance(currencyCode));
        return format.format(amount);
    }

    public static String formatCurrency(double amount, String currencyCode){
        NumberFormat format=NumberFormat.getCurrencyInstance();
        format.setCurrency(Currency.getInstance(currencyCode));
        return format.format(amount);
    }

    public static Boolean isBusinessOpen(String openTime, String closeTime){
        LocalDateTime now=LocalDateTime.now();
        LocalDateTime close=LocalDateTime.parse(openTime);
        LocalDateTime open=LocalDateTime.parse(openTime);

        if(!now.isBefore(open) && !now.isAfter(close)){
            return true;
        }
        return false;
    }
}

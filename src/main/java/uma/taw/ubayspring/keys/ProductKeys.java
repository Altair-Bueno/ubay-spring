package uma.taw.ubayspring.keys;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;

public class ProductKeys {
    public final static int productsPerPageLimit = 5;

    public static String localizedString(HttpServletRequest request, String key){
        Locale.setDefault(Locale.ENGLISH);
        ResourceBundle bundle = ResourceBundle.getBundle("messages", request.getLocale());
        return bundle.getString(key);
    }
}

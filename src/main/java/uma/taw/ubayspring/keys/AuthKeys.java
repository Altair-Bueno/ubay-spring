package uma.taw.ubayspring.keys;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import uma.taw.ubayspring.dto.LoginDTO;
import uma.taw.ubayspring.dto.products.ProductClientDTO;
import uma.taw.ubayspring.entity.ClientEntity;
import uma.taw.ubayspring.types.KindEnum;

/**
 * Multiple constant values shared between auth related servlets
 *
 * @author Altair Bueno
 */
public class AuthKeys {
    // From https://stackoverflow.com/a/1223146
    public final static String USERNAME_REGEX = "^.{3,20}$";
    //public final static String USERNAME_REGEX = "^[a-zA-Z0-9]+([a-zA-Z0-9](_|-| )[a-zA-Z0-9])*[a-zA-Z0-9]+$";

    public final static String PASSWORD_REGEX = ".{8,}";
    public final static String USERNAME_PARAMETER = "username";
    public final static String PASSWORD_PARAMETER = "password";
    public static final String NAME_PARAMETER = "name";
    public static final String LAST_NAME_PARAMETER = "lastName";
    public static final String ADDRESS_PARAMETER = "address";
    public static final String CITY_PARAMETER = "city";
    public static final String BIRTH_PARAMETER = "birthDate";
    public static final String GENDER_PARAMETER = "gender";
    public static final String OLD_PASSWORD_PARAMETER = "oldPassword";
    public static final String REPEAT_PASSWORD_PARAMETER = "repeatPassword";
    public static final String PASSWORD_CHANGE_ID_PARAMETER = "passwordChangeID";
    public static final int NAME_MAXLENGTH = 50;
    public static final int LAST_NAME_MAXLENGTH = 50;

    public static final int ADDRESS_MAXLENGTH = 100;

    public static final int CITY_MAXLENGTH = 100;
}

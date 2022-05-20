package uma.taw.ubayspring.types;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Altair Bueno
 */

public enum KindEnum implements GrantedAuthority {
    client, admin;

    @Override
    public String getAuthority() {
        return this.toString();
    }
}

package backend.secure;

import io.micronaut.security.authentication.*;
import java.util.Collection;

public class OpenUserDetails extends UserDetails {

    private Long userId;

    public OpenUserDetails(String username, Collection<String> roles) {
        super(username, roles);
    }


    public OpenUserDetails(String username, Collection<String> roles, Long userId) {
        super(username, roles);
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
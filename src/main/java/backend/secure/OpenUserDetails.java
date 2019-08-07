package backend.secure;

import io.micronaut.security.authentication.*;
import javax.annotation.Nullable;

import java.util.Collection;

import backend.model.User;

public class OpenUserDetails extends UserDetails {

    private User detail;

    public OpenUserDetails(String username, Collection<String> roles) {
        super(username, roles);
    }


    public OpenUserDetails(String username, Collection<String> roles, User detail) {
        super(username, roles);
        this.detail = detail;
    }

    public User getDetail() {
        return detail;
    }

    public void setDetail(User detail) {
        this.detail = detail;
    }
}
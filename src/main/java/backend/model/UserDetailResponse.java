package backend.model;

import java.util.ArrayList;
import java.util.List;

public class UserDetailResponse {
    public String status;
    public String message;
    public ArrayList<UserDetail> data;

    public UserDetailResponse(String status, String message, List<UserDetail> userDetail) {
        this.status = status;
        this.message = message;
        this.data = new ArrayList<UserDetail>();

        for (UserDetail item : userDetail) {
            this.data.add(item);
        }
    }

    public UserDetailResponse(String status, String message, UserDetail userDetail) {
        this.status = status;
        this.message = message;

        this.data = new ArrayList<UserDetail>();

        if (userDetail != null) {
            this.data.add(userDetail);
        }
    }

    public UserDetailResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
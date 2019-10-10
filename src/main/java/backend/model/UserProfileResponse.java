package backend.model;

import java.util.List;
import java.util.ArrayList;

public class UserProfileResponse {
    public String status;
    public String message;
    public ArrayList<UserProfile> data;

    public UserProfileResponse(String status, String message, List<UserProfile> userProfile) {
        this.status = status;
        this.message = message;
        this.data = new ArrayList<UserProfile>();

        for (UserProfile item : userProfile) {
            this.data.add(item);
        }
    }

    public UserProfileResponse(String status, String message, UserProfile userProfile) {
        this.status = status;
        this.message = message;

        this.data = new ArrayList<UserProfile>();

        if (userProfile != null) {
            this.data.add(userProfile);
        }
    }

    public UserProfileResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
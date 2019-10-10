package backend.response;

import java.util.ArrayList;
import java.util.List;

import backend.model.User;

/**
 * Author : supi.core@gmail.com | github.com/sup1core
 */

public class UserManagementResponse {

    public String status;
    public String message;
    public ArrayList<User> data;

    public UserManagementResponse (String status, String message, List<User> user){
        this.status= status;
        this.message=message;
        this.data = new ArrayList<User>();

        for (User item:user){
            this.data.add(item);
        }
    }

    public UserManagementResponse (String status, String message, User user){
        this.message = message;
        this.status = status;
        this.data = new ArrayList<User>();
        if (user !=null) {
            this.data.add(user);
        }
    }

    public UserManagementResponse (String status, String message){
        this.status = status;
        this.message = message;
    }
}
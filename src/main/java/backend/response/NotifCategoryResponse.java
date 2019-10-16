package backend.response;

import java.util.ArrayList;
import java.util.List;

import backend.model.NotifCategory;

/**
 * Author : supi.core@gmail.com | github.com/sup1core
 */

 public class NotifCategoryResponse {
    
    public String status;
    public String message;
    public ArrayList<NotifCategory> data;
    
    public NotifCategoryResponse (String status, String message, List <NotifCategory> notifCategory){
        this.status = status;
        this.message = message;
        this.data = new ArrayList<NotifCategory>();

        for (NotifCategory item:notifCategory){
            this.data.add(item);
        }
    }

    public NotifCategoryResponse (String status, String message, NotifCategory notifCategory) {
        this.status = status;
        this.message = message;
        this.data = new ArrayList<NotifCategory>();

        if (notifCategory != null) {
            this.data.add(notifCategory);
        }
    }

    public NotifCategoryResponse (String status, String message) {
        this.status = status;
        this.message = message;
    }
 }
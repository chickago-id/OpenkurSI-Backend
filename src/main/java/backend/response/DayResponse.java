package backend.response;

import java.util.ArrayList;
import java.util.List;

import backend.model.Day;

/**
 * Author : supi.core@gmail.com | github.com/sup1core
 */

 public class DayResponse {
    public String status;
    public String message;
    public ArrayList<Day> data;

    public DayResponse(String status, String message, List<Day> day){
        this.status = status;
        this.message = message;
        this.data = new ArrayList<Day>();

        for(Day item:day){
            this.data.add(item);
        }
    }

    public DayResponse (String status, String message, Day day){
        this.status = status;
        this.message = message;
        this.data = new ArrayList<Day>();
        if (day != null) {
            this.data.add(day);
        }
    }

    public DayResponse (String status, String message){
        this.message = message;
        this.status = status;
    }
 }
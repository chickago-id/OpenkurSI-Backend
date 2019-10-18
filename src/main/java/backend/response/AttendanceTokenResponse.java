package backend.response;

import java.util.List;

import backend.model.AttendanceToken;

import java.util.ArrayList;

/**
 * Author : akbar.lazuardi@yahoo.com | akbarlaz@github.com
 */

public class AttendanceTokenResponse {
    public String status;
    public String message;
    public ArrayList<AttendanceToken> data;

    public AttendanceTokenResponse(String status, String message, List<AttendanceToken> objAT) {
        this.status = status;
        this.message = message;
        this.data = new ArrayList<AttendanceToken>();

        for(AttendanceToken item:objAT) {
            this.data.add(item);
        }
    }

    public AttendanceTokenResponse(String status, String message, AttendanceToken objAT) {
        this.status = status;
        this.message = message;
        this.data = new ArrayList<AttendanceToken>();

        if(objAT != null) {
            this.data.add(objAT);   
        }
    }

    public AttendanceTokenResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
package backend.response;

import java.util.List;

import backend.model.Attendance;

import java.util.ArrayList;

/**
 * Author : akbar.lazuardi@yahoo.com | akbarlaz@github.com
 */

public class AttendanceResponse {
    public String status;
    public String message;
    public ArrayList<Attendance> data;

    public AttendanceResponse(String status, String message, List<Attendance> objAttendance) {
        this.status = status;
        this.message = message;
        this.data = new ArrayList<Attendance>();

        for(Attendance item:objAttendance) {
            this.data.add(item);
        }
    }

    public AttendanceResponse(String status, String message, Attendance objAttendance) {
        this.status = status;
        this.message = message;

        this.data = new ArrayList<Attendance>();

        if(objAttendance != null) {
            this.data.add(objAttendance);   
        }
    }

    public AttendanceResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
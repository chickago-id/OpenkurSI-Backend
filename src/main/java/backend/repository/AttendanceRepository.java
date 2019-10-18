package backend.repository;

import java.util.List;

import javax.validation.constraints.NotNull;

import backend.model.Attendance;
import backend.model.AttendanceToken;

/**
 * Author : akbar.lazuardi@yahoo.com | akbarlaz@github.com
 */

public interface AttendanceRepository {
   
    Attendance save(Attendance objAttendance);
    Attendance update(Long id, Attendance objAttendance);
    List<Attendance> findAll();
    Attendance findById(@NotNull Long id);
    void deleteById(@NotNull Long id);
    AttendanceToken findByToken(String token);
    Boolean existByUserIdAndToken(Long user_id, String token);
    
}
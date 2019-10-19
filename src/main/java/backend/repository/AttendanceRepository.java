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
    Boolean existByIdUserAndToken(Long id_user, String token);
    List<Attendance> findByIdKelasAndIdUser(Long id_kelas, Long id_user);
    List<Attendance> findByIdKelasAndToken(Long id_kelas, String token);
}
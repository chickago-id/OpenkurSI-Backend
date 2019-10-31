package backend.repository;

import java.util.List;

import javax.validation.constraints.NotNull;

import backend.model.Attendance;
import backend.model.AttendanceToken;
import backend.model.KelasPeserta;

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
    List<Attendance> findByIdJadwalAndIdUser(Integer id_jadwal, Long id_user);
    List<Attendance> findByIdKelasAndToken(Long id_kelas, String token);
    List<Object> findByIdUser(Long id_user);
    List<Object> findByIdJadwal(Integer id_jadwal);
    Attendance findByIdJadwalAndIdUserAndStatus(Integer id_jadwal, Long id_user);
    List<KelasPeserta> findByIdKelas(Long id_kelas);
    
}
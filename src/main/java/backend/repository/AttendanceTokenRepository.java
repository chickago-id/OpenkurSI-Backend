package backend.repository;

import backend.model.AttendanceToken;
import backend.model.Jadwal;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Author : akbar.lazuardi@yahoo.com | akbarlaz@github.com
 */

public interface AttendanceTokenRepository {
   
    AttendanceToken save(AttendanceToken objAT);
    AttendanceToken update(Long id, AttendanceToken objAT);
    List<AttendanceToken> findAll();
    AttendanceToken findById(@NotNull Long id);
    void deleteById(@NotNull Long id);
    AttendanceToken findByToken(String token);
    List<Jadwal> findByIdPengajar(Long id_pengajar);
    List<Jadwal> findByIdKelas(Long id_kelas);
    Jadwal findByIdKelasAndIdMateri(Long id_kelas, Integer id_materi);
}
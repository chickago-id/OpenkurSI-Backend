package backend.repository;

import backend.model.AttendanceToken;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Author : akbar.lazuardi@yahoo.com | akbarlaz@github.com
 */

public interface AttendanceTokenRepository {
   
    AttendanceToken save(AttendanceToken objAT);
    AttendanceToken update(Integer id, AttendanceToken objAT);
    List<AttendanceToken> findAll();
    AttendanceToken findById(@NotNull Integer id);
    void deleteById(@NotNull Integer id);
    AttendanceToken findByToken(String token);
    
}
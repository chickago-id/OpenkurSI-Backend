package backend.repository;

import backend.model.PresenceType;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Author : akbar.lazuardi@yahoo.com | akbarlaz@github.com
 */

public interface PresenceTypeRepository {
   
    PresenceType save(PresenceType presenceType);
    PresenceType update(Integer id, PresenceType presenceType);
    List<PresenceType> findAll();
    PresenceType findById(@NotNull Integer id);
    void deleteById(@NotNull Integer id);
    
}
package backend.repository;

import backend.model.PresenceStatus;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Author : akbar.lazuardi@yahoo.com | akbarlaz@github.com
 */

public interface PresenceStatusRepository {
   
    PresenceStatus save(PresenceStatus objPS);
    PresenceStatus update(Integer id, PresenceStatus objPS);
    List<PresenceStatus> findAll();
    PresenceStatus findById(@NotNull Integer id);
    void deleteById(@NotNull Integer id);
    
}
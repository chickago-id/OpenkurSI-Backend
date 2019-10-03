package backend.repository;

import backend.model.AccessLevel;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Author : akbar.lazuardi@yahoo.com | akbarlaz@github.com
 */

public interface AccessLevelRepository {
   
    AccessLevel save(AccessLevel AccessLevel);
    AccessLevel update(Integer id, AccessLevel AccessLevel);
    List<AccessLevel> findAll();
    AccessLevel findById(@NotNull Integer id);
    void deleteById(@NotNull Integer id);
    
}
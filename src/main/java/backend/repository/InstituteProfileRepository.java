package backend.repository;

import backend.model.InstituteProfile;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Author : akbar.lazuardi@yahoo.com | akbarlaz@github.com
 */

public interface InstituteProfileRepository {
    InstituteProfile save(@NotNull InstituteProfile objIP);
    InstituteProfile update(Integer id, InstituteProfile objIP);
    List<InstituteProfile> findAll();
    InstituteProfile findById(@NotNull Integer id);
    void deleteById(@NotNull Integer id);
}
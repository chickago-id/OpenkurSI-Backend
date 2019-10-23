package backend.repository;

import java.util.List;

import javax.validation.constraints.NotNull;

import backend.model.KelasType;

/**
 * Author : supi.core@gmail.com | github.com/sup1core
 */

 public interface KelasTypeRepository {
 
     KelasType save(KelasType kelasType);
     KelasType update(Integer id, KelasType kelasType);
     List<KelasType> findAll();
     KelasType findById(@NotNull Integer id);
     void deleteById(@NotNull Integer id);
 }
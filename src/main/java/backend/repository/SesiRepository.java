package backend.repository;

import java.util.List;

import javax.validation.constraints.NotNull;

import backend.model.Sesi;

/**
 * Author : supi.core@gmail.com | github.com/sup1core  
 */

 public interface SesiRepository{
     Sesi save(Sesi sesi);
     Sesi update(Integer id, Sesi sesi);

     List<Sesi> findAll();

     Sesi findById(@NotNull Integer id);
     void deleteById(@NotNull Integer id);
 }
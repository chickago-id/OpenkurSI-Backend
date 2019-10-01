package backend.repository;

import java.util.List;

import javax.validation.constraints.NotNull;

import backend.model.Sesi;

/**
 * Author : supi.core@gmail.com
 */

 public interface SesiRepository{
     Sesi save(@NotNull Sesi sesi);
     Sesi update(Integer id, @NotNull Sesi sesi);

     List<Sesi> findAll();

     Sesi findById(@NotNull Integer id);
     void deleteById(@NotNull Integer id);
 }
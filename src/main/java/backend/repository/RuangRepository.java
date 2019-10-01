package backend.repository;

import java.util.List;

import javax.validation.constraints.NotNull;

import backend.model.Ruang;

/**
 * Author : supi.core@gmail.com
 */
public interface RuangRepository {
    Ruang save(@NotNull Ruang ruang);
    Ruang update(Integer id, @NotNull Ruang ruang);
    List<Ruang> findAll();
    Ruang findById(@NotNull Integer id);
    void deleteById(@NotNull Integer id);
    
}
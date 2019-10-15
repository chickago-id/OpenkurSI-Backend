package backend.repository;

import javax.validation.constraints.NotNull;

import backend.model.Jadwal;

import java.util.List;

/**
 * Author : supi.core@gmail.com | github.com/sup1core
 */

public interface JadwalRepository {
    Jadwal save(Jadwal jadwal);
    Jadwal update(Integer id, Jadwal jadwal);
    List<Jadwal> findAll();
    Jadwal findById(@NotNull Integer id);
    void deleteById(@NotNull Integer id);
}
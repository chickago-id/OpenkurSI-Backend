package backend.repository;

import backend.model.*;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface JadwalRepository {
    // Materi save(@NotNull String kodeMateri, @NotNull String namaMateri);
    Jadwal save(@NotNull Jadwal jadwal);
    Jadwal update(@NotNull Jadwal jadwal);
    List<Jadwal> findAll();
    Jadwal findById(@NotNull Long id);
    void deleteById(@NotNull Long id);
}
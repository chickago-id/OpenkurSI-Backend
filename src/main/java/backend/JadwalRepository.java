package backend;

import backend.model.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface JadwalRepository {
    // Materi save(@NotNull String kodeMateri, @NotNull String namaMateri);
    Jadwal save(@NotNull Jadwal jadwal);
    Jadwal update(@NotNull Jadwal jadwal);
    List<Jadwal> findAll();
    Jadwal findById(@NotNull Long id);
    void deleteById(@NotNull Long id);
}
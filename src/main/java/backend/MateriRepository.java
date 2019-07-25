package backend;

import backend.model.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface MateriRepository {
    // Materi save(@NotNull String kodeMateri, @NotNull String namaMateri);
    Materi save(@NotNull Materi materi);
    Materi update(@NotNull Materi materi);
    List<Materi> findAll();
    Materi findById(@NotNull Long id);
    void deleteById(@NotNull Long id);
}
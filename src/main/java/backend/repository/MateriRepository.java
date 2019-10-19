package backend.repository;

import backend.model.Materi;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface MateriRepository {
    // Materi save(@NotNull String kodeMateri, @NotNull String namaMateri);
    Materi save(Materi materi);
    Materi update(Integer id, Materi materi);
    List<Materi> findAll();
    Materi findById(@NotNull Integer id);
    void deleteById(@NotNull Integer id);
}
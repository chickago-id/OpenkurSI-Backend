package backend;

import backend.model.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface MateriRepository {
    Materi save(@NotNull String kodeMateri, String namaMateri);
    Materi update(@NotNull Materi materi);
    List<Materi> findAll();
    Optional<Materi> findById(@NotNull Long id);
}
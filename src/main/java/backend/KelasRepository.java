package backend;

import backend.model.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface KelasRepository {
   
    Kelas save(@NotNull Kelas kelas);
    Kelas update(@NotNull Kelas kelas);
    List<Kelas> findAll();
    Kelas findById(@NotNull Long id);
    void deleteById(@NotNull Long id);
}
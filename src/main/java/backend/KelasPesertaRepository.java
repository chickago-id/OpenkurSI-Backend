package backend;

import backend.model.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface KelasPesertaRepository {
    KelasPeserta save(@NotNull KelasPeserta kelaspeserta);
    KelasPeserta update(@NotNull KelasPeserta kelaspeserta);
    List<KelasPeserta> findAll();
    KelasPeserta findById(@NotNull Long id);
    void deleteById(@NotNull Long id);
}
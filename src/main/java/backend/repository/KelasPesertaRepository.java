package backend.repository;

import backend.model.KelasPeserta;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface KelasPesertaRepository {
    KelasPeserta save(@NotNull KelasPeserta kelaspeserta);
    KelasPeserta update(@NotNull KelasPeserta kelaspeserta);
    List<KelasPeserta> findAll();
    KelasPeserta findById(@NotNull Long id);
    void deleteById(@NotNull Long id);
}
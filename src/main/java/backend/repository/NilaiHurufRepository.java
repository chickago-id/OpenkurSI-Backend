package backend.repository;

import java.util.List;

import javax.validation.constraints.NotNull;

import backend.model.*;

/**
 * Author : supi.core@gmail.com
 */

public interface NilaiHurufRepository{
    NilaiHuruf save(@NotNull NilaiHuruf nilaihuruf);
    NilaiHuruf update(Long id, @NotNull NilaiHuruf nilaihuruf);

    List<NilaiHuruf> findAll();
    NilaiHuruf findById(@NotNull Long id);
    void deleteById(@NotNull Long id);
}

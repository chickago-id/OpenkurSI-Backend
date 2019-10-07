package backend.repository;

import java.util.List;

import javax.validation.constraints.NotNull;

import backend.model.*;

/**
 * Author : supi.core@gmail.com | github.com/sup1core  
 */

public interface NilaiHurufRepository{
    NilaiHuruf save(NilaiHuruf nilaiHuruf);
    NilaiHuruf update(Long id, NilaiHuruf nilaiHuruf);

    List<NilaiHuruf> findAll();
    NilaiHuruf findById(@NotNull Long id);
    void deleteById(@NotNull Long id);
}

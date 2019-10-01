package backend.repository;

import java.util.List;

import javax.validation.constraints.NotNull;

import backend.model.NilaiAkhir;

/*
Author : supi.core@gmail.com
*/

public interface NilaiAkhirRepository{

    NilaiAkhir save(NilaiAkhir nilaiAkhir);
    NilaiAkhir update (Long id, NilaiAkhir nilaiAkhir);
    List<NilaiAkhir> findAll();
    NilaiAkhir findById(@NotNull Long id);
    void deleteById(@NotNull Long id);
}
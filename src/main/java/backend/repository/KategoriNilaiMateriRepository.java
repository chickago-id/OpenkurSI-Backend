package backend.repository;

import backend.model.KategoriNilaiMateri;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Author : akbar.lazuardi@yahoo.com | akbarlaz@github.com
 */

public interface KategoriNilaiMateriRepository {
   
    KategoriNilaiMateri save(KategoriNilaiMateri KategoriNilaiMateri);
    KategoriNilaiMateri update(Long id, KategoriNilaiMateri KategoriNilaiMateri);
    List<KategoriNilaiMateri> findAll();
    KategoriNilaiMateri findById(@NotNull Long id);
    void deleteById(@NotNull Long id);
    
}
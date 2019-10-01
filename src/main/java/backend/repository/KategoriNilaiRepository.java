package backend.repository;

import backend.model.KategoriNilai;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Author : akbar.lazuardi@yahoo.com | akbarlaz@github.com
 */

public interface KategoriNilaiRepository {
   
    KategoriNilai save(KategoriNilai kategoriNilai);
    KategoriNilai update(Integer id, KategoriNilai kategoriNilai);
    List<KategoriNilai> findAll();
    KategoriNilai findById(@NotNull Integer id);
    void deleteById(@NotNull Integer id);
    
}
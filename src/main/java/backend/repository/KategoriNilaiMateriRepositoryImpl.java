package backend.repository;

import java.util.List;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import backend.model.KategoriNilaiMateri;
import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;

/**
 * Author : akbar.lazuardi@yahoo.com | akbarlaz@github.com
 */

@Singleton
public class KategoriNilaiMateriRepositoryImpl implements KategoriNilaiMateriRepository{
    @PersistenceContext
    private EntityManager entityManager;

    public KategoriNilaiMateriRepositoryImpl(@CurrentSession EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public KategoriNilaiMateri save(KategoriNilaiMateri KategoriNilaiMateri) {
        entityManager.persist(KategoriNilaiMateri);
        return KategoriNilaiMateri;
    }

    @Override
    @Transactional
    public KategoriNilaiMateri update(Long id, KategoriNilaiMateri KategoriNilaiMateri) {
        entityManager.merge(KategoriNilaiMateri);
        return KategoriNilaiMateri;

    }

    @Override
    @Transactional(readOnly = true)
    public List<KategoriNilaiMateri> findAll() {
        String qlString = "SELECT k FROM KategoriNilaiMateri k";
        TypedQuery<KategoriNilaiMateri> query = entityManager.createQuery(qlString, KategoriNilaiMateri.class); 
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<KategoriNilaiMateri> findByIdMateri(Integer id_materi) {
        String qlString = "SELECT a FROM KategoriNilaiMateri a where id_materi = :id_materi";
        TypedQuery<KategoriNilaiMateri> query = entityManager.createQuery(
            qlString, 
            KategoriNilaiMateri.class
        ).setParameter("id_materi", id_materi);
        return query.getResultList(); 
    }

    @Override
    @Transactional(readOnly = true)
    public KategoriNilaiMateri findByIdMateriAndIdKategoriNilai(Integer id_materi, Integer id_kategori_nilai) {
        String qlString = "SELECT a FROM KategoriNilaiMateri a where id_materi = :id_materi " + 
                            "and id_kategori_nilai = :id_kategori_nilai";
        TypedQuery<KategoriNilaiMateri> query = entityManager.createQuery(
            qlString, 
            KategoriNilaiMateri.class
        ).setParameter("id_materi", id_materi)
        .setParameter("id_kategori_nilai", id_kategori_nilai);
        return query.getSingleResult(); 
    }

    @Override
    @Transactional(readOnly = true)
    public KategoriNilaiMateri findById(@NotNull Long id) {        
        return entityManager.find(KategoriNilaiMateri.class, id);
    }

    @Override
    @Transactional
    public void deleteById(@NotNull Long id) {
        KategoriNilaiMateri KategoriNilaiMateri = findById(id);
        if(KategoriNilaiMateri != null) {
            entityManager.remove(KategoriNilaiMateri);
        }
    }

    @Override
    @Transactional
    public Boolean existsByIdMateriAndIdKategoriNilai(Integer id_materi, Integer id_kategori_nilai) {
        String qlString = "select a from KategoriNilaiMateri a where a.id_materi = :id_materi " +
            "and a.id_kategori_nilai = :id_kategori_nilai";
        TypedQuery<KategoriNilaiMateri> query = entityManager.createQuery(
            qlString,
            KategoriNilaiMateri.class
            ).setParameter("id_materi", id_materi)
            .setParameter("id_kategori_nilai", id_kategori_nilai)
            .setMaxResults(1);
        return query.getResultList().isEmpty();
    }

}

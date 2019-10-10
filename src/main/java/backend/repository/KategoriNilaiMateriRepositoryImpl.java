package backend.repository;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import java.util.List;

import backend.model.KategoriNilaiMateri;

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

        /* Integer idKN = KategoriNilaiMateri.getIdKategoriNilaiMateri();
        String nama_kategori = KategoriNilaiMateri.getNamaKategori();
        Float bobot_nilai = KategoriNilaiMateri.getBobotNilai();
        Long id_materi = KategoriNilaiMateri.getMateri().getId();
        
        String qlString = "UPDATE KategoriNilaiMateri kn SET id_kategori_nilai = :idKN, bobot_nilai = :bobot_nilai, "+
        "nama_kategori = :nama_kategori, id_materi = :id_materi where id = :id";
                return entityManager.createQuery(qlString)
                .setParameter("idKN", idKN)
                .setParameter("bobot_nilai", bobot_nilai)
                .setParameter("nama_kategori", nama_kategori)
                .setParameter("id_materi", id_materi)
                .setParameter("id", id)
                .executeUpdate();
         */     


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

    /* @Override
    @Transactional(readOnly = true)
    public Stream<KategoriNilaiMateri> coba() {
        String qlString = "SELECT k, SUM(bobot_nilai) FROM KategoriNilaiMateri k";
        TypedQuery<KategoriNilaiMateri> query = entityManager.createQuery(qlString, KategoriNilaiMateri.class);
        return query.getResultStream();
        
    } */

    @Override
    @Transactional(readOnly = true)
    public List<KategoriNilaiMateri> findByIdMateri(Integer id_materi) {
        String qlString = "SELECT a FROM KategoriNilaiMateri a where id_materi = " + id_materi;
        TypedQuery<KategoriNilaiMateri> query = entityManager.createQuery(qlString, KategoriNilaiMateri.class);
        return query.getResultList(); 
    }

    @Override
    @Transactional(readOnly = true)
    public KategoriNilaiMateri findByIdMateriAndIdKategoriNilai(Integer id_materi, Long id_kategori_nilai) {
        String qlString = "SELECT a FROM KategoriNilaiMateri a where id_materi = " + id_materi + 
                            "and id_kategori_nilai = "+ id_kategori_nilai;
        TypedQuery<KategoriNilaiMateri> query = entityManager.createQuery(qlString, KategoriNilaiMateri.class);
        return query.getSingleResult(); 
    }

    /* @Override
    public KategoriNilaiMateri sumOfMateri(Long id_materi) {
        String qlString = "SELECT sum(bobot_nilai) FROM KategoriNilaiMateri a where id_materi = " + id_materi;
        Query query = entityManager.createQuery(qlString, KategoriNilaiMateri.class);
        return (KategoriNilaiMateri) query.getSingleResult();
    } */

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



}

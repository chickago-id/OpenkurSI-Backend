package backend;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import java.util.List;

import backend.model.KategoriNilai;

@Singleton
public class KategoriNilaiRepositoryImpl implements KategoriNilaiRepository{
    @PersistenceContext
    private EntityManager entityManager;

    public KategoriNilaiRepositoryImpl(@CurrentSession EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public KategoriNilai save(KategoriNilai kategoriNilai) {
        
        entityManager.persist(kategoriNilai);
        return kategoriNilai;
    }

    @Override
    @Transactional
    public KategoriNilai update(Integer id, KategoriNilai kategoriNilai) {

        /* Integer idKN = kategoriNilai.getIdKategoriNilai();
        String nama_kategori = kategoriNilai.getNamaKategori();
        Float bobot_nilai = kategoriNilai.getBobotNilai();
        Long id_materi = kategoriNilai.getMateri().getId();
        
        String qlString = "UPDATE KategoriNilai kn SET id_kategori_nilai = :idKN, bobot_nilai = :bobot_nilai, "+
        "nama_kategori = :nama_kategori, id_materi = :id_materi where id = :id";
                return entityManager.createQuery(qlString)
                .setParameter("idKN", idKN)
                .setParameter("bobot_nilai", bobot_nilai)
                .setParameter("nama_kategori", nama_kategori)
                .setParameter("id_materi", id_materi)
                .setParameter("id", id)
                .executeUpdate();
         */     


        entityManager.merge(kategoriNilai);

         return kategoriNilai;

    }

    @Override
    @Transactional(readOnly = true)
    public List<KategoriNilai> findAll() {
        String qlString = "SELECT k FROM KategoriNilai k";
        TypedQuery<KategoriNilai> query = entityManager.createQuery(qlString, KategoriNilai.class);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public KategoriNilai findById(@NotNull Integer id) {
        
        return entityManager.find(KategoriNilai.class, id);
    }

    @Override
    @Transactional
    public void deleteById(@NotNull Integer id) {
        KategoriNilai kategoriNilai = findById(id);

        if(kategoriNilai != null) {
            entityManager.remove(kategoriNilai);
        }
    }



}

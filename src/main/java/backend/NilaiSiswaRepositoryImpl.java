package backend;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.util.List;

import backend.model.NilaiSiswa;

@Singleton
public class NilaiSiswaRepositoryImpl implements NilaiSiswaRepository{
    @PersistenceContext
    private EntityManager entityManager;

    public NilaiSiswaRepositoryImpl(@CurrentSession EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public NilaiSiswa save(NilaiSiswa nilaiSiswa) {
        entityManager.persist(nilaiSiswa);
        return nilaiSiswa;
    }

    @Override
    @Transactional
    public NilaiSiswa update(Integer id, NilaiSiswa nilaiSiswa) {
        
        entityManager.merge(nilaiSiswa);
        return nilaiSiswa;
    }

    @Override
    @Transactional(readOnly = true)
    public List<NilaiSiswa> findAll() {
        String qlString = "SELECT k FROM NilaiSiswa k";
        TypedQuery<NilaiSiswa> query = entityManager.createQuery(qlString, NilaiSiswa.class);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public NilaiSiswa findById(@NotNull Integer id) {
        return entityManager.find(NilaiSiswa.class, id);
    }

    @Override
    @Transactional
    public void deleteById(@NotNull Integer id) {
        NilaiSiswa NilaiSiswa = findById(id);

        if(NilaiSiswa != null) {
            entityManager.remove(NilaiSiswa);
        }
    }
}

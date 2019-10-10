package backend.repository;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.util.List;
import backend.model.MasterKelas;

@Singleton
public class MasterKelasRepositoryImpl implements MasterKelasRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public MasterKelasRepositoryImpl(@CurrentSession EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void deleteById(@NotNull String kode_kelas) {
        MasterKelas masterKelas = findById(kode_kelas);
        if (masterKelas != null)

        {
            entityManager.remove(masterKelas);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<MasterKelas> findAll() {
        String qlString = "SELECT m FROM MasterKelas m";
        TypedQuery<MasterKelas> query = entityManager.createQuery(qlString, MasterKelas.class);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public MasterKelas findById(@NotNull String kode_kelas) {
        return entityManager.find(MasterKelas.class, kode_kelas);
    }

    @Override
    @Transactional
    public MasterKelas save(@NotNull MasterKelas masterKelas) {
        entityManager.persist(masterKelas);
        return masterKelas;
    }

    @Override
    @Transactional
    public MasterKelas update(@NotNull MasterKelas masterKelas) {
        entityManager.merge(masterKelas);
        return masterKelas;
    }
}

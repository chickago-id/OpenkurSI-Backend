package backend.repository;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.util.List;

import backend.model.Batch;
import backend.model.Kelas;
import backend.model.KelasType;
import backend.model.MasterKelas;

@Singleton
public class KelasRepositoryImpl implements KelasRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public KelasRepositoryImpl(@CurrentSession EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Kelas save(@NotNull Kelas kelas) {
        entityManager.persist(kelas);
        return kelas;
    }

    @Override
    @Transactional
    public Kelas update(Integer id, @NotNull Kelas kelas) {
        return entityManager.merge(kelas);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Kelas> findAll() {
        String qlString = "SELECT m FROM Kelas m";
        TypedQuery<Kelas> query = entityManager.createQuery(qlString, Kelas.class);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Kelas findById(@NotNull Long id) {
        return entityManager.find(Kelas.class, id);
    }

    @Override
    @Transactional
    public void deleteById(@NotNull Long id) {
        Kelas kelas = findById(id);

        if (kelas != null) {
            entityManager.remove(kelas);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public MasterKelas findById(Integer id) {
        return entityManager.find(MasterKelas.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public KelasType cariId(Integer id) {
        return entityManager.find(KelasType.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Batch cariBatchid(Long id_batch) {
        return entityManager.find(Batch.class, id_batch);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Kelas> enRoll() {
        String qlString = "SELECT m FROM Kelas m where status=1";
        TypedQuery<Kelas> query = entityManager.createQuery(qlString, Kelas.class);
        return query.getResultList();
    }
}

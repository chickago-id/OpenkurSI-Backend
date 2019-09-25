package backend;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

import backend.model.Batch;

@Singleton
public class BatchRepositoryImpl implements BatchRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public BatchRepositoryImpl(@CurrentSession EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Batch save(@NotNull Batch batch) {
        entityManager.persist(batch);
        return batch;
    }

    @Override
    @Transactional
    public Batch update(@NotNull Batch batch) {
        return entityManager.merge(batch);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Batch> findAll() {
        String qlString = "SELECT b FROM Batch b";
        TypedQuery<Batch> query = entityManager.createQuery(qlString, Batch.class);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Batch findById(@NotNull Long id_batch) {
        return entityManager.find(Batch.class, id_batch);
    }

    @Override
    @Transactional
    public void deleteById(@NotNull Long id_batch) {
        Batch batch = findById(id_batch);

        if (batch != null) {
            entityManager.remove(batch);
        }
    }
}

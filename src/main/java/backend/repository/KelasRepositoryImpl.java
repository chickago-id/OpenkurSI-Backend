package backend.repository;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.util.List;
import backend.model.Kelas;

@Singleton
public class KelasRepositoryImpl implements KelasRepository{
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
    public Kelas update(@NotNull Kelas kelas) {
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

        if(kelas != null) {
            entityManager.remove(kelas);
        }
    }
}

package backend.repository;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.util.List;
import backend.model.Jadwal;
import backend.repository.JadwalRepository;

/**
 * Author : supi.core@gmail.com | github.com/sup1core
 */

@Singleton
public class JadwalRepositoryImpl implements JadwalRepository{
    @PersistenceContext
    private EntityManager entityManager;

    public JadwalRepositoryImpl(@CurrentSession EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Jadwal save(Jadwal jadwal) {
        entityManager.persist(jadwal);
        return jadwal;
    }

    @Override
    @Transactional
    public Jadwal update(Integer id, Jadwal jadwal) {
        return entityManager.merge(jadwal);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Jadwal> findAll() {
        String qlString = "SELECT j FROM Jadwal j";
        TypedQuery<Jadwal> query = entityManager.createQuery(qlString, Jadwal.class);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Jadwal findById(@NotNull Integer id) {
        return entityManager.find(Jadwal.class, id);
    }

    @Override
    @Transactional
    public void deleteById(@NotNull Integer id) {
        Jadwal jadwal = findById(id);

        if(jadwal != null) {
            entityManager.remove(jadwal);
        }
    }
}

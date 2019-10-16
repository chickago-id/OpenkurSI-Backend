package backend.repository;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import java.util.List;

import backend.model.PresenceStatus;

/**
 * Author : akbar.lazuardi@yahoo.com | akbarlaz@github.com
 */

@Singleton
public class PresenceStatusRepositoryImpl implements PresenceStatusRepository{
    @PersistenceContext
    private EntityManager entityManager;
    public PresenceStatusRepositoryImpl(@CurrentSession EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public PresenceStatus save(PresenceStatus objPS) {
        entityManager.persist(objPS);
        return objPS;
    }

    @Override
    @Transactional
    public PresenceStatus update(Integer id, PresenceStatus objPS) {
        entityManager.merge(objPS);
         return objPS;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PresenceStatus> findAll() {
        String qlString = "SELECT a FROM PresenceStatus a";
        TypedQuery<PresenceStatus> query = entityManager.createQuery(qlString, PresenceStatus.class);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public PresenceStatus findById(@NotNull Integer id) {
        return entityManager.find(PresenceStatus.class, id);
    }

    @Override
    @Transactional
    public void deleteById(@NotNull Integer id) {
        PresenceStatus PresenceStatus = findById(id);
        if(PresenceStatus != null) {
            entityManager.remove(PresenceStatus);
        }
    }
}

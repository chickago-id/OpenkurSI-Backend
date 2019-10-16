package backend.repository;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import java.util.List;

import backend.model.PresenceType;

/**
 * Author : akbar.lazuardi@yahoo.com | akbarlaz@github.com
 */

@Singleton
public class PresenceTypeRepositoryImpl implements PresenceTypeRepository{
    @PersistenceContext
    private EntityManager entityManager;
    public PresenceTypeRepositoryImpl(@CurrentSession EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public PresenceType save(PresenceType presenceType) {
        entityManager.persist(presenceType);
        return presenceType;
    }

    @Override
    @Transactional
    public PresenceType update(Integer id, PresenceType presenceType) {
        entityManager.merge(presenceType);
         return presenceType;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PresenceType> findAll() {
        String qlString = "SELECT a FROM PresenceType a";
        TypedQuery<PresenceType> query = entityManager.createQuery(qlString, PresenceType.class);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public PresenceType findById(@NotNull Integer id) {
        return entityManager.find(PresenceType.class, id);
    }

    @Override
    @Transactional
    public void deleteById(@NotNull Integer id) {
        PresenceType PresenceType = findById(id);
        if(PresenceType != null) {
            entityManager.remove(PresenceType);
        }
    }
}

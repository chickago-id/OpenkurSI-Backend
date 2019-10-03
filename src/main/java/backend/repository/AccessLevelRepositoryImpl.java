package backend.repository;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import java.util.List;

import backend.model.AccessLevel;

/**
 * Author : akbar.lazuardi@yahoo.com | akbarlaz@github.com
 */

@Singleton
public class AccessLevelRepositoryImpl implements AccessLevelRepository{
    @PersistenceContext
    private EntityManager entityManager;

    public AccessLevelRepositoryImpl(@CurrentSession EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public AccessLevel save(AccessLevel accessLevel) {
        
        entityManager.persist(accessLevel);
        return accessLevel;
    }

    @Override
    @Transactional
    public AccessLevel update(Integer id, AccessLevel accessLevel) {

        entityManager.merge(accessLevel);

         return accessLevel;

    }

    @Override
    @Transactional(readOnly = true)
    public List<AccessLevel> findAll() {
        String qlString = "SELECT k FROM AccessLevel k";
        TypedQuery<AccessLevel> query = entityManager.createQuery(qlString, AccessLevel.class);
        
        return query.getResultList();
        
    }

    @Override
    @Transactional(readOnly = true)
    public AccessLevel findById(@NotNull Integer id) {
        
        return entityManager.find(AccessLevel.class, id);
    }

    @Override
    @Transactional
    public void deleteById(@NotNull Integer id) {
        AccessLevel AccessLevel = findById(id);

        if(AccessLevel != null) {
            entityManager.remove(AccessLevel);
        }
    }



}

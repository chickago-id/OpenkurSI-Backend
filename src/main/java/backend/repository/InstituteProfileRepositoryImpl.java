package backend.repository;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import java.util.List;

import backend.model.InstituteProfile;

/**
 * Author : akbar.lazuardi@yahoo.com | akbarlaz@github.com
 */

@Singleton
public class InstituteProfileRepositoryImpl implements InstituteProfileRepository{
    @PersistenceContext
    private EntityManager entityManager;
    public InstituteProfileRepositoryImpl(@CurrentSession EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public InstituteProfile save(InstituteProfile objIP) {
        entityManager.persist(objIP);
        return objIP;
    }

    @Override
    @Transactional
    public InstituteProfile update(Integer id, InstituteProfile objIP) {
        entityManager.merge(objIP);
        return objIP;
    }

    @Override
    @Transactional(readOnly = true)
    public List<InstituteProfile> findAll() {
        String qlString = "SELECT k FROM InstituteProfile k";
        TypedQuery<InstituteProfile> query = entityManager.createQuery(qlString, InstituteProfile.class);        
        return query.getResultList();        
    }

    @Override
    @Transactional(readOnly = true)
    public InstituteProfile findById(@NotNull Integer id) {
        return entityManager.find(InstituteProfile.class, id);
    }

    @Override
    @Transactional
    public void deleteById(@NotNull Integer id) {
        InstituteProfile objIP = findById(id);
        if(objIP != null) {
            entityManager.remove(objIP);
        }
    }
}

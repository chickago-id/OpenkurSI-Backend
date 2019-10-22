package backend.repository;

import java.util.List;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import backend.model.NotifCategory;
import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;

/**
 * Author : supi.core@gmail.com | github.com/sup1core
 */

 @Singleton
 
 public class NotifCategoryRepositoryImpl implements NotifCategoryRepository {
     
    @PersistenceContext
    private EntityManager entityManager;

    public NotifCategoryRepositoryImpl (@CurrentSession EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public NotifCategory save(NotifCategory notifCategory) {
        entityManager.persist(notifCategory);
        return notifCategory;
    }

    @Override
    @Transactional
    public NotifCategory update(Integer id, NotifCategory notifCategory) {
        entityManager.merge(notifCategory);
        return notifCategory;
    }

    @Override
    @Transactional (readOnly = true)
    public List<NotifCategory> findAll() {
        String qlString = "SELECT c FROM NotifCategory c";
        TypedQuery<NotifCategory> query = entityManager.createQuery(qlString, NotifCategory.class);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public NotifCategory findById(@NotNull Integer id) {
        return entityManager.find(NotifCategory.class, id);
    }

    @Override
    @Transactional
    public void deleteById (@NotNull Integer id) {
        NotifCategory notifCategory = findById(id);

        if (notifCategory != null) {
            entityManager.remove(notifCategory);
        }
    }
 }
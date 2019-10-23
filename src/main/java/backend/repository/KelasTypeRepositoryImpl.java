package backend.repository;

import java.util.List;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import backend.model.KelasType;
import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;

/**
 * Author : supi.core@gmail.com | github.com/sup1core
 */

 @Singleton
 public class KelasTypeRepositoryImpl implements KelasTypeRepository {
     @PersistenceContext
     private EntityManager entityManager;

     public KelasTypeRepositoryImpl (@CurrentSession EntityManager entityManager) {
         this.entityManager = entityManager;
     }

     @Override
     @Transactional
     public KelasType save(KelasType kelasType) {
         entityManager.persist(kelasType);
         return kelasType;
     }

     @Override
     @Transactional
     public KelasType update(Integer id, KelasType kelasType) {
         entityManager.merge(kelasType);
         return kelasType;
     }

     @Override
     @Transactional
     public List<KelasType> findAll() {
        String qlString ="SELECT k FROM KelasType k order by name asc";
        TypedQuery<KelasType> query = entityManager.createQuery(qlString, KelasType.class);
        return query.getResultList();
     }

     @Override
     @Transactional(readOnly = true)
     public KelasType findById(@NotNull Integer id) {
         return entityManager.find(KelasType.class, id);
     }

     @Override
     @Transactional
     public void deleteById(@NotNull Integer id) {
         KelasType kelasType = findById(id);

         if (kelasType !=null) {
             entityManager.remove(kelasType);
         }
     }

 }
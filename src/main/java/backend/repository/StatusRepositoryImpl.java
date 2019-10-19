package backend.repository;

import java.util.List;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import backend.model.Status;
import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;

/**
 * Author : supi.core@gmail.com | github.com/sup1core
 */

 @Singleton
 
 public class StatusRepositoryImpl implements StatusRepository {
 
     @PersistenceContext
     private EntityManager entityManager;

     public StatusRepositoryImpl (@CurrentSession EntityManager entityManager) {
         this.entityManager = entityManager;
     }

     @Override
     @Transactional
     public Status save(Status status) {
         entityManager.persist(status);
         return status;
     }

     @Override
     @Transactional
     public Status update(Integer id, Status status) {
         entityManager.merge(status);
         return status;
     }

     @Override
     @Transactional
     public List<Status> findAll(){
         String qlString = "SELECT s FROM Status s where deleted_by = null";
         TypedQuery<Status> query = entityManager.createQuery(qlString, Status.class);
         return query.getResultList();
     }

     @Override
     @Transactional(readOnly = true)
     public Status findById(@NotNull Integer id) {
         return entityManager.find(Status.class, id);
     }

     @Override
     @Transactional
     public void deleteById(@NotNull Integer id) {
         Status status = findById(id);

         if (status != null) {
             entityManager.remove(status);
         }
     }
 }
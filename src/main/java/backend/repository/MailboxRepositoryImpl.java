package backend.repository;

import java.util.List;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import backend.model.Mailbox;
import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;

/**
 * Author : supi.core@gmail.com | github.com/sup1core
 */

 @Singleton
 
 public class MailboxRepositoryImpl implements MailboxRepository{
 
     @PersistenceContext
     private EntityManager entityManager;

     public MailboxRepositoryImpl (@CurrentSession EntityManager entityManager) {
         this.entityManager = entityManager;
     }

     @Override
     @Transactional
     public Mailbox save(Mailbox mailbox) {
         entityManager.persist(mailbox);
         return mailbox;
     }

     @Override
     @Transactional
     public Mailbox update(Integer id, Mailbox mailbox) {
         return entityManager.merge(mailbox);
     }

     @Override
     @Transactional (readOnly = true)
     public List<Mailbox> findAll() {
         String qlString = "SELECT m FROM Mailbox m";
         TypedQuery<Mailbox> query = entityManager.createQuery(qlString, Mailbox.class);
         return query.getResultList();
     }

     @Override
     @Transactional(readOnly = true)
     public Mailbox findById(@NotNull Integer id) {
        return entityManager.find(Mailbox.class, id);
     }

     @Override
     @Transactional (readOnly = true)
     public List<Mailbox> getRunningtext(){
        String qlString="SELECT m FROM Mailbox m where id_notifcategory=3 and status_id=1";
        TypedQuery<Mailbox> query = entityManager.createQuery(qlString, Mailbox.class);
        return query.getResultList();
     }

     @Override
     @Transactional (readOnly = true)
     public List<Mailbox> getCarousel() {
         String qlString="SELECT m FROM Mailbox m where id_notifcategory=4 and status_id=1 order by id desc";
         TypedQuery<Mailbox> query = entityManager.createQuery(qlString, Mailbox.class);
         query.setMaxResults(4);
         return query.getResultList();
     }

     @Override
     @Transactional (readOnly = true)
     public List<Mailbox> getNotification() {
         String qlString="SELECT m FROM Mailbox m where id_notifcategory=1 order by date desc";
         TypedQuery<Mailbox> query = entityManager.createQuery(qlString, Mailbox.class);
         return query.getResultList();
     }

     @Override
     @Transactional (readOnly = true)
     public List<Mailbox> getNewsinfo() {
         String qlString="SELECT m FROM Mailbox m where id_notifcategory=2 order by date desc";
         TypedQuery<Mailbox> query = entityManager.createQuery(qlString, Mailbox.class);
         return query.getResultList();
     }

     @Override
     @Transactional
     public void deleteById (@NotNull Integer id) {
         Mailbox mailbox = findById(id);
         if (mailbox != null) {
             entityManager.remove(mailbox);
         }
     }
 }
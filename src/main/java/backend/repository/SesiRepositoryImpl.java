package backend.repository;

import java.util.List;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import backend.model.Sesi;
import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;

/*
 * Author : supi.core@gmail.com
 */

 @Singleton
 /**
  * SesiRepositoryImpl
  */
 public class SesiRepositoryImpl implements SesiRepository{
     @PersistenceContext
     private EntityManager entityManager;

    public SesiRepositoryImpl (@CurrentSession EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Sesi save (@NotNull Sesi sesi){
        entityManager.persist(sesi);
        return sesi;
    }

    @Override
    @Transactional
    public Sesi update(Integer id, @NotNull Sesi sesi){
        return entityManager.merge(sesi);
    }

    @Override
    @Transactional(readOnly = true)
    public List <Sesi> findAll(){
        String qlString = "Select s from Sesi s";
        TypedQuery<Sesi> query = entityManager.createQuery(qlString, Sesi.class);
        return query.getResultList();
    }

    @Override
    @Transactional (readOnly = true)
    public Sesi findById(@NotNull Integer id){
        return entityManager.find(Sesi.class, id);
    }

    @Override
    @Transactional
    public void deleteById(@NotNull Integer id){
        Sesi sesi = findById(id);
        if (sesi !=null){
            entityManager.remove(sesi);
        }
    }

 }
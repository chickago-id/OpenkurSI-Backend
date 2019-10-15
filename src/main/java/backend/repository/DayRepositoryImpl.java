package backend.repository;

import java.util.List;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import backend.model.Day;
import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;

/**
 * Author : supi.core@gmail.com | github.com/sup1core
 */


 @Singleton
 public class DayRepositoryImpl implements DayRepository{
    @PersistenceContext
    private EntityManager entityManager;

    public DayRepositoryImpl(@CurrentSession EntityManager entityManager){
        this.entityManager = entityManager;   
    }

    @Override
    @Transactional
    public Day save(Day day){
        entityManager.persist(day);
        return day;
    }

    @Override
    @Transactional
    public Day update(Integer id, Day day){
        entityManager.merge(day);
        return day;
    }

    @Override
    @Transactional
    public List<Day> findAll(){
        String qlString = "SELECT d FROM Day d";
        TypedQuery<Day> query = entityManager.createQuery(qlString, Day.class);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Day findById(@NotNull Integer id){
        return entityManager.find(Day.class, id);
    }

    @Override
    @Transactional
    public void deleteById(@NotNull Integer id){
        Day day = findById(id);

        if (day !=null) {
            entityManager.remove(day);
        }
    }
     
 }
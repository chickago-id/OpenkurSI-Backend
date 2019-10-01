package backend.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import backend.model.Ruang;
import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;

/**
 * Author : supi.core@gmail.com
 */
public class RuangRepositoryImpl implements RuangRepository{
    @PersistenceContext
    private EntityManager entityManager;

    public RuangRepositoryImpl (@CurrentSession EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Ruang save(@NotNull Ruang ruang){
        entityManager.persist(ruang);
        return ruang;
    }

    @Override
    @Transactional
    public Ruang update(Integer id, @NotNull Ruang ruang){
        return entityManager.merge(ruang);
    }

    @Override
    @Transactional
    public List <Ruang> findAll(){
        String qlString ="select r from Ruang r";
        TypedQuery<Ruang> query= entityManager.createQuery(qlString, Ruang.class);
        return query.getResultList();
    }

    @Override
    @Transactional (readOnly = true)
    public Ruang findById(@NotNull Integer id){
        return entityManager.find(Ruang.class, id);
    }
    
    @Override
    @Transactional
    public void deleteById(@NotNull Integer id) {
        Ruang ruang = findById(id);
        if (ruang !=null){
            entityManager.remove(ruang);
        }
    }
}
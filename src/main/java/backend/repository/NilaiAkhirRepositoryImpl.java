package backend.repository;

import java.util.List;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import backend.model.NilaiAkhir;
import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;

/**
 * Author : supi.core@gmail.com
 */

@Singleton
public class NilaiAkhirRepositoryImpl implements NilaiAkhirRepository{

    @PersistenceContext
    private EntityManager entityManager;

    public NilaiAkhirRepositoryImpl(@CurrentSession EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public NilaiAkhir save(NilaiAkhir nilaiAkhir){
        entityManager.persist(nilaiAkhir);
        return nilaiAkhir;
    }

    @Override
    @Transactional
    public NilaiAkhir update(Long id, NilaiAkhir nilaiAkhir){
        entityManager.merge(nilaiAkhir);
        return nilaiAkhir;
    }

    @Override
    @Transactional
    public List<NilaiAkhir> findAll(){
        String qlString = "SELECT n FROM NilaiAkhir n";
        TypedQuery<NilaiAkhir> query = entityManager.createQuery(qlString, NilaiAkhir.class);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public NilaiAkhir findById(@NotNull Long id){
        return entityManager.find(NilaiAkhir.class, id);
    }

    @Override
    @Transactional
    public void deleteById(@NotNull Long id){
        NilaiAkhir nilaiAkhir = findById(id);

        if(nilaiAkhir !=null){
            entityManager.remove(nilaiAkhir);
        }
    }

}
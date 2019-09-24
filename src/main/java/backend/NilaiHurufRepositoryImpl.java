package backend;

import java.util.List;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import backend.model.NilaiHuruf;
import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;

@Singleton
public class NilaiHurufRepositoryImpl implements NilaiHurufRepository{
    @PersistenceContext
    private EntityManager entityManager;

    public NilaiHurufRepositoryImpl (@CurrentSession EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public NilaiHuruf save(@NotNull NilaiHuruf nilaihuruf){
        entityManager.persist(nilaihuruf);

        return nilaihuruf;
    }
    @Override
    @Transactional
    public NilaiHuruf update(@NotNull NilaiHuruf nilaihuruf){
        return entityManager.merge(nilaihuruf);
    }

    @Override
    @Transactional(readOnly = true)
    public List <NilaiHuruf> findAll(){
        String qlString = "Select n from NilaiHuruf n";
        TypedQuery<NilaiHuruf> query = entityManager.createQuery(qlString, NilaiHuruf.class);
        return query.getResultList();
    }

    @Override
    @Transactional (readOnly = true)
    public NilaiHuruf findById(@NotNull Long id_nilai_huruf){
        return entityManager.find(NilaiHuruf.class, id_nilai_huruf);
    }

    @Override
    @Transactional
    public void deleteById(@NotNull Long id_nilai_huruf) {
        NilaiHuruf nilaihuruf = findById(id_nilai_huruf);

        if (nilaihuruf != null){
            entityManager.remove(nilaihuruf);
        }
    }
}
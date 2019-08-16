package backend;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

import backend.model.Absen_Instruktur;

@Singleton
public class Absen_InstrukturRepositoryImpl implements Absen_InstrukturRepository{
    @PersistenceContext
    private EntityManager entityManager;

    public Absen_InstrukturRepositoryImpl(@CurrentSession EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Absen_Instruktur save(@NotNull Absen_Instruktur absen_instruktur) {
        entityManager.persist(absen_instruktur);
        return absen_instruktur;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Absen_Instruktur> findAll() {
        String qlString = "SELECT m FROM Absen_Instruktur m";
        TypedQuery<Absen_Instruktur> query = entityManager.createQuery(qlString, Absen_Instruktur.class);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Absen_Instruktur findById(@NotNull Long id) {
        return entityManager.find(Absen_Instruktur.class, id);
    }

    
}

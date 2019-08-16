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

import backend.model.Absen_Peserta;

@Singleton
public class Absen_PesertaRepositoryImpl implements Absen_PesertaRepository{
    @PersistenceContext
    private EntityManager entityManager;

    public Absen_PesertaRepositoryImpl(@CurrentSession EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Absen_Peserta save(@NotNull Absen_Peserta absen_peserta) {
        entityManager.persist(absen_peserta);
        return absen_peserta;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Absen_Peserta> findAll() {
        String qlString = "SELECT m FROM Absen_Peserta m";
        TypedQuery<Absen_Peserta> query = entityManager.createQuery(qlString, Absen_Peserta.class);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Absen_Peserta findById(@NotNull Long id) {
        return entityManager.find(Absen_Peserta.class, id);
    }

    
}

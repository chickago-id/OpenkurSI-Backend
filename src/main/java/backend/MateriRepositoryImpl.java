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

import backend.model.Materi;

@Singleton
public class MateriRepositoryImpl implements MateriRepository{
    @PersistenceContext
    private EntityManager entityManager;

    public MateriRepositoryImpl(@CurrentSession EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Materi save(@NotNull String kodeMateri, String namaMateri) {

        Materi materi = new Materi();
        materi.setKodeMateri(kodeMateri);
        materi.setNamaMateri(namaMateri);

        entityManager.persist(materi);
        return materi;
    }

    @Override
    @Transactional
    public Materi update(@NotNull Materi materi) {
        return entityManager.merge(materi);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Materi> findAll() {
        String qlString = "SELECT m FROM Materi m";
        TypedQuery<Materi> query = entityManager.createQuery(qlString, Materi.class);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Materi> findById(@NotNull Long id) {
        return Optional.ofNullable(entityManager.find(Materi.class, id));
    }
}

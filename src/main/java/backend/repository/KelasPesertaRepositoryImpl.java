package backend.repository;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.util.List;

import backend.model.KelasPeserta;

@Singleton
public class KelasPesertaRepositoryImpl implements KelasPesertaRepository{
    @PersistenceContext
    private EntityManager entityManager;

    public KelasPesertaRepositoryImpl(@CurrentSession EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public KelasPeserta save(@NotNull KelasPeserta kelaspeserta) {
        entityManager.persist(kelaspeserta);
        return kelaspeserta;
    }

    @Override
    @Transactional
    public KelasPeserta update(@NotNull KelasPeserta kelaspeserta) {
        return entityManager.merge(kelaspeserta);
    }

    @Override
    @Transactional(readOnly = true)
    public List<KelasPeserta> findAll() {
        String qlString = "select a from KelasPeserta a";
        TypedQuery<KelasPeserta> query = entityManager.createQuery(
            qlString, 
            KelasPeserta.class
            );
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<KelasPeserta> findByIdKelas(Long id_kelas) {
        String qlString = "select a from KelasPeserta a " +
                            "inner join UserDetail b on a.id_user = b.id where id_kelas = " + id_kelas;
        TypedQuery<KelasPeserta> query = entityManager.createQuery(
                qlString, 
                KelasPeserta.class
            );
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public KelasPeserta findById(@NotNull Long id) {
        return entityManager.find(KelasPeserta.class, id);
    }

    @Override
    @Transactional
    public void deleteById(@NotNull Long id) {
        KelasPeserta kelaspeserta = findById(id);

        if(kelaspeserta != null) {
            entityManager.remove(kelaspeserta);
        }
    }
}

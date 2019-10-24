package backend.repository;

import java.util.List;
import java.util.Optional;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import backend.model.UserDetail;
import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;

@Singleton
public class UserDetailRepositoryImpl implements UserDetailRepository{
    @PersistenceContext
    private EntityManager entityManager;

    public UserDetailRepositoryImpl(@CurrentSession EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public UserDetail save(@NotNull UserDetail userDetail) {
        entityManager.persist(userDetail);
        return userDetail;
    }

    @Override
    @Transactional
    public UserDetail update(@NotNull UserDetail userDetail) {
        return entityManager.merge(userDetail);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDetail> findByUserId(@NotNull Long userId) {
        String qlString = "SELECT ud FROM UserDetail ud where id_user = \'" + userId +"\'";
        TypedQuery<UserDetail> query = entityManager.createQuery(qlString, UserDetail.class);
        return Optional.ofNullable(query.getResultList().stream().findFirst().orElse(null));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDetail> getInstructor(){
        String qlString ="SELECT ud FROM UserDetail ud INNER JOIN User u on u.id = ud.id_user where u.role='Pengajar'";
        TypedQuery<UserDetail> query = entityManager.createQuery(qlString, UserDetail.class);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDetail> getSiswa(){
        String qlString ="SELECT ud FROM UserDetail ud INNER JOIN User u on u.id = ud.id_user where u.role='Peserta'";
        TypedQuery<UserDetail> query = entityManager.createQuery(qlString, UserDetail.class);
        return query.getResultList();
    }
}

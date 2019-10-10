package backend;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.util.Optional;

import backend.model.UserDetail;

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
}

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

import backend.model.User;

@Singleton
public class UserRepositoryImpl implements UserRepository{
    @PersistenceContext
    private EntityManager entityManager;

    public UserRepositoryImpl(@CurrentSession EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public User save(@NotNull User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    @Transactional
    public User update(@NotNull User user) {
        return entityManager.merge(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        String qlString = "SELECT m FROM User m";
        TypedQuery<User> query = entityManager.createQuery(qlString, User.class);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(@NotNull Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    @Transactional
    public void deleteById(@NotNull Long id) {
        User user = findById(id);

        if(user != null) {
            entityManager.remove(user);
        }
    }
}

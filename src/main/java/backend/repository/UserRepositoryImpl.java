package backend.repository;

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
        String qlString = "SELECT u FROM User u";
        TypedQuery<User> query = entityManager.createQuery(qlString, User.class);
        return query.getResultList();
    }

    //supicore added
    @Override
    @Transactional(readOnly = true)
    public List<User> getAll(){
        String qlString ="select u FROM User u inner join UserDetail ud on ud.id_user = ud.id";
        TypedQuery<User> query = entityManager.createQuery(qlString, User.class);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(@NotNull Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByUsername(@NotNull String username) {
        String qlString = "SELECT u FROM User u where username = \'" + username +"\'";
        TypedQuery<User> query = entityManager.createQuery(qlString, User.class);
        return Optional.ofNullable(query.getResultList().stream().findFirst().orElse(null));
    }
    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByEmail(@NotNull String email) {
        String qlString = "SELECT u FROM User u where email = \'" + email +"\'";
        TypedQuery<User> query = entityManager.createQuery(qlString, User.class);
        return Optional.ofNullable(query.getResultList().stream().findFirst().orElse(null));
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

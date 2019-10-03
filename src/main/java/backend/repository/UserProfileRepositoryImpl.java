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

import backend.model.UserProfile;

@Singleton
public class UserProfileRepositoryImpl implements UserProfileRepository {
    @PersistenceContext
    private EntityManager entityManager;

    // public KelasRepositoryImpl(@CurrentSession EntityManager entityManager) {
    // this.entityManager = entityManager;
    // }

    public UserProfileRepositoryImpl(@CurrentSession EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void deleteById(@NotNull Long id_user_profile) {
        // TODO Auto-generated method stub
        UserProfile userProfile = findById(id_user_profile);

        if (userProfile != null) {
            entityManager.remove(id_user_profile);
        }

    }

    @Override
    @Transactional(readOnly = true)
    public List<UserProfile> findAll() {
        // TODO Auto-generated method stub
        String qlString = "SELECT u FROM UserProfile u";
        TypedQuery<UserProfile> query = entityManager.createQuery(qlString, UserProfile.class);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public UserProfile findById(@NotNull Long id_user_profile) {
        // TODO Auto-generated method stub
        return entityManager.find(UserProfile.class, id_user_profile);
    }

    @Override
    @Transactional
    public UserProfile save(@NotNull UserProfile userProfile) {
        // TODO Auto-generated method stub
        entityManager.persist(userProfile);
        return userProfile;
    }

    @Override
    @Transactional
    public UserProfile update(@NotNull UserProfile userProfile) {
        // TODO Auto-generated method stub
        return entityManager.merge(userProfile);
    }

    // @Override
    // @Transactional
    // public Kelas save(@NotNull Kelas kelas) {
    // entityManager.persist(kelas);
    // return kelas;
    // }

    // @Override
    // @Transactional
    // public Kelas update(@NotNull Kelas kelas) {
    // return entityManager.merge(kelas);
    // }

    // @Override
    // @Transactional(readOnly = true)
    // public List<Kelas> findAll() {
    // String qlString = "SELECT m FROM Kelas m";
    // TypedQuery<Kelas> query = entityManager.createQuery(qlString, Kelas.class);
    // return query.getResultList();
    // }

    // @Override
    // @Transactional(readOnly = true)
    // public Kelas findById(@NotNull Long id_kelas) {
    // return entityManager.find(Kelas.class, id_kelas);
    // }

    // @Override
    // @Transactional
    // public void deleteById(@NotNull Long id_kelas) {
    // Kelas kelas = findById(id_kelas);

    // if (kelas != null) {
    // entityManager.remove(kelas);
    // }
    // }
}

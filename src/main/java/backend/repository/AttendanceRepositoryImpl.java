package backend.repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import backend.model.Attendance;
import backend.model.AttendanceToken;
import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;

/**
 * Author : akbar.lazuardi@yahoo.com | akbarlaz@github.com
 */

@Singleton
public class AttendanceRepositoryImpl implements AttendanceRepository{
    
    
    @PersistenceContext
    private EntityManager entityManager;
    public AttendanceRepositoryImpl(@CurrentSession EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Attendance save(Attendance objAttendance) {
        entityManager.persist(objAttendance);
        return objAttendance;
    }

    @Override
    @Transactional
    public Attendance update(Long id, Attendance objAttendance) {
        entityManager.merge(objAttendance);
         return objAttendance;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Attendance> findAll() {
        String qlString = "SELECT a FROM Attendance a";
        TypedQuery<Attendance> query = entityManager.createQuery(qlString, Attendance.class);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Attendance findById(@NotNull Long id) {
        return entityManager.find(Attendance.class, id);
    }

    @Override
    @Transactional
    public void deleteById(@NotNull Long id) {
        Attendance objAttendance = findById(id);
        if(objAttendance != null) {
            entityManager.remove(objAttendance);
        }
    }

    @Override
    @Transactional
    public AttendanceToken findByToken(String token) {
        String qlString = "select a from AttendanceToken a " +
            "where a.token = :token";
        TypedQuery<AttendanceToken> query =entityManager.createQuery(
            qlString, 
            AttendanceToken.class
        ).setParameter("token", token);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public Boolean existByUserIdAndToken(Long user_id, String token) {
        String qlString = "select a from Attendance a where a.user_id = :user_id " +
            "and a.token = :token";
        TypedQuery<Attendance> query = entityManager.createQuery(
            qlString,
            Attendance.class
            ).setParameter("user_id", user_id)
            .setParameter("token", token)
            .setMaxResults(1);
        return query.getResultList().isEmpty();
        /* if(query.getResultList().isEmpty()) {
            return false;
        } else {
            return true;
        } */
    }
}

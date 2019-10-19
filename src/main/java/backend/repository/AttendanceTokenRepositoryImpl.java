package backend.repository;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import java.util.List;

import backend.model.AttendanceToken;
import backend.model.Jadwal;

/**
 * Author : akbar.lazuardi@yahoo.com | akbarlaz@github.com
 */

@Singleton
public class AttendanceTokenRepositoryImpl implements AttendanceTokenRepository{
    @PersistenceContext
    private EntityManager entityManager;
    public AttendanceTokenRepositoryImpl(@CurrentSession EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public AttendanceToken save(AttendanceToken objAT) {
        entityManager.persist(objAT);
        return objAT;
    }

    @Override
    @Transactional
    public AttendanceToken update(Integer id, AttendanceToken objAT) {
        entityManager.merge(objAT);
         return objAT;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AttendanceToken> findAll() {
        String qlString = "SELECT a FROM AttendanceToken a";
        TypedQuery<AttendanceToken> query = entityManager.createQuery(qlString, AttendanceToken.class);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public AttendanceToken findById(@NotNull Integer id) {
        return entityManager.find(AttendanceToken.class, id);
    }

    @Override
    @Transactional
    public void deleteById(@NotNull Integer id) {
        AttendanceToken objAT = findById(id);
        if(objAT != null) {
            entityManager.remove(objAT);
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
    public List<Jadwal> findByIdPengajar(Long id_pengajar) {
        String qlString = "select a from Jadwal a " +
            "join fetch Kelas b on a.id_kelas = b.id where a.id_pengajar = :id_pengajar " +
            "group by a.id_kelas";
        TypedQuery<Jadwal> query =entityManager.createQuery(
            qlString, 
            Jadwal.class
        ).setParameter("id_pengajar", id_pengajar);
        return query.getResultList();
    }    
    
}

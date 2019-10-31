package backend.repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import backend.model.Attendance;
import backend.model.AttendanceToken;
import backend.model.KelasPeserta;
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
    public Boolean existByIdUserAndToken(Long id_user, String token) {
        String qlString = "select a from Attendance a where a.id_user = :id_user " +
            "and a.token = :token";
        TypedQuery<Attendance> query = entityManager.createQuery(
            qlString,
            Attendance.class
            ).setParameter("id_user", id_user)
            .setParameter("token", token)
            .setMaxResults(1);
        return query.getResultList().isEmpty();
    }

    @Override
    @Transactional
    public List<Attendance> findByIdJadwalAndIdUser(Integer id_jadwal, Long id_user) {
        String qlString = "select a from Attendance a "
            + "join fetch Jadwal b on a.id_jadwal = b.id "
            + "where a.id_user = :id_user "
            + "and a.id_jadwal = :id_jadwal "
            + "order by clock desc";
        TypedQuery<Attendance> query = entityManager.createQuery(
            qlString,
            Attendance.class
        ).setParameter("id_user", id_user)
        .setParameter("id_jadwal", id_jadwal);
        return query.getResultList();
    }

    @Override
    @Transactional
    public List<Attendance> findByIdKelasAndToken(Long id_kelas, String token) {
        String qlString = "select a from Attendance a where a.id_kelas = :id_kelas and a.token = :token order by a.clock desc";
        TypedQuery<Attendance> query = entityManager.createQuery(
            qlString,
            Attendance.class
        ).setParameter("id_kelas", id_kelas)
        .setParameter("token", token);
        return query.getResultList();
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Object> findByIdUser(Long id_user) {
        String qlString = "select a, count(a) from Attendance "
            + "a where a.id_user = :id_user group by id_jadwal";
        Query query = entityManager.createQuery(
            qlString
        ).setParameter("id_user", id_user);
        List<Object> result = query.getResultList(); 
        return result;
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Object> findByIdJadwal(Integer id_jadwal) {
        String qlString = "select b.kode_kelas, e.kode_materi, d.nama_lengkap from Jadwal a "
            + "inner join Materi e on a.id_materi = e.id "
            + "inner join Kelas b on a.id_kelas = b.id "
            + "inner join KelasPeserta c on b.id = c.id_kelas "
            + "inner join UserDetail d on c.id_user = d.id "
            + "where a.id = :id_jadwal";
        Query query = entityManager.createQuery(qlString)
            .setParameter("id_jadwal", id_jadwal);
        return query.getResultList();
    }

    @Override
    @Transactional
    public List<KelasPeserta> findByIdKelas(Long id_kelas) {
        String qlString = "select a from KelasPeserta a "
            + "join fetch UserDetail b on a.id_user = b.id_user "
            + "where a.id_kelas = :id_kelas";
        TypedQuery<KelasPeserta> query = entityManager.createQuery(
            qlString,
            KelasPeserta.class
        ).setParameter("id_kelas", id_kelas);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Attendance findByIdJadwalAndIdUserAndStatus(Integer id_jadwal, Long id_user) {
        String qlString = "select a from Attendance a "
            + "join fetch Jadwal b on a.id_jadwal = b.id "
            + "inner join Kelas c on b.id_kelas = c.id "
            + "inner join KelasPeserta d on c.id = d.id_kelas "
            + "where (a.is_approved = 0 or a.is_approved = null)"
            + "and a.id_jadwal = :id_jadwal "
            + "and a.id_user = :id_user "
            + "order by clock desc";
        TypedQuery<Attendance> query = entityManager.createQuery(
            qlString,
            Attendance.class
        ).setParameter("id_jadwal", id_jadwal)
        .setParameter("id_user", id_user);
        return query.getResultList().get(0);
    }
}

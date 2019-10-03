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

import backend.model.TahunAkademik;

@Singleton
public class TahunAkademikRepositoryImpl implements TahunAkademikRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public TahunAkademikRepositoryImpl(@CurrentSession EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void deleteById(@NotNull Long id_tahun_akademik) {
        // TODO Auto-generated method stub
        TahunAkademik tahunAkademik = findById(id_tahun_akademik);
        if (tahunAkademik != null) {
            entityManager.remove(tahunAkademik);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<TahunAkademik> findAll() {
        // TODO Auto-generated method stub
        String qlString = "SELECT t FROM TahunAkademik t";
        TypedQuery<TahunAkademik> query = entityManager.createQuery(qlString, TahunAkademik.class);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public TahunAkademik findById(@NotNull Long id_tahun_akademik) {
        // TODO Auto-generated method stub
        return entityManager.find(TahunAkademik.class, id_tahun_akademik);
    }

    @Override
    @Transactional
    public TahunAkademik save(@NotNull TahunAkademik tahunAkademik) {
        // TODO Auto-generated method stub
        entityManager.persist(tahunAkademik);
        return tahunAkademik;
    }

    @Override
    @Transactional
    public TahunAkademik update(@NotNull TahunAkademik tahunAkademik) {
        // TODO Auto-generated method stub
        entityManager.merge(tahunAkademik);
        return tahunAkademik;
    }

    // @Override
    // @Transactional
    // public void deleteById(@NotNull Long id_master_kelas) {
    // // TODO Auto-generated method stub
    // MasterKelas masterKelas = findById(id_master_kelas);
    // if (masterKelas != null)

    // {
    // entityManager.remove(masterKelas);
    // }
    // }

    // @Override
    // @Transactional(readOnly = true)
    // public List<MasterKelas> findAll() {
    // // TODO Auto-generated method stub
    // String qlString = "SELECT m FROM MasterKelas m";
    // TypedQuery<MasterKelas> query = entityManager.createQuery(qlString,
    // MasterKelas.class);
    // return query.getResultList();
    // }

    // @Override
    // @Transactional(readOnly = true)
    // public MasterKelas findById(@NotNull Long id_master_kelas) {
    // // TODO Auto-generated method stub
    // return entityManager.find(MasterKelas.class, id_master_kelas);
    // }

    // @Override
    // @Transactional
    // public MasterKelas save(@NotNull MasterKelas masterKelas) {
    // // TODO Auto-generated method stub
    // entityManager.persist(masterKelas);
    // return masterKelas;
    // }

    // @Override
    // @Transactional
    // public MasterKelas update(@NotNull MasterKelas masterKelas) {
    // // TODO Auto-generated method stub
    // entityManager.merge(masterKelas);
    // return masterKelas;
    // }
}

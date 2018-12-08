package dao;

import entity.SubsEntity;
import entity.SubsEntityPK;
import org.hibernate.LockMode;
import org.hibernate.query.Query;
import util.DBService;

import java.util.List;

public class SubsDAO extends AbstractDao<SubsEntity,String> {
    public SubsDAO() {
    }

    @Override
    public List<SubsEntity> getAll() {
        return  DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from SubsEntity ", SubsEntity.class).list();

    }

    @Override
    public SubsEntity getEntityById(String id) {
        return DBService.getSessionFactory()
                .getCurrentSession()
                .get(SubsEntity.class, id, LockMode.PESSIMISTIC_READ);
    }

    public SubsEntity getEntityById(SubsEntityPK key)
    {
        Query query = DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from SubsEntity where login = :paramLogin AND projectid = :paramProjId");
        query.setParameter("paramLogin",key.getLogin());
        query.setParameter("paramProjId",key.getProjectid());
        return (SubsEntity) query.uniqueResult();

    }

    public void delete(SubsEntityPK key)
    {
        Query query = DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("delete from SubsEntity where login = :paramLogin AND projectid = :paramProjId");
        query.setParameter("paramLogin",key.getLogin());
        query.setParameter("paramProjId",key.getProjectid());
        query.executeUpdate();
    }


}

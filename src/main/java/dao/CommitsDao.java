package dao;

import entity.CommitsEntity;
import entity.CommitsEntityPK;
import org.hibernate.LockMode;
import org.hibernate.query.Query;
import util.DBService;

import java.util.List;

public class CommitsDao extends AbstractDao<CommitsEntity,String> {
    @Override
    public List<CommitsEntity> getAll() {
        return  DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from CommitsEntity  ", CommitsEntity.class).list();
    }

    /**
     *
     * do not use
     */
    @Override
    public CommitsEntity getEntityById(String id) {
        return DBService.getSessionFactory()
                .getCurrentSession()
                .get(CommitsEntity.class,id,LockMode.PESSIMISTIC_READ);
    }

    public CommitsEntity getEntityById(CommitsEntityPK item){
        Query query = DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from CommitsEntity where  developer = :paramDeveloper AND projectid = :paramProjId AND time =:paramTime");
        query.setParameter("paramDeveloper", item.getDeveloper());
        query.setParameter("paramProjId",item.getProjectid());
        query.setParameter("paramTime",item.getProjectid());
        return (CommitsEntity) query.uniqueResult();
    }
}

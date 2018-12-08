package dao;

import entity.CommitsEntity;
import org.hibernate.LockMode;
import org.hibernate.query.Query;
import util.DBService;

import java.util.List;

public class CommitsDao extends AbstractDao<CommitsEntity,String> {
    @Override
    public List<CommitsEntity> getAll() {
        return  DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from CommitsEntity ", CommitsEntity.class).list();
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

}

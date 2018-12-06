package dao;

import entity.CommentsEntity;
import org.hibernate.LockMode;
import org.hibernate.query.Query;
import util.DBService;
import java.util.List;

public class CommentsDAO extends AbstractDao<CommentsEntity,String> {


    @Override
    public List<CommentsEntity> getAll() {
        return DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from CreditinfoEntity ", CommentsEntity.class).list();
    }

    @Override
    public CommentsEntity getEntityById(String id) {
        return DBService.getSessionFactory()
                .getCurrentSession()
                .get(CommentsEntity.class, id, LockMode.PESSIMISTIC_READ);
    }
}

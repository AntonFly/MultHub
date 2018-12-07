package dao;

import entity.FollowersEntity;
import org.hibernate.LockMode;
import util.DBService;

import java.util.List;

public class FollowersDAO extends AbstractDao<FollowersEntity,String>{

    @Override
    public List<FollowersEntity> getAll() {
        return  DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from CreditinfoEntity ", FollowersEntity.class).list();
    }

    @Override
    public FollowersEntity getEntityById(String id) {
        return DBService.getSessionFactory()
                .getCurrentSession()
                .get(FollowersEntity.class, id, LockMode.PESSIMISTIC_READ);
    }
}

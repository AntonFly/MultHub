package dao;

import entity.MassageEntity;
import entity.UserpostEntity;
import org.hibernate.LockMode;
import util.DBService;

import java.util.List;

public class UserpostDAO extends AbstractDao<UserpostEntity,String>{

    @Override
    public List<UserpostEntity> getAll() {
        return  DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from UserpostEntity ", UserpostEntity.class).list();
    }

    @Override
    public UserpostEntity getEntityById(String id) {
        return DBService.getSessionFactory()
                .getCurrentSession()
                .get(UserpostEntity.class, id, LockMode.PESSIMISTIC_READ);
    }
}
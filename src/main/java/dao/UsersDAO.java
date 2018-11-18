package dao;

import entity.*;
import org.hibernate.LockMode;
import org.hibernate.Session;
import util.DBService;

import java.io.Serializable;
import java.util.List;

public class UsersDAO extends AbstractDao<UsersEntity> {
    public UsersDAO() {
    }

    @Override
    public List<UsersEntity> getAll() {
        return  DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from UsersEntity ", UsersEntity.class).list();

    }


    @Override
    public void update(UsersEntity entity) {
         DBService.getSessionFactory()
                .getCurrentSession()
                .update(entity);
    }

    @Override
    public UsersEntity getEntityById(String id) {
        return DBService.getSessionFactory()
                .getCurrentSession()
                .get(UsersEntity.class, id, LockMode.PESSIMISTIC_READ);
    }

    @Override
    public void delete(UsersEntity entity) {
        DBService.getSessionFactory()
                .getCurrentSession()
                .delete(entity);
    }

    @Override
    public Serializable create(UsersEntity entity) {
        return  DBService.getSessionFactory()
                .getCurrentSession()
                .save(entity);
    }
}

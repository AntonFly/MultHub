package dao;

import entity.*;
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
    public Serializable create(UsersEntity entity) {
        return  DBService.getSessionFactory()
                .getCurrentSession()
                .save(entity);
    }
}

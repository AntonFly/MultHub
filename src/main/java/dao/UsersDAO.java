package dao;

import entity.*;
import org.hibernate.LockMode;
import org.hibernate.Session;
import util.DBService;

import java.io.Serializable;
import java.util.List;

public class UsersDAO extends AbstractDao<UsersEntity,String> {
    public UsersDAO() {
    }

    @Override
    public List<UsersEntity> getAll() {
        return  DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from UsersEntity ", UsersEntity.class).list();

    }

    @Override
    public UsersEntity getEntityById(String id) {
        return DBService.getSessionFactory()
                .getCurrentSession()
                .get(UsersEntity.class, id, LockMode.PESSIMISTIC_READ);
    }




}

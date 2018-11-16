package dao;

import entity.*;
import org.hibernate.Session;

import java.util.List;

public class UsersDAO extends AbstractDao {
    public UsersDAO() {
    }

    @Override
    public List getAll() {
        System.out.println("polucheni vse user");
        return null;
    }

    @Override
    public Object getEntityById(Object id) {
        System.out.println("poluchen user");
        return null;
    }

    @Override
    public Object update(Object entity) {
        System.out.println("obnovlen user");
        return null;
    }

    @Override
    public boolean delete(Object id) {
        System.out.println("udalen user");
        return false;
    }

    @Override
    public boolean create(Object entity) {
        System.out.println("sozdan user");
        return false;
    }
}

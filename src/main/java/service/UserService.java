package service;

import dao.*;
import entity.*;
import exception.DBException;
import javafx.beans.property.Property;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import util.DBService;

import javax.persistence.NoResultException;
import java.util.List;

public class UserService extends AbstractService<UsersEntity,String> {

    public UserService() {
    }

    @Override
    public List<UsersEntity> getAll() throws DBException {
        Transaction transaction = DBService.getTransaction();
        try {
            UsersDAO dao = DaoFactory.getUsersDAO();
            List<UsersEntity> list =  dao.getAll();

            transaction.commit();
            return list;
//            logger.fine("Create item " + user);

        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }

    }

    @Override
    public boolean create(UsersEntity user) throws DBException {
        Transaction transaction = DBService.getTransaction();
        try {
            UsersDAO dao = DaoFactory.getUsersDAO();
            dao.create(user);

            transaction.commit();

//            logger.fine("Create item " + user);

        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
        return true;
    }

    @Override
    public boolean update(UsersEntity item) throws DBException {
        Transaction transaction = DBService.getTransaction();
        try {
            UsersDAO dao = DaoFactory.getUsersDAO();
            dao.update(item);

            transaction.commit();

//            logger.fine("Create item " + user);

        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
        return true;
    }


    @Override
    public UsersEntity get(String id) throws DBException {
        Transaction transaction =DBService.getTransaction();
        try {
            UsersDAO dao = DaoFactory.getUsersDAO();
            UsersEntity ue =dao.getEntityById(id);
            transaction.commit();
            return ue;
        }catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }


    }

    @Override
    public boolean delete(String id) throws DBException {
        Transaction transaction = DBService.getTransaction();
        try{
            UsersDAO dao= DaoFactory.getUsersDAO();
            dao.delete(id);
            transaction.commit();
        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
        return true;
    }

    public  boolean signIn(String login,String password ) throws DBException {
        Transaction transaction= DBService.getTransaction();
        UsersEntity user=null;
        try{
            UsersDAO dao =DaoFactory.getUsersDAO();
            user =dao.getEntityById(login);
            transaction.commit();
        }catch (HibernateException | NoResultException e){
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
        return user.getPassword().equals(password);
    }

    public boolean signUp(UsersEntity user,ConnectiondataEntity con)throws DBException {
        Transaction transaction = DBService.getTransaction();
        try {
            UsersDAO uDao = DaoFactory.getUsersDAO();
            ConnectiondataDao connectiondataDao = DaoFactory.getConnectiondataDao();
            uDao.create(user);
            connectiondataDao.create(con);
            transaction.commit();
        }catch (HibernateException | NoResultException e){
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
        return true;
    }
     public  boolean sub(UsersEntity user, ProjectsEntity projectsEntity)throws DBException{
        Transaction transaction =DBService.getTransaction();
        SubsEntity subsEntity = new SubsEntity();
        subsEntity.setLogin(user.getLogin());
        subsEntity.setProjectid(projectsEntity.getProjectid());
        try{
            SubsDAO subsDAO= DaoFactory.getSubsDAO();
            subsDAO.create(subsEntity);
            transaction.commit();
        }catch (HibernateException | NoResultException e){
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
         return true;
     }
}

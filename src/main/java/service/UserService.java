package service;

import dao.CommitsDao;
import dao.DaoFactory;
import dao.UsersDAO;
import entity.CommitsEntity;
import entity.CommitsEntityPK;
import entity.ProjectsEntity;
import entity.UsersEntity;
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

//    public List<CommitsEntity> getProgectCommits(ProjectsEntity item) throws DBException {
//        Transaction transaction = DBService.getTransaction();
//        try {
//            CommitsDao dao = DaoFactory.getCommitsDao();
//            CommitsEntity cU = dao.getEntityById(item);
//            transaction.commit();
//            return cU;
//
//        } catch (HibernateException | NoResultException e) {
//            DBService.transactionRollback(transaction);
//            throw new DBException(e);
//        }
//
//
//    }
}

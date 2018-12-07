package service;

import dao.*;
import entity.*;
import exception.DBException;
import javafx.beans.property.Property;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import util.DBService;
import java.sql.Timestamp;

import javax.persistence.NoResultException;
import java.util.List;



public class ProjectService extends AbstractService<ProjectsEntity,Integer>{
    @Override
    public List<ProjectsEntity> getAll() throws DBException {
        Transaction transaction = DBService.getTransaction();
        try {
            ProjectsDAO dao = DaoFactory.getProjectsDAO();
            List<ProjectsEntity> list =  dao.getAll();

            transaction.commit();
            return list;
//            logger.fine("Create item " + user);

        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }

    }

    /**
     * Generate new user
     * @param project - project obj
     * @return true in case of success
     * @throws DBException Hiber exceptions replaced with
     */
    @Override
    public boolean create(ProjectsEntity project) throws DBException {
        Transaction transaction = DBService.getTransaction();
        try {
            ProjectsDAO dao = DaoFactory.getProjectsDAO();
            dao.create(project);

            transaction.commit();

//            logger.fine("Create item " + user);

        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
        return true;
    }

    @Override
    public boolean update(ProjectsEntity item) throws DBException {
        Transaction transaction = DBService.getTransaction();
        try {
            ProjectsDAO dao = DaoFactory.getProjectsDAO();
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
    public ProjectsEntity get(Integer id) throws DBException {
        Transaction transaction =DBService.getTransaction();
        try {
            ProjectsDAO dao = DaoFactory.getProjectsDAO();
            ProjectsEntity pe =dao.getEntityById(id);
            transaction.commit();
            return pe;
        }catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
    }

    @Override
    public boolean delete(Integer id) throws DBException {
        Transaction transaction = DBService.getTransaction();
        try{
            ProjectsDAO dao= DaoFactory.getProjectsDAO();
            dao.delete(id);
            transaction.commit();
        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
        return true;
    }
}

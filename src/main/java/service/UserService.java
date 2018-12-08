package service;

import dao.*;
import entity.*;
import exception.DBException;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import util.DBService;

import javax.persistence.NoResultException;
import java.util.List;

public class UserService extends AbstractService<UsersEntity,String> {

    public UserService() {
    }

    /**
     * Returned all users
     * @return list of users
     * @throws DBException Hiber exceptions replaced with
     */

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

    /**
     * Generate new user
     * @param user - user obj
     * @return true in case of success
     * @throws DBException Hiber exceptions replaced with
     */
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

    /**
     * Update short user information
     * @param item user who need to update
     *@return in case of success TRUE
     *@throws DBException Hiber exceptions replaced with
     */
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

    /**
     *  Return short information about one user
     * @param id user, whose information needed
     *@return in case of success TRUE
     *@throws DBException Hiber exceptions replaced with
     */
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

    /**
     *  Delete one user account
     * @param id user's login, who want
     *@return in case of success TRUE
     *@throws DBException Hiber exceptions replaced with
     */
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

    /**
     *
     * @param login user's login
     * @param password input user's password
     * @return in case of success TRUE
     * @throws DBException Hiber exceptions replaced with@throws DBException
     */
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

    /**
     *
     * @param user obj, new user data
     * @param con obj, new user's connection dara
     * @return in case of success TRUE
     * @throws DBException Hiber exceptions replaced withhrows DBException
     */
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



    /**
     *
     * @param user UserEntity obj who want to subscribe
     * @param projectsEntity ProjectEntity obj goal project
     * @return in case of success TRUE
     * @throws DBException Hiber exceptions replaced with
     * adds new sub into SubsEntity
     */
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


    /**
     * Delete subscription from one project
     * @param user UserEntity obj who want to unsubscribe
     * @param projectsEntity ProjectEntity obj goal project
     * @return in case of success TRUE
     * @throws DBException Hiber exceptions replaced with
     *
     */
     public boolean unsub(UsersEntity user, ProjectsEntity projectsEntity)throws DBException {
         Transaction transaction =DBService.getTransaction();
         SubsEntityPK subsEntitypk = new SubsEntityPK();
         subsEntitypk.setLogin(user.getLogin());
         subsEntitypk.setProjectid(projectsEntity.getProjectid());
         try{
                SubsDAO subsDAO= DaoFactory.getSubsDAO();
                subsDAO.delete(subsEntitypk);
                transaction.commit();
         }catch (HibernateException | NoResultException e){
             DBService.transactionRollback(transaction);
             throw new DBException(e);
         }
         return true;
     }

    /**
     *  Make comment to one project
     * @param comment obj which want to add
     * @return in case of success TRUE
     * @throws DBException Hiber exceptions replaced with
     */
     public boolean doComment(CommentsEntity comment)throws DBException{
         Transaction transaction =DBService.getTransaction();
         try{
             CommentsDAO commentsDAO = DaoFactory.getCommentsDAO();
             commentsDAO.create(comment);
             transaction.commit();
         }catch (HibernateException | NoResultException e){
             DBService.transactionRollback(transaction);
             throw new DBException(e);
         }
         return true;
     }

    /**
     * Delete comment from one project
     * @param comment comment which want to delete
     * @return  in case of success TRUE
     * @throws DBException Hiber exceptions replaced with
     */
     public boolean deleteComment(CommentsEntity comment) throws DBException{
         Transaction transaction =DBService.getTransaction();
         try{
             CommentsDAO commentsDAO = DaoFactory.getCommentsDAO();
             commentsDAO.delete(comment);
             transaction.commit();
         }catch (HibernateException | NoResultException e){
             DBService.transactionRollback(transaction);
             throw new DBException(e);
         }
         return true;
     }


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     *
     * @param user1  how it's better to do(2 users or dialogEnt(imho via users))
     * @param user2
     * @return
     * @throws DBException
     */
     public boolean createDialog(UsersEntity user1,UsersEntity user2)throws DBException{
         Transaction transaction =DBService.getTransaction();
         DialogEntity dialogEntity = new DialogEntity();
         dialogEntity.setId(null);
         dialogEntity.setOneUserId(user1.getLogin());
         dialogEntity.setTwoUserId(user2.getLogin());
         try{
             DialogDAO dialogDao = DaoFactory.getDialogDao();
             dialogDao.create(dialogEntity);
             transaction.commit();
         }catch (HibernateException | NoResultException e){
             DBService.transactionRollback(transaction);
             throw new DBException(e);
         }
         return true;
     }

    ////////////////////////////////////////////////////////////////////////
     public boolean addMessgae()throws DBException{
         Transaction transaction =DBService.getTransaction();
         try{

             transaction.commit();
         }catch (HibernateException | NoResultException e){
             DBService.transactionRollback(transaction);
             throw new DBException(e);
         }
         return true;
     }

     public boolean deleteMessage()throws DBException{
         Transaction transaction =DBService.getTransaction();
         try{

             transaction.commit();
         }catch (HibernateException | NoResultException e){
             DBService.transactionRollback(transaction);
             throw new DBException(e);
         }
         return true;
     }
    ////////////////////////////////////////////////////////////////////////


     ////////////////////////////////////////////////////////////////////////
     public boolean followUser()throws DBException {
         Transaction transaction =DBService.getTransaction();
         try{

             transaction.commit();
         }catch (HibernateException | NoResultException e){
             DBService.transactionRollback(transaction);
             throw new DBException(e);
         }
         return true;
     }
     public boolean unfollowUser()throws DBException{
         Transaction transaction =DBService.getTransaction();
         try{

             transaction.commit();
         }catch (HibernateException | NoResultException e){
             DBService.transactionRollback(transaction);
             throw new DBException(e);
         }
         return true;
     }
    ////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////
    /**
     * Юзер может создавать скок угодно проектов и соответственно становится в нем менеджером (картеж девелоперов )
     * @param projectsEntity
     * @param usersEntity
     * @return
     * @throws DBException
     */
    public boolean createProject(ProjectsEntity projectsEntity,UsersEntity usersEntity)throws  DBException{
        Transaction transaction =DBService.getTransaction();
        try{

            transaction.commit();
        }catch (HibernateException | NoResultException e){
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
        return true;
    }

    public boolean deleteProject()throws DBException{
        Transaction transaction =DBService.getTransaction();
        try{

            transaction.commit();
        }catch (HibernateException | NoResultException e){
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
        return true;
    }
////////////////////////////////////////////////////////////////////////

}

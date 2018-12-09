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
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;


public class ProjectService extends AbstractService<ProjectsEntity,String>{
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
     * Generate new project
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

    /**
     * @deprecated because of uuid id any change can cause errors
     * @param item project obj
     * @return true in case of success
     * @throws DBException Hiber exceptions replaced with
     */
    @Override
    @Deprecated
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
    public ProjectsEntity get(String id) throws DBException {
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
    public boolean delete(String id) throws DBException {
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

    /**
     *  adds new row in developers
     * @param developersEntity Developer Entity object
     * @return true in case of success
     * @throws DBException Hiber exceptions replaced with
     */
    public boolean addDeveloper(DevelopersEntity developersEntity) throws DBException{
        Transaction transaction = DBService.getTransaction();
        try{
            DevelopersDAO developersDAO = DaoFactory.getDevelopersDAO();
            developersDAO.create(developersEntity);
            transaction.commit();
        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
        return true;
    }

    /**
     * removes row from Developers entity
     * @param developersEntity developerEntity obkect
     * @return true in case of success
     * @throws DBException Hiber exceptions replaced with
     */
    public boolean deleteDeveloper(DevelopersEntity developersEntity) throws DBException{
        Transaction transaction = DBService.getTransaction();
        DevelopersEntityPK developersEntityPK = new DevelopersEntityPK();
        developersEntityPK.setLogin(developersEntity.getLogin());
        developersEntityPK.setProjectid(developersEntity.getProjectid());

        try{
            DevelopersDAO developersDAO = DaoFactory.getDevelopersDAO();
            developersDAO.delete(developersEntityPK);
            transaction.commit();
        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
        return true;
    }

    /**
     * adds new request to Requests entity. isRequest = false
     * @param requestsEntity requestEntity object
     * @return true in case of success
     * @throws DBException Hiber exceptions replaced with
     */
    public boolean sendInviteToProject(RequestsEntity requestsEntity)throws DBException{
        Transaction transaction = DBService.getTransaction();
        requestsEntity.setIsrequest(false);
        try{
            RequestsDAO requestsDAO = DaoFactory.getRequestsDAO();
            requestsDAO.create(requestsEntity);
            transaction.commit();
        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
        return true;
    }
//шляпа с айдюком проекта

    /**
     * adds new row in project posts entity
     * shifts the responsibility of proj obj init to the upper level
     * @param projectpostsEntity
     * @return
     * @throws DBException
     */
    public boolean addPostToBlog(ProjectpostsEntity projectpostsEntity)throws DBException{
        Transaction transaction = DBService.getTransaction();

        try{
            ProjectspostsDAO projectspostsDAO = DaoFactory.getProjectspostsDAO();
            projectspostsDAO.create(projectpostsEntity);
            transaction.commit();
        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
        return true;
    }

    /**
     *
     * @param projectpostsEntity post obj without a key
     * @return
     * @throws DBException
     */
    public boolean deletePostInBlog(ProjectpostsEntity projectpostsEntity) throws DBException{
        Transaction transaction = DBService.getTransaction();
        try{
            ProjectspostsDAO projectspostsDAO = DaoFactory.getProjectspostsDAO();
            projectspostsDAO.delete(UUID.nameUUIDFromBytes(projectpostsEntity.getText().getBytes()).toString());

            transaction.commit();
        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
        return true;
    }

    /**
     *
     * @param requestsEntity
     * @return
     * @throws DBException
     */
    public boolean approveRequest(RequestsEntity requestsEntity)throws DBException{
        Transaction transaction = null;
        RequestsEntityPK requestsEntityPK = new RequestsEntityPK();
        requestsEntityPK.setLogin(requestsEntity.getLogin());
        requestsEntityPK.setProjectid(requestsEntity.getProjectid());
        try{
            DevelopersEntity developersEntity = new DevelopersEntity();
            developersEntity.setProjectid(requestsEntity.getProjectid());
            developersEntity.setLogin(requestsEntity.getLogin());
            developersEntity.setDescription("empty");
            developersEntity.setProjpos(requestsEntity.getProjpos());
            addDeveloper(developersEntity);
            transaction = DBService.getTransaction();
            RequestsDAO requestsDAO = DaoFactory.getRequestsDAO();
            requestsDAO.delete(requestsEntityPK);
            transaction.commit();
        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
        return true;
    }

    /**
     *
     * @param requestsEntity
     * @return
     * @throws DBException
     */
    public boolean rejectRequest(RequestsEntity requestsEntity)throws DBException{
        Transaction transaction = DBService.getTransaction();
        RequestsEntityPK requestsEntityPK = new RequestsEntityPK();
        requestsEntityPK.setLogin(requestsEntity.getLogin());
        requestsEntityPK.setProjectid(requestsEntity.getProjectid());
        try{
            RequestsDAO requestsDAO = DaoFactory.getRequestsDAO();
            requestsDAO.delete(requestsEntityPK);
            transaction.commit();
        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
        return true;
    }

    /**
     *
     * @param developersEntity
     * @return
     * @throws DBException
     */
    public boolean updateDeveloper(DevelopersEntity developersEntity)throws DBException{
        Transaction transaction = DBService.getTransaction();
        try{
            DevelopersDAO developersDAO = DaoFactory.getDevelopersDAO();
            developersDAO.update(developersEntity);
            transaction.commit();
        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
        return true;
    }

    /**
     *
     * @param creditinfoEntity
     * @return
     * @throws DBException
     */
    public boolean addCreditInfo(CreditinfoEntity creditinfoEntity)throws DBException{
        Transaction transaction = DBService.getTransaction();
        try{
             CreditInfoDAO creditInfoDAO = DaoFactory.getCreditInfoDAO();
             creditInfoDAO.create(creditinfoEntity);
             transaction.commit();
        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
        return true;
    }

    /**
     *
     * @param creditinfoEntity
     * @return
     * @throws DBException
     */
    public boolean deleteCreditInfo(CreditinfoEntity creditinfoEntity)throws DBException {
        Transaction transaction = DBService.getTransaction();
        try {
            CreditInfoDAO creditInfoDAO = DaoFactory.getCreditInfoDAO();
            creditInfoDAO.delete(creditinfoEntity.getProjectid());
            transaction.commit();
        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
        return true;
    }
    //////////////////////////////////////////////////////      COMMIT       //////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * не уверен в параметрах от слова совсем, ибо чтобы обновить коммит нужно взять его айди
     * а чтоб взять айди коммита нужно взять айди проекта . Получается ебала костыльная as usual
     *
     * реализовано так что айди проекта сетится на уровень выше))))00 а айди коммита здесь
     * должно работать
     * @return
     * @throws DBException
     */
    public boolean approveCommit( CommitsEntity commitsEntity)throws DBException{
        Transaction transaction = DBService.getTransaction();
        commitsEntity.setId(UUID.nameUUIDFromBytes(   (commitsEntity.getDeveloper()+commitsEntity.getProjectid()+commitsEntity.getTime())   .getBytes()  ).toString());
        commitsEntity.setApproved(Approved.APPROVED);
        try{
            CommitsDao commitsDao = DaoFactory.getCommitsDao();
            commitsDao.update(commitsEntity);
            transaction.commit();
        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
        return true;
    }
    //the same as in approve

    /**
     *
     * @param commitsEntity
     * @return
     * @throws DBException
     */
    public boolean rejectCommit(CommitsEntity commitsEntity)throws DBException{
        commitsEntity.setId(UUID.nameUUIDFromBytes(   (commitsEntity.getDeveloper()+commitsEntity.getProjectid()+commitsEntity.getTime())   .getBytes()  ).toString());
        deleteCommit(commitsEntity);
        return true;
    }

    /**
     *
     * @param commitsEntity
     * @param commitsfileEntities
     * @return
     * @throws DBException
     */
    public boolean commitFiles(CommitsEntity commitsEntity,List<CommitsfileEntity> commitsfileEntities )throws DBException{
        Transaction transaction = DBService.getTransaction();
        try{
            CommitsDao commitsDao = DaoFactory.getCommitsDao();
            commitsDao.create(commitsEntity);
            CommitsfileDAO commitsfileDAO = DaoFactory.getCommitsfileDAO();
            for (CommitsfileEntity file :commitsfileEntities) {
                commitsfileDAO.create(file);
            }
            transaction.commit();
        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }

        return true;
    }


    /**
     *
     * @return
     * @throws DBException
     */
    public List<CommitsEntity> getUncheckedCommits()throws DBException{ //непонятки с commitsEntity
        Transaction transaction = DBService.getTransaction();
        List<CommitsEntity> commits;
        try{
            CommitsDao commitsDao = DaoFactory.getCommitsDao();
            commits = commitsDao.getUnchecked();
            transaction.commit();
        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
        return commits;
    }

    /**
     *
     * @param commitsEntity
     * @return
     * @throws DBException
     */
    public List<CommitsfileEntity> getCommitFiles(CommitsEntity commitsEntity)throws DBException{
        Transaction transaction = DBService.getTransaction();
        List<CommitsfileEntity> commits;
        try{
            CommitsfileDAO commitsDao = DaoFactory.getCommitsfileDAO();
            commits = commitsDao.getAssociatedFiles(  commitsEntity );
            transaction.commit();
        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
        return commits;
    }

    /**
     *
     * @param commitsEntity
     * @return
     * @throws DBException
     */
    public boolean deleteCommit(CommitsEntity commitsEntity)throws DBException{
        Transaction transaction = DBService.getTransaction();
        try{
            CommitsDao commitsDao = DaoFactory.getCommitsDao();
            commitsDao.delete(commitsEntity);
            transaction.commit();
        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }

        return true;
    }

    /**
     *
     * @param commitsfileEntity
     * @return
     * @throws DBException
     */
    public boolean deleteCommitFile(CommitsfileEntity commitsfileEntity)throws DBException{
        Transaction transaction = DBService.getTransaction();
        CommitsfileEntityPK commitsfileEntityPK = new CommitsfileEntityPK();
        commitsfileEntityPK.setCommitid(commitsfileEntity.getCommitid());
        commitsfileEntityPK.setFilename(commitsfileEntity.getFilename());
        try{
            CommitsfileDAO commitsfileDAO =DaoFactory.getCommitsfileDAO();
            commitsfileDAO.delete(commitsfileEntityPK);
            transaction.commit();
        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }

        return true;
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     *
     * @param commentsEntity
     * @return
     * @throws DBException
     */
    public boolean deleteComment(CommentsEntity commentsEntity)throws DBException{
        try {
            ServiceFactory.getUserService().deleteComment(commentsEntity);
        }
        catch (Exception ex){
            throw new DBException(ex);
        }
        return true;
    }

}
/*
  добавление файла в проект
  приминение коммита
  рассмотрение администратором коммита
  отправка приглашения в проект - done
  добавление пользователя в проект -done
  добавление поста в микроблог -done

  все сделано но не все затесчено

*/
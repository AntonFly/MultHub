package service;

import dao.*;
import entity.*;
import exception.DBException;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.DBService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import javax.persistence.NoResultException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ViewService  {
//    public List<UsersEntity> getProjDevelopers(ProjectsEntity proj){
//        Transaction transaction= DBService.getTransaction();
//        try{
//            Integer goalProjId;
//            DevelopersDAO devDao= DaoFactory.getDevelopersDAO();
//            ProjectsDAO projDao=DaoFactory.getProjectsDAO();
//            List<ProjectsEntity> projects= projDao.getAll();
//            for (ProjectsEntity prj: projects
//                 ) {
//                if prj.
//
//            }
//
//        }
//    }

    /**
     * Return user information for user profile
     * @param login request user login
     * @return map of user's information
     * @throws DBException Hiber exceptions replaced with
     */
    public Map<String,Object> UserPageInformation(String login) throws DBException {
        Transaction transaction=DBService.getTransaction();
        Map<String,Object> map =new HashMap<>();

        UsersDAO usersDAO= DaoFactory.getUsersDAO();
        ConnectiondataDao conDao= DaoFactory.getConnectiondataDao();
        UserpostDAO postsDao= DaoFactory.getUserPostDao();
        DevelopersDAO devDao= DaoFactory.getDevelopersDAO();
        ProjectsDAO projDao=DaoFactory.getProjectsDAO();
        FollowersDAO followDao=DaoFactory.getFollowersDao();

        UsersEntity user= usersDAO.getEntityById(login);
        ConnectiondataEntity connectiondata=conDao.getEntityById(login);
        List<UserpostEntity> posts= postsDao.getUserPosts(user.getLogin());
        List<DevelopersEntity> devEnt=devDao.getUserProject(login);
        List<ProjectsEntity> proj = new ArrayList<>();
        List<FollowersEntity> followers= followDao.getUserFollowers(login);

        transaction.commit();
        transaction=DBService.getTransaction();

        for (DevelopersEntity dev:
                devEnt) {
            proj.add(projDao.getEntityById(dev.getProjectid()));

        }
        transaction.commit();
        map.put("login",user.getLogin());
        map.put("name",user.getName());
        map.put("surname",user.getSurname());
        map.put("imjPath",user.getImgpath());
        map.put("email",connectiondata.geteMail());
        map.put("mobilenumb",connectiondata.getMobilenumb());
        map.put("satus",user.getStatus());
        map.put("posts",posts);
        map.put("projects",proj);
        map.put("followers",followers);

        return map;
    }
/////////////////////////Project page

    /**
     *
     * @param projectsEntity
     * @return
     * @throws DBException
     */
    public Map<String,Object> mainPageProjectInfo(ProjectsEntity projectsEntity) throws DBException{
        Transaction transaction= DBService.getTransaction();
        Map<String,Object> mapa = new HashMap<>();
        try {
            //posts
            ProjectspostsDAO projectspostsDAO = DaoFactory.getProjectspostsDAO();
            List<ProjectpostsEntity> posts = projectspostsDAO.getProjectPosts(projectsEntity.getProjectid());
            //donaters
            DonatersDAO donatersDAO = DaoFactory.getDonatersDAO();
            List<DonatersEntity> donaters = donatersDAO.getProjectDonaters(projectsEntity.getProjectid());
            //comments
            CommentsDAO commentsDAO = DaoFactory.getCommentsDAO();
            List<CommentsEntity> comments = commentsDAO.getProjectComments(projectsEntity.getProjectid());
            //followers
            SubsDAO subsDAO = DaoFactory.getSubsDAO();
            UsersDAO usersDAO = DaoFactory.getUsersDAO();
            List<SubsEntity> subsEntities = subsDAO.getProjectSubs(projectsEntity.getProjectid());
            List<UsersEntity> users = new LinkedList<>();
            for (SubsEntity sub: subsEntities) {
                users.add(usersDAO.getEntityById(sub.getLogin()));
            }
            mapa.put("ProjectEntity",projectsEntity);
            mapa.put("Posts",posts);
            mapa.put("Subs",users);
            mapa.put("Comments",comments);
            mapa.put("Donaters",donaters);

            transaction.commit();
        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
        return mapa;
    }

    /**
     *
     * @param projectsEntity
     * @return
     * @throws DBException
     */
    public List<Object[]> filesPageProjectInfo(ProjectsEntity projectsEntity) throws DBException{
        Transaction transaction= DBService.getTransaction();
        List<Object[]> list;
        try {
            CommitsDao commitsDao = DaoFactory.getCommitsDao();
            list = commitsDao.getCommitsFiles(projectsEntity);
            transaction.commit();

        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
        return list;
    }

    /**
     *
     * @param projectsEntity
     * @return
     * @throws DBException
     */
    public Map<String, Object> developersPageProjectInfo(ProjectsEntity projectsEntity) throws DBException{
        Transaction transaction= DBService.getTransaction();
        Map<String,Object> mapa = new HashMap<>();
        try {
            DevelopersDAO developersDAO = DaoFactory.getDevelopersDAO();
            mapa.put("ProjectEntity",projectsEntity);
            mapa.put("Devs",developersDAO.getProjectDevs(projectsEntity.getProjectid()));
            transaction.commit();

        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
        return mapa;
    }

    /**
     *
     * @param projectsEntity
     * @return
     * @throws DBException
     */
    public List<Object[]> uncheckedfilesPageProjectInfo(ProjectsEntity projectsEntity) throws DBException{ //for manager approvence
        Transaction transaction= DBService.getTransaction();
        List<Object[]> list;
        try {
            CommitsDao commitsDao = DaoFactory.getCommitsDao();
            list = commitsDao.getUncheckCommitsFiles(projectsEntity);
            transaction.commit();

        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
        return list;
    }
    /**
     *
     * @param fileDirectory
     * @return
     * @throws DBException
     */
    public Map<CommitsEntity, CommitsfileEntity> getFilecommits(String fileDirectory) throws DBException {
        Transaction transaction= DBService.getTransaction();
        Map<CommitsEntity,CommitsfileEntity> map = new HashMap<>();
        try {
            CommitsfileDAO commitsfileDAO = DaoFactory.getCommitsfileDAO();
            List<CommitsfileEntity> files = commitsfileDAO.getFilesByPath(fileDirectory);
            transaction.commit();
            //List<CommitsEntity> commits = new LinkedList<>();
            CommitsDao commitsDao = DaoFactory.getCommitsDao();
            for (CommitsfileEntity file: files) {
                transaction = DBService.getTransaction();
                map.put(commitsDao.getEntityById(file.getCommitid()),file);
              //      commits.add(commitsDao.getEntityById(file.getCommitid()));
                transaction.commit();
            }

        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
        return map;
    }

    /////////////////////////////////////////

    public  List<Map<String,Object>> getDialogs(String login) throws DBException {
        List<Map<String,Object>> result= new ArrayList<>();
        Transaction transaction= DBService.getTransaction();

        UsersDAO userDao= DaoFactory.getUsersDAO();
        DialogDAO dialogDAO =DaoFactory.getDialogDao();
        MessageDAO messageDao=DaoFactory.getMessageDao();

        List<DialogEntity> dialogs=dialogDAO.getUserDialogs(login);
        transaction.commit();
        for (DialogEntity dialog:dialogs
             ) {
            UsersEntity other;
            transaction=DBService.getTransaction();
            Map<String,Object> map=new HashMap<>();
            if (dialog.getOneUserId().equals(login)){
                map.put("other",dialog.getTwoUserId());
             other=userDao.getEntityById(dialog.getTwoUserId());
            }else{
                map.put("other",dialog.getOneUserId());
                other=userDao.getEntityById(dialog.getOneUserId());
            }
            MessageEntity message =messageDao.getLastMessage(login);
            map.put("otherImage",other.getImgpath());
            map.put("text",message.getText());
            map.put("time",message.getTime());
            result.add(map);
        }
        return result;
    }


    public List<MessageEntity> getDialogMessages(String id){
        Transaction transaction= DBService.getTransaction();
        List<MessageEntity> result;
        MessageDAO messageDAO=DaoFactory.getMessageDao();
        result =messageDAO.getDialogMessages(id);
        transaction.commit();
        return  result;
    }

    public  List<Map<String,Object>> mainPage(){
        List<Map<String,Object>> result= new ArrayList<>();
        Transaction transaction= DBService.getTransaction();

        SubsDAO subsDao=DaoFactory.getSubsDAO();
        ProjectsDAO projectsDao=DaoFactory.getProjectsDAO();
        ProjectspostsDAO postDao= DaoFactory.getProjectspostsDAO();
        CommitsDao commitsDao=DaoFactory.getCommitsDao();
        CommitsfileDAO commitsfileDao=DaoFactory.getCommitsfileDAO();
        List<Object[]> subs=subsDao.getMostPopular();
        transaction.commit();
        for (Object[] sub: subs
             ) {
            transaction=DBService.getTransaction();
            ProjectsEntity proj=projectsDao.getEntityById((String) sub[0]);
            ProjectpostsEntity post=postDao.getProjLastPost(proj.getProjectid());
            List<CommitsfileEntity> latestMedia=commitsfileDao.getLatestMedea(proj.getProjectid());
            transaction.commit();
            Map<String,Object> map=new HashMap<>();
            map.put("followers",sub[1]);
            map.put("prjName", proj.getName());
            map.put("description",proj.getDescription());
            map.put("lastPost",post.getText());
            map.put("lastMedia",latestMedia);
            result.add(map);
//            System.out.println(post.getText());
//           for (Object media: latestMedia
//            ) {
//                System.out.println((String) media);
//               System.out.println("1");
//            }

        }
        return result;
    }
//    public List<MessageEntity> getDialogMessages(String id){
//        Transaction transaction= DBService.getTransaction();
//        List<MessageEntity> result=DBService.getSessionFactory()
//                .getCurrentSession()
//                .createQuery("from MessageEntity  where = :Loginparam  or twoUserId =:Loginparam\");");
//    }
}

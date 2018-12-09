package service;

import dao.*;
import entity.*;
import exception.DBException;
import org.hibernate.Transaction;
import util.DBService;

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
        Map<String,Object> map =new HashMap<>();
        UsersDAO usersDAO= DaoFactory.getUsersDAO();
        UsersEntity user= usersDAO.getEntityById(login);
        ConnectiondataDao conDao= DaoFactory.getConnectiondataDao();
        ConnectiondataEntity connectiondata=conDao.getEntityById(login);
        UserpostDAO postsDao= DaoFactory.getUserPostDao();
        List<UserpostEntity> posts= postsDao.getUserPosts(user.getLogin());
        
        map.put("login",user.getLogin());
        map.put("name",user.getName());
        map.put("surname",user.getSurname());
        map.put("imjpath",user.getImgpath());
        map.put("email",connectiondata.geteMail());
        map.put("mobilenumb",connectiondata.getMobilenumb());
        map.put("posts",posts);

        return map;
    }

    public Map<String,Object> mainPageProjectInfo(ProjectsEntity projectsEntity) throws DBException{
        Map<String,Object> mapa = new HashMap<>();
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

        return mapa;
    }

    public void filesPageProjectInfo(ProjectsEntity projectsEntity) throws DBException{
        CommitsDao commitsDao = DaoFactory.getCommitsDao();
        commitsDao.getCommitsFiles(projectsEntity);

//        CommitsfileDAO commitsfileDAO = DaoFactory.getCommitsfileDAO();
//        List<CommitsEntity> allCommits = commitsDao.getCommits(projectsEntity.getProjectid());




    }

    public Map<String, Object> developersPageProjectInfo(ProjectsEntity projectsEntity) throws DBException{

        Map<String,Object> mapa = new HashMap<>();

        DevelopersDAO developersDAO = DaoFactory.getDevelopersDAO();
        mapa.put("ProjectEntity",projectsEntity);
        mapa.put("Devs",developersDAO.getProjectDevs(projectsEntity.getProjectid()));

        return mapa;
    }

}

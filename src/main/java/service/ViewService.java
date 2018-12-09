package service;

import dao.*;
import entity.*;
import exception.DBException;
import org.hibernate.Transaction;
import util.DBService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        map.put("posts",posts);
        map.put("projects",proj);
        map.put("followers",followers);

        return map;
    }

}

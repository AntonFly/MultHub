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

}

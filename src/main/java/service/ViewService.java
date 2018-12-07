package service;

import dao.*;
import entity.ConnectiondataEntity;
import entity.CreditinfoEntity;
import entity.ProjectsEntity;
import entity.UsersEntity;
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
    public Map<String,Object> fullUserInformation(String login) throws Exception {
        Map<String,Object> map =new HashMap<>();
        UsersDAO usersDAO= DaoFactory.getUsersDAO();
        UsersEntity user= usersDAO.getEntityById(login);
        map.put("login",user.getLogin());
        map.put("name",user.getName());
        map.put("surname",user.getSurname());
        map.put("imjpath",user.getImgpath());
        ConnectiondataDao conDao= DaoFactory.getConnectiondataDao();
        ConnectiondataEntity connectiondata=conDao.getEntityById(login);
        map.put("email",connectiondata.geteMail());
        map.put("mobilenumb",connectiondata.getMobilenumb());
        CreditInfoDAO creditDao=DaoFactory.getCreditInfoDAO();
        CreditinfoEntity creditinfoEntity=creditDao.getEntityById(login);
        map.put("curdnumber",creditinfoEntity.getCardnumber());
        map.put("yamoney",creditinfoEntity.getYamoney());
        map.put("qiwi",creditinfoEntity.getQiwimobilephone());
        return map;

    }
}

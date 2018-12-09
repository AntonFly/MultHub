package service;

import entity.*;
import exception.DBException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ViewServiceTest {
    private ViewService vs;
    @BeforeEach
    void setUp() {
        vs= ServiceFactory.getViewService();
    }

    @Test
    void userPageInformation() {
        Map<String,Object> user_data=null;
        try {
            user_data=vs.UserPageInformation("5d");
        } catch (DBException e) {
            e.printStackTrace();
            Assertions.fail("Ошибка получения информации профиля");
        }
        System.out.println("\nLogin: "+(String) user_data.get("login"));
        System.out.println("Name: "+(String)user_data.get("name"));
        System.out.println("Img: "+(String) user_data.get("imjPath"));
        for (ProjectsEntity proj: (List<ProjectsEntity>)user_data.get("projects")
             ) {
            System.out.println("\nProject: "+proj.getName());
        }
        System.out.println("\nFollowers:");
        for (FollowersEntity follower:(List<FollowersEntity>)user_data.get("followers")
             ) {
            System.out.println(follower.getFollower());
        }
        System.out.println("\nPosts:");
        for (UserpostEntity post:(List<UserpostEntity>)user_data.get("posts")
        ) {
            System.out.println(post.getText());
        }
//        System.out.println("Followers: "+user_data.get("projects"));

    }


    @Test
    void userDialog(){
        List<Map<String,Object>> result=null;
        try{
            result=vs.getDialogs("5d");
        } catch (DBException e) {
            e.printStackTrace();
            Assertions.fail("Ошибка получения диалогов");
        }
        for (Map<String,Object> map:
                result) {
            System.out.println();
            System.out.println("Собеседник: "+(String) map.get("other"));
            System.out.println("Аватар собеседникa: "+(String) map.get("otherImage"));
            System.out.println("Последнее сообщение: "+ (String) map.get("text"));
            System.out.println("Время сообщения: "+(Timestamp) map.get("time"));
        }
    }
    @Test
    void getFiles(){
        try{
         ProjectsEntity projectsEntity = new ProjectsEntity();
         projectsEntity.setName("LOL CHANGED");
         projectsEntity.setDescription("V 1999 GODU rodilsa divan i vosstal");
         projectsEntity.setCurbudget(12.);
         projectsEntity.setGoalbudget(13.);
         projectsEntity.setProjectid(UUID.nameUUIDFromBytes((projectsEntity.getName()+projectsEntity.getDescription()).getBytes()).toString());
         List<Object[]> list = ServiceFactory.getViewService().filesPageProjectInfo(projectsEntity);
         System.out.println("Commits and files connected with project:");
         for(Object[] row:list){
             System.out.println("CommitId: "+((CommitsEntity)row[1]).getId()+" \nDEVELOPER: "+ ((CommitsEntity)row[1]).getDeveloper()+" TIME:"+((CommitsEntity)row[1]).getTime());
             System.out.println("FILENAME: "+((CommitsfileEntity)row[2]).getFilename() +" FILEPATH:"+((CommitsfileEntity)row[2]).getFilepath());
         }

        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail("Ошибка получения диалогов");
        }
    }
}

package service;

import entity.*;
import exception.DBException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

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
            System.out.println("Собеседник: "+ map.get("other"));
            System.out.println("Аватар собеседникa: "+ map.get("otherImage"));
            System.out.println("Последнее сообщение: "+  map.get("text"));
            System.out.println("Время сообщения: "+ map.get("time"));
        }
    }

    @Test
    void dialogMessages() throws DBException {
        List<MessageEntity> messages= vs.getDialogMessages("1");
        for (MessageEntity mes:
                messages) {
            System.out.println();
            System.out.println("Cообщение: "+  mes.getText());
            System.out.println("Время сообщения: "+ mes.getTime());
        }
    }

    @Test
    void mainPage() throws DBException {
        List<Map<String,Object>> result=vs.mainPage();
        for (Map<String,Object> map:
                result) {
            System.out.println("\nПодписчиков: "+(BigInteger)map.get("followers"));
            System.out.println("Название проекта: " +(String) map.get("prjName"));
            System.out.println("Описание проекта: "+(String) map.get("description"));
            System.out.println("Последний пост: "+(String) map.get("lastPost"));
            List<CommitsfileEntity> latestMedia=(List<CommitsfileEntity>)map.get("lastMedia");
            System.out.print("Последние медиа файлы: ");
            for (CommitsfileEntity media: latestMedia
                 ) {
                System.out.println(( media).getFilename());

            }
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

    @Test
    void getFileCommits(){
        try {
            Map<CommitsEntity,CommitsfileEntity> map = ServiceFactory.getViewService().getFilecommits("kek");
            for (Map.Entry<CommitsEntity,CommitsfileEntity> entry: map.entrySet()) {
                System.out.println("Commit: id = "+entry.getKey().getId()+" time = "+entry.getKey().getTime()+" File: filename = "+entry.getValue().getFilename());
            }

        } catch (Exception e) {
        e.printStackTrace();
        Assertions.fail("Ошибка получения комитов");
        }
    }

    @Test
    void mainPageInfo(){
        try {
            ProjectsEntity projectsEntity = new ProjectsEntity();
            projectsEntity.setName("LOL CHANGED");
            projectsEntity.setDescription("V 1999 GODU rodilsa divan i vosstal");
            projectsEntity.setCurbudget(12.);
            projectsEntity.setGoalbudget(13.);
            projectsEntity.setProjectid(UUID.nameUUIDFromBytes((projectsEntity.getName()+projectsEntity.getDescription()).getBytes()).toString());

            Map<String,Object> map = ServiceFactory.getViewService().mainPageProjectInfo(projectsEntity);
            System.out.println("posts text: "+((List<ProjectpostsEntity>)map.get("Posts")).get(0).getText());
            System.out.println("subs: "+((List<SubsEntity>)map.get("Subs")).get(0).getLogin());

        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail("Ошибка получения инфы");
        }
    }

    @Test
    void developersPageProjectInfo(){
        try {
            ProjectsEntity projectsEntity = new ProjectsEntity();
            projectsEntity.setName("LOL CHANGED");
            projectsEntity.setDescription("V 1999 GODU rodilsa divan i vosstal");
            projectsEntity.setCurbudget(12.);
            projectsEntity.setGoalbudget(13.);
            projectsEntity.setProjectid(UUID.nameUUIDFromBytes((projectsEntity.getName()+projectsEntity.getDescription()).getBytes()).toString());

            Map<String,Object> map = ServiceFactory.getViewService().developersPageProjectInfo(projectsEntity);
            System.out.println("developers: login = "+((List<DevelopersEntity>)map.get("Devs")).get(0).getLogin() +" description = "+((List<DevelopersEntity>)map.get("Devs")).get(0).getDescription());

        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail("Ошибка получения инфы");
        }
    }

    @Test
    void uncheckedfilesPageProjectInfo(){
        try {

            ProjectsEntity projectsEntity = new ProjectsEntity();
            projectsEntity.setName("LOL CHANGED");
            projectsEntity.setDescription("V 1999 GODU rodilsa divan i vosstal");
            projectsEntity.setCurbudget(12.);
            projectsEntity.setGoalbudget(13.);
            projectsEntity.setProjectid(UUID.nameUUIDFromBytes((projectsEntity.getName()+projectsEntity.getDescription()).getBytes()).toString());


            List<Object[]> list = ServiceFactory.getViewService().uncheckedfilesPageProjectInfo(projectsEntity);
            System.out.println("Waiting commits:");
            for(Object[] row:list){
                System.out.println("CommitId: "+((CommitsEntity)row[1]).getId()+" \nDEVELOPER: "+ ((CommitsEntity)row[1]).getDeveloper()+" TIME:"+((CommitsEntity)row[1]).getTime());
                System.out.println("FILENAME: "+((CommitsfileEntity)row[2]).getFilename() +" FILEPATH:"+((CommitsfileEntity)row[2]).getFilepath());
            }

        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail("Ошибка получения инфы");
        }
    }
}

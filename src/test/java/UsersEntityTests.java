import dao.DaoFactory;
import entity.*;
import exception.DBException;
import org.junit.jupiter.api.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import service.UserService;

import java.sql.Timestamp;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UsersEntityTests {
    private UserService ds;
    String login= "3d";
    String newPassword="updated";
     static void main(String[] args) {
         JUnitCore runner = new JUnitCore();
         Result result = runner.run(UsersEntityTests.class);
         System.out.println("run tests: " + result.getRunCount());
         System.out.println("failed tests: " + result.getFailureCount());
         System.out.println("ignored tests: " + result.getIgnoreCount());
         System.out.println("success: " + result.wasSuccessful());
    }
    @BeforeAll
    void init(){
         ds= new UserService();
    }
    @Test
    void addUser(){
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setLogin(login);
        usersEntity.setName("smth");
        usersEntity.setSurname("smth");
        usersEntity.setPassword("smth");
        try {
            ds.create(usersEntity);
        } catch (DBException e) {
            e.printStackTrace();
            Assertions.fail("Ошибка добавления объекта");
        }

    }
    @Test
    void deleteUser(){
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setLogin(login);
        try {
            ds.delete(usersEntity.getLogin());
        } catch (DBException e) {
            e.printStackTrace();
            Assertions.fail("Ошибка удаления объекта");
        }
    }
    @Test
    void  getUser(){
         UsersEntity user=null;
        try {
            user =ds.get(login);
        } catch (DBException e) {
            e.printStackTrace();
            Assertions.fail("Ошибка изъятия объекта");
        }
        Assertions.assertNotNull(user);
        Assertions.assertEquals(login, user.getLogin());
    }

    @Test
    void  updateUser(){
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setLogin(login);
        usersEntity.setName("smth");
        usersEntity.setSurname("smth");
        usersEntity.setPassword(newPassword);
        try {
            ds.update(usersEntity);
        } catch (DBException e) {
            e.printStackTrace();
            Assertions.fail("Ошибка обновления объекта");
        }
        try {
            usersEntity=ds.get(login);
        } catch (DBException e) {
            e.printStackTrace();
            Assertions.fail("Ошибка изъятия объекта");
        }
        Assertions.assertEquals(newPassword,usersEntity.getPassword());
    }
    @Test
    void signUp(){
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setLogin("4d");
        usersEntity.setName("dipidor");
        usersEntity.setSurname("ffkgf");
        usersEntity.setPassword("danxyi");
        ConnectiondataEntity con = new ConnectiondataEntity();
        con.setLogin("3d");
        con.seteMail("@mail.com");
        con.setMobilenumb(4452);
        try{
        Assertions.assertTrue(ds.signUp(usersEntity,con));
        ds.delete(usersEntity.getLogin());
        }catch (DBException e){
            e.printStackTrace();
            Assertions.fail("Ошибка регистрации");
        }
     }
    @Test
    void signIn(){
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setLogin("4d");
        usersEntity.setName("dipidor");
        usersEntity.setSurname("ffkgf");
        usersEntity.setPassword("danxyi");
        try {
            ds.create(usersEntity);
            Assertions.assertTrue(ds.signIn(usersEntity.getLogin(),usersEntity.getPassword()));
            ds.delete(usersEntity.getLogin());
        } catch (DBException e) {
            e.printStackTrace();

        }
    }
    @Test
    void sub(){
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setLogin("4d");
        usersEntity.setName("dipidor");
        usersEntity.setSurname("ffkgf");
        usersEntity.setPassword("danxyi");
        ProjectsEntity projectsEntity = new ProjectsEntity();
        projectsEntity.setProjectid(1);
        try {
            ds.create(usersEntity);
            ds.sub(usersEntity,projectsEntity);
            ds.delete(usersEntity.getLogin());
        }catch (DBException e) {
            e.printStackTrace();
            Assertions.fail("Ошибка подписки");
        }
    }
    @Test
    void unsub(){
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setLogin("4d");
        usersEntity.setName("dipidor");
        usersEntity.setSurname("ffkgf");
        usersEntity.setPassword("danxyi");
        ProjectsEntity projectsEntity = new ProjectsEntity();
        projectsEntity.setProjectid(1);
        try {
            ds.create(usersEntity);
            ds.sub(usersEntity,projectsEntity);
            ds.unsub(usersEntity,projectsEntity); //this shit works
            ds.delete(usersEntity.getLogin());
        }catch (DBException e) {
            e.printStackTrace();
            Assertions.fail("Ошибка отписки");
        }
    }

    @Test
    void doComment() throws DBException { //проблема с ключом коммента, поэтому херь с удалением

        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setLogin("4d");
        usersEntity.setName("dipidor");
        usersEntity.setSurname("ffkgf");
        usersEntity.setPassword("danxyi");
        ProjectsEntity projectsEntity = new ProjectsEntity();
        projectsEntity.setProjectid(1);
        CommentsEntity commentsEntity = new CommentsEntity();
        commentsEntity.setId(null);
        commentsEntity.setLogin(usersEntity.getLogin());
        commentsEntity.setProjectid(projectsEntity.getProjectid());
        commentsEntity.setComment("skfnhsdghfjds"); //нужно переименовать в коммент
        commentsEntity.setTime(new Timestamp(System.currentTimeMillis()));   //могут быть косяки со временем у клиента, тип время с сервака указывается
         try {
            ds.create(usersEntity);
            ds.doComment(commentsEntity);
            ds.deleteComment(commentsEntity);
            ds.delete(usersEntity.getLogin());
        }catch (DBException e) {
            e.printStackTrace();
            ds.delete(usersEntity.getLogin());
            Assertions.fail("Ошибка отписки");
        }

    }
}


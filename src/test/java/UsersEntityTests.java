import dao.DaoFactory;
import entity.*;
import exception.DBException;
import org.junit.jupiter.api.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import service.UserService;

import java.sql.Timestamp;
import java.util.UUID;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UsersEntityTests {
    private UserService ds;
    String login= "d";
    String newPassword="updated";
     static void main(String[] args) {
         JUnitCore runner = new JUnitCore();
         Result result = runner.run(UsersEntityTests.class);
//         System.out.println("run tests: " + result.getRunCount());
//         System.out.println("failed tests: " + result.getFailureCount());
//         System.out.println("ignored tests: " + result.getIgnoreCount());
//         System.out.println("success: " + result.wasSuccessful());
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
    void signUp() throws DBException {
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setLogin("4d");
        usersEntity.setName("dipidor");
        usersEntity.setSurname("ffkgf");
        usersEntity.setPassword("danxyi");
        ConnectiondataEntity con = new ConnectiondataEntity();
        con.setLogin("4d");
        con.seteMail("@mail.com");
        con.setMobilenumb(4452);
        try{
        Assertions.assertTrue(ds.signUp(usersEntity,con));
        ds.delete(usersEntity.getLogin());
        }catch (DBException e){
            ds.delete(usersEntity.getLogin());
            e.printStackTrace();
            Assertions.fail("Ошибка регистрации");
        }
     }
    @Test
    void signIn() throws DBException {
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
            ds.delete(usersEntity.getLogin());
            Assertions.fail("Ошибка входа");
            e.printStackTrace();


        }
    }
    @Test
    void sub() throws DBException {
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setLogin("4d");
        usersEntity.setName("dipidor");
        usersEntity.setSurname("ffkgf");
        usersEntity.setPassword("danxyi");
        ProjectsEntity projectsEntity = new ProjectsEntity();
        projectsEntity.setProjectid("1");
        try {
            ds.create(usersEntity);
            ds.sub(usersEntity,projectsEntity);
            ds.delete(usersEntity.getLogin());
        }catch (DBException e) {
            ds.delete(usersEntity.getLogin());
            e.printStackTrace();
            Assertions.fail("Ошибка подписки");
        }
    }
    @Test
    void unsub() throws DBException {
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setLogin("4d");
        usersEntity.setName("dipidor");
        usersEntity.setSurname("ffkgf");
        usersEntity.setPassword("danxyi");
        ProjectsEntity projectsEntity = new ProjectsEntity();
        projectsEntity.setProjectid("1");
        try {
            ds.create(usersEntity);
            ds.sub(usersEntity,projectsEntity);
            ds.unsub(usersEntity,projectsEntity); //this shit works
            ds.delete(usersEntity.getLogin());
        }catch (DBException e) {
            e.printStackTrace();
            ds.delete(usersEntity.getLogin());
            Assertions.fail("Ошибка отписки");
        }
    }

    @Test
    void do_deleteComment() throws DBException { //проблема с ключом коммента, поэтому херь с удалением

        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setLogin("4d");
        usersEntity.setName("dipidor");
        usersEntity.setSurname("ffkgf");
        usersEntity.setPassword("danxyi");
        ProjectsEntity projectsEntity = new ProjectsEntity();
        projectsEntity.setProjectid("1");
        CommentsEntity commentsEntity = new CommentsEntity();
        commentsEntity.setId(null);
        commentsEntity.setLogin(usersEntity.getLogin());
        commentsEntity.setProjectid(projectsEntity.getProjectid());
        commentsEntity.setComment("skfnhsdghfjds");
        commentsEntity.setTime(new Timestamp(System.currentTimeMillis()));
         try {
            ds.create(usersEntity);
            ds.doComment(commentsEntity);
            ds.deleteComment(commentsEntity);
            ds.delete(usersEntity.getLogin());
        }catch (DBException e) {
            e.printStackTrace();
            ds.delete(usersEntity.getLogin());
            Assertions.fail("Ошибка удаления комментария");
        }
    }
    @Test
    void createDialog() throws DBException{
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setLogin("4d");
        usersEntity.setName("dipidor");
        usersEntity.setSurname("ffkgf");
        usersEntity.setPassword("danxyi");
        UsersEntity usersEntity2 = new UsersEntity();
        usersEntity2.setLogin("5d");
        usersEntity2.setName("dipidor");
        usersEntity2.setSurname("ffkgf");
        usersEntity2.setPassword("danxyi");
        try{
            ds.create(usersEntity);
            ds.create(usersEntity2);
            ds.createDialog(usersEntity,usersEntity2);
            ds.delete(usersEntity.getLogin());
            ds.delete(usersEntity2.getLogin());
        }catch (DBException e){
            e.printStackTrace();
            Assertions.fail("Ошибка создания диалога");
        }
    }
    @Test
    void sendMessage() throws DBException{
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setLogin("4d");
        usersEntity.setName("dipidor");
        usersEntity.setSurname("ffkgf");
        usersEntity.setPassword("danxyi");
        UsersEntity usersEntity2 = new UsersEntity();
        usersEntity2.setLogin("5d");
        usersEntity2.setName("dipidor");
        usersEntity2.setSurname("ffkgf");
        usersEntity2.setPassword("danxyi");
        MessageEntity message =new MessageEntity();
        message.setDialogId(UUID.nameUUIDFromBytes((usersEntity.getLogin()+usersEntity2.getLogin()).getBytes()).toString());
        message.setIsread(false);
        message.setTime(new Timestamp(System.currentTimeMillis()));
        message.setUserId(usersEntity.getLogin());
        message.setToUserId(usersEntity2.getLogin());
        message.setText("horosho");
        try{
        ds.create(usersEntity);
        ds.create(usersEntity2);
        ds.createDialog(usersEntity,usersEntity2);
        ds.addMessgae(message);
        ds.delete(usersEntity.getLogin());
        ds.delete(usersEntity2.getLogin());
        }catch (DBException e){
            e.printStackTrace();
            Assertions.fail("Ошибка отправки сообщения");
        }
    }
}


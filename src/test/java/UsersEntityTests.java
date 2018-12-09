import dao.DaoFactory;
import dao.RequestsDAO;
import entity.*;
import exception.DBException;
import org.hibernate.Transaction;
import org.junit.jupiter.api.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import service.ProjectService;
import service.ServiceFactory;
import service.UserService;
import util.DBService;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.UUID;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UsersEntityTests {
    private UserService ds;
    private String login= "d";
    UsersEntity user;

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
         ds= ServiceFactory.getUserService();
        user = new UsersEntity();
        user.setLogin(login);
        user.setName("smth");
        user.setSurname("smth");
        user.setPassword("smth");
    }
    @Test
    void addUser(){

        try {
            ds.create(user);
        } catch (DBException e) {
            e.printStackTrace();
            Assertions.fail("Ошибка добавления объекта");
        }

    }
    @Test
    void deleteUser(){
//        UsersEntity usersEntity = new UsersEntity();
//        usersEntity.setLogin(login);
        try {
            ds.delete(user.getLogin());
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
        String newPassword = "updated";
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

    @Test
    void follow_unfollow() throws DBException{
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
        FollowersEntity follow= new FollowersEntity();
        follow.setLogin(usersEntity.getLogin());
        follow.setFollower(usersEntity2.getLogin());
        try {
            ds.create(usersEntity);
            ds.create(usersEntity2);
            ds.followUser(follow);
            ds.unfollowUser(follow);
            ds.delete(usersEntity.getLogin());
            ds.delete(usersEntity2.getLogin());
        } catch (DBException e) {
            e.printStackTrace();
            Assertions.fail("Ошибка подписки/отписки");
        }
    }

    @Test
    void create_delete_project(){
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setLogin("4d");
        usersEntity.setName("dipidor");
        usersEntity.setSurname("ffkgf");
        usersEntity.setPassword("danxyi");
        ProjectsEntity proj= new ProjectsEntity();
        proj.setDescription("dfsdf");
        proj.setName("car");
        proj.setProjectid(UUID.nameUUIDFromBytes((proj.getName()+proj.getDescription()).getBytes()).toString());
        proj.setCurbudget(2.);
        proj.setGoalbudget(3.);
        try{
            ds.create(usersEntity);
        ds.createProject(proj,usersEntity);
        ds.deleteProject(proj);
        ds.delete(usersEntity.getLogin());
        } catch (DBException e) {
            e.printStackTrace();
            Assertions.fail("Ошибка создания/удаления проекта пользователя");
        }

    }

    @Test
    void request(){
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setLogin("4d");
        usersEntity.setName("dipidor");
        usersEntity.setSurname("ffkgf");
        usersEntity.setPassword("danxyi");
        ProjectsEntity projectsEntity = new ProjectsEntity();
        projectsEntity.setProjectid("1");
        try{
            ds.create(usersEntity);
            ds.requestProj(projectsEntity,usersEntity);
            ds.delete(usersEntity.getLogin());

        } catch (DBException e) {
            e.printStackTrace();
            Assertions.fail("Ошибка создания/удаления проекта пользователя");
        }
    }

    @Test
    void donate(){
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setLogin("4d");
        usersEntity.setName("dipidor");
        usersEntity.setSurname("ffkgf");
        usersEntity.setPassword("danxyi");
        ProjectsEntity projectsEntity = new ProjectsEntity();
        projectsEntity.setProjectid("1");
        DonatersEntity donate= new DonatersEntity();
        donate.setLogin(usersEntity.getLogin());
        donate.setProjectid(projectsEntity.getProjectid());
        donate.setText("spasibo");
        donate.setValue(5.);
        try {
            ds.create(usersEntity);
            ds.donate(donate);
            ds.delete(usersEntity.getLogin());
        } catch (DBException e) {
            e.printStackTrace();
            Assertions.fail("Ошибка доната");
        }
    }

    @Test
    void rejectInvite(){
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setLogin("4d");
        usersEntity.setName("dipidor");
        usersEntity.setSurname("ffkgf");
        usersEntity.setPassword("danxyi");
        ProjectsEntity projectsEntity = new ProjectsEntity();
        projectsEntity.setProjectid("1");
        RequestsEntity req= new RequestsEntity();
        req.setIsrequest(false);
        req.setProjpos(Projpos.DEVELOPER);
        req.setProjectid(projectsEntity.getProjectid());
        req.setLogin(usersEntity.getLogin());
        try {
            ds.create(usersEntity);
            ProjectService service=ServiceFactory.getProjectService();
            service.sendInviteToProject(req);
            ds.rejectInvite(req);
            ds.delete(usersEntity.getLogin());
        } catch (DBException e) {
            e.printStackTrace();
            Assertions.fail("Ошибка доната");
        }

    }

    @Test
    void approveInvite(){
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setLogin("4d");
        usersEntity.setName("dipidor");
        usersEntity.setSurname("ffkgf");
        usersEntity.setPassword("danxyi");
        ProjectsEntity projectsEntity = new ProjectsEntity();
        projectsEntity.setProjectid("1");
        RequestsEntity req= new RequestsEntity();
        req.setIsrequest(false);
        req.setProjpos(Projpos.DEVELOPER);
        req.setProjectid(projectsEntity.getProjectid());
        req.setLogin(usersEntity.getLogin());
        try {
            ds.create(usersEntity);
            ProjectService service=ServiceFactory.getProjectService();
            service.sendInviteToProject(req);
            ds.approveInvite(req);
            ds.delete(usersEntity.getLogin());
        } catch (DBException e) {
            e.printStackTrace();
            Assertions.fail("Ошибка доната");
        }
    }
}


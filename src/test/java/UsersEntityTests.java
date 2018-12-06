import entity.UsersEntity;
import exception.DBException;
import org.junit.jupiter.api.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import service.UserService;
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
}

import entity.UsersEntity;
import exception.DBException;
import org.junit.jupiter.api.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import service.UserService;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UsersEntityTests {
    private UserService ds;
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
        usersEntity.setLogin("3d");
        usersEntity.setName("dipidor");
        usersEntity.setSurname("ffkgf");
        usersEntity.setPassword("danxyi");
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
        usersEntity.setLogin("3d");
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
            user =ds.get("3d");
        } catch (DBException e) {
            e.printStackTrace();
            Assertions.fail("Ошибка изъятия объекта");
        }
        Assertions.assertNotNull(user);
        Assertions.assertEquals("3d", user.getLogin());
    }

    @Test
    void  updateUser(){
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setLogin("3d");
        usersEntity.setName("dipidor");
        usersEntity.setSurname("ffkgf");
        usersEntity.setPassword("updated");
        try {
            ds.update(usersEntity);
        } catch (DBException e) {
            e.printStackTrace();
            Assertions.fail("Ошибка обновления объекта");
        }
        try {
            usersEntity=ds.get("3d");
        } catch (DBException e) {
            e.printStackTrace();
            Assertions.fail("Ошибка изъятия объекта");
        }
        Assertions.assertEquals("updated",usersEntity.getPassword());
    }
}

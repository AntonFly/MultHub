import dao.DaoFactory;
import entity.*;
import exception.DBException;
import org.junit.jupiter.api.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import service.ProjectService;
import service.UserService;

import java.math.BigInteger;
import java.sql.Timestamp;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProjectsServiceTests {
    private ProjectService ps;
    String login = "3d";
    String newPassword = "updated";

    static void main(String[] args) {
        JUnitCore runner = new JUnitCore();
        Result result = runner.run(UsersEntityTests.class);
        System.out.println("run tests: " + result.getRunCount());
        System.out.println("failed tests: " + result.getFailureCount());
        System.out.println("ignored tests: " + result.getIgnoreCount());
        System.out.println("success: " + result.wasSuccessful());
    }

    @BeforeAll
    void init() {
        ps = new ProjectService();
    }

    @Test
    void addProject() {
        ProjectsEntity pe = new ProjectsEntity();
        pe.setProjectid(null);
        pe.setCurbudget(12);
        pe.setDescription("V 1999 GODU rodilsa divan i vosstal");
        pe.setGoalbudget(13);
        pe.setName("Vosstanie mashine");
        try {
            ps.create(pe);
            System.out.println(pe.getProjectid());
            System.out.println(ps.get(pe.getProjectid()).getName());
        } catch (DBException e) {
            e.printStackTrace();
            Assertions.fail("Ошибка добавления объекта");
        }

    }

}
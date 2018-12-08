import dao.DaoFactory;
import entity.*;
import exception.DBException;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runners.MethodSorters;
import service.ProjectService;
import service.UserService;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.UUID;

//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProjectsServiceTests {
    private ProjectService ps;
    static ProjectsEntity pe;

    static void main(String[] args) {
        JUnitCore runner = new JUnitCore();
        Result result = runner.run(ProjectsServiceTests.class);
    }

    @BeforeAll
    void init() {
        ps = new ProjectService();
        pe = new ProjectsEntity();
        pe.setProjectid(null);
        pe.setCurbudget(12.);
        pe.setDescription("V 1999 GODU;lkjh rodilsa divan i vosstal");
        pe.setGoalbudget(13.);
        pe.setName("Vosstanie mashine");
    }

    @Test
    void getProject() {
        try {
            System.out.println("UPDATED:" +ps.get(UUID.nameUUIDFromBytes((pe.getName()+pe.getDescription()).getBytes()).toString()).getName());

        } catch (DBException e) {
            e.printStackTrace();
            Assertions.fail("Ошибка добавления объекта");
        }
    }
    @Test
    void addProject() {
        try {
            ps.create(pe);
        } catch (DBException e) {
            e.printStackTrace();
            Assertions.fail("Ошибка добавления объекта");
        }
    }

//    @Test
//    void deleteProject(){
//
//        try {
//            ps.delete(UUID.nameUUIDFromBytes((pe.getName()+pe.getDescription()).getBytes()).toString());
//
//        } catch (DBException e) {
//            e.printStackTrace();
//            Assertions.fail("Ошибка добавления объекта");
//        }
//    }

//    @Test
//    void updateProject(){
//        pe.setName("LOL CHANGED");
//        try {
//            ps.update(pe);
//            System.out.println(pe.getProjectid());
//            System.out.println(ps.get(pe.getProjectid()).getName());
//        } catch (DBException e) {
//            e.printStackTrace();
//            Assertions.fail("Ошибка добавления объекта");
//        }
//
//    }



}
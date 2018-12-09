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
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProjectsServiceTests {
    private ProjectService ps;
    static ProjectsEntity pe;
    static CommitsEntity commitsEntity;

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
        pe.setDescription("TEST");
        pe.setGoalbudget(13.);
        pe.setName("TEST");

    }

    @Test
    void getProject() {
        try {
            addProject();
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

    @Test
    void deleteProject(){

        try {
            addProject();
            ps.delete(UUID.nameUUIDFromBytes((pe.getName()+pe.getDescription()).getBytes()).toString());

        } catch (DBException e) {
            e.printStackTrace();
            Assertions.fail("Ошибка добавления объекта");
        }
    }

    @Test
    void commitFiles(){
        try {
//            ps.create(pe);

            commitsEntity = new CommitsEntity();
            commitsEntity.setApproved(Approved.AWAITS);
            commitsEntity.setDeveloper("Usherb ebanii");
            commitsEntity.setId(null);
            commitsEntity.setProjectid(pe.getProjectid());
            commitsEntity.setTime(new Timestamp(System.currentTimeMillis()));

            List<CommitsfileEntity> commitsfileEntities = new LinkedList<>();
            CommitsfileEntity commitsfileEntity1 = new CommitsfileEntity();
            commitsfileEntity1.setCommitid( UUID.nameUUIDFromBytes(   (commitsEntity.getDeveloper()+commitsEntity.getProjectid()+commitsEntity.getTime())   .getBytes()  ).toString());
            commitsfileEntity1.setFilename("pizda ebanaya cherez rot mangusta blyat");
            commitsfileEntity1.setFilepath("epta");
            CommitsfileEntity commitsfileEntity2 = new CommitsfileEntity();
            commitsfileEntity2.setCommitid( UUID.nameUUIDFromBytes(   (commitsEntity.getDeveloper()+commitsEntity.getProjectid()+commitsEntity.getTime())   .getBytes()  ).toString());
            commitsfileEntity2.setFilename("pizda ebanaya cherez rot mangusta blyat2");
            commitsfileEntity2.setFilepath("epta");
            CommitsfileEntity commitsfileEntity3 = new CommitsfileEntity();
            commitsfileEntity3.setCommitid( UUID.nameUUIDFromBytes(   (commitsEntity.getDeveloper()+commitsEntity.getProjectid()+commitsEntity.getTime())   .getBytes()  ).toString());
            commitsfileEntity3.setFilename("pizda ebanaya cherez rot mangusta blyat3");
            commitsfileEntity3.setFilepath("epta");
            commitsfileEntities.add(commitsfileEntity1);
            commitsfileEntities.add(commitsfileEntity2);
            commitsfileEntities.add(commitsfileEntity3);

            ps.commitFiles(commitsEntity,commitsfileEntities);
            //ps.deleteCommitFile(commitsfileEntity3);         IMPORTANT THING
            ps.delete(UUID.nameUUIDFromBytes((pe.getName()+pe.getDescription()).getBytes()).toString()); //удаляет проект


        } catch (DBException e) {
            e.printStackTrace();
            Assertions.fail("Ошибка добавления коммита");
        }
    }

    @Test
    void getCommits(){
        try {
            List<CommitsEntity> commits = ps.getUncheckedCommits();
            for (CommitsEntity d:commits) {
                System.out.println(d.getDeveloper());
            }

            deleteCommit();
            ps.delete(UUID.nameUUIDFromBytes((pe.getName()+pe.getDescription()).getBytes()).toString());

        } catch (DBException e) {
            e.printStackTrace();
            Assertions.fail("Ошибка получения коммитов");
        }
    }
    @Test
    void getFiles(){
        try {
            List<CommitsfileEntity> files = ps.getCommitFiles(commitsEntity);
            for (CommitsfileEntity d:files) {
                System.out.println(d.getFilename());
            }
        } catch (DBException e) {
            e.printStackTrace();
            Assertions.fail("Ошибка получения коммитов");
        }
    }

    void aprove_rejectCommit(){

    }


    void deleteCommit(){
        try {
            ps.deleteCommit(commitsEntity);

        } catch (DBException e) {
            e.printStackTrace();
            Assertions.fail("Ошибка получения коммитов");
        }
    }

    @Test
    void addDeveloper(){

    }

    @Test
    void deleteDeveloper(){

    }
    @Test
    void sendInvite(){}

    static ProjectpostsEntity projectpostsEntity;
    @Test
    void addPostToBlog(){
        try {
            //ps.create(pe);
            projectpostsEntity = new ProjectpostsEntity();
            projectpostsEntity.setId(null);
            projectpostsEntity.setFilepath("aaaaa");
            projectpostsEntity.setProjectid(pe.getProjectid());
            projectpostsEntity.setText("LOL ORU");
            projectpostsEntity.setTime(new Timestamp(System.currentTimeMillis()));
            ps.addPostToBlog(projectpostsEntity);
        } catch (DBException e) {
            e.printStackTrace();
            Assertions.fail("Ошибка получения коммитов");
        }
    }
    @Test
    void deletePost(){
        try {
            ps.deletePostInBlog(projectpostsEntity);
        } catch (DBException e) {
            e.printStackTrace();
            Assertions.fail("Ошибка получения коммитов");
        }
    }

    @Test
    void approveRequest_addDev(){

    }

    @Test
    void addCredit(){

    }

    @Test
    void deleteCredit(){

    }

}
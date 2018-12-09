package service;

import entity.FollowersEntity;
import entity.ProjectsEntity;
import entity.UserpostEntity;
import exception.DBException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

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
}
import java.util.List;

public class UserService {

    private UsersDao usersDao = new UsersDao();

    public UserService() {
    }

    public Users findUser(String login) {
        return usersDao.findByLogin(login);
    }

    public void saveUser(Users user) {
        usersDao.save(user);
    }

    public void deleteUser(Users user) {
        usersDao.delete(user);
    }

    public void updateUser(Users user) {
        usersDao.update(user);
    }




}

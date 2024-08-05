package Sql;
import java.sql.Connection;
import java.sql.SQLException;

public class UserDaoTest {

    public static void main(String[] args) {
        try {

            Connection con = connection_class.connect_to_db("new_db", "postgres", "postgres");
            UserDao userDao = new UserDao();


            userDao.createUser("Alice", "alice@example.com", con);
            System.out.println("User created.");


//            List<User> users = userDao.readUsers(con);
//            System.out.println("Users in database:");
//            for (User user : users) {
//                System.out.println(user);
//            }
//
//
//            if (!users.isEmpty()) {
//                User user = users.get(0);
//                userDao.updateUser(user.getId(), "Alice Updated", "alice.updated@example.com", con);
//                System.out.println("User updated.");
//            }


//            if (!users.isEmpty()) {
//                User user = users.get(0);
//                userDao.deleteUser(user.getId(), con);
//                System.out.println("User deleted.");
//            }

            // Cleanup
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

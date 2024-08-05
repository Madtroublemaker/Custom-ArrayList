package Sql;
import java.sql.Connection;
import java.sql.SQLException;

public class MyArrayListTest {

    public static void main(String[] args) {
        try {

            Connection con = connection_class.connect_to_db("new_db", "postgres", "postgres");
            UserDao userDao = new UserDao();
            MyArrayList myArrayList = new MyArrayList(userDao.readUsers(con), userDao, con);


            User newUser = new User(0, "Bob", "bob@example.com");
            myArrayList.add(newUser);
            System.out.println("Added User: " + newUser);


            System.out.println("User at index 0: " + myArrayList.get(0));

            // Test set
            User updatedUser = new User(myArrayList.get(0).getId(), "Bob Updated", "bob.updated@example.com");
            myArrayList.set(0, updatedUser);
            System.out.println("Updated User: " + updatedUser);

            // Test remove
            myArrayList.remove(0);
            System.out.println("User removed.");

            // Test clear
            myArrayList.clear();
            System.out.println("All users cleared.");

            // Cleanup
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

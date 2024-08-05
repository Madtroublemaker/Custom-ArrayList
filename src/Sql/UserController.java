package Sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserController {

    public static Connection con;
    public static UserDao userDao;


    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        userDao = new UserDao();
        con = connection_class.connect_to_db("new_db", "postgres", "postgres");



        while(true){
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice ==0){
                exit();
                break;
            }

            switch (choice) {
                case 1:
                    createUser(scanner);
                    break;
                case 2:
                    readUsers();
                    break;
                case 3:
                    updateUser(scanner);
                    break;
                case 4:
                    deleteUser(scanner);
                    break;
                case 5:
                    exit();
                    return;
                default:
                    System.out.println("Invalid choice");
            }

        }
    }

    private static void printMenu(){
        System.out.println("Hello please selct option");
        System.out.println("1 Create User");
        System.out.println("2 Read Users");
        System.out.println("3 Update User");
        System.out.println("4 Delete User");
        System.out.println("5 Exit");
        System.out.print("Enter your choice: ");
    }

    private static void createUser(Scanner scanner){
        System.out.println("Enter name:");
        String name = scanner.nextLine();
        System.out.println("Enter email:");
        String email = scanner.nextLine();

        userDao.createUser(name, email, con);
    }
    private static void readUsers(){
        List<User> users = userDao.readUsers(con);
        for (User user: users){
            System.out.println(user);
        }
    }
    private static void updateUser(Scanner scanner) throws SQLException {
        System.out.println("Enter id");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter name");
        String name = scanner.nextLine();
        System.out.println("Enter email");
        String email = scanner.nextLine();

        userDao.updateUser(id, name, email, con);
;    }
    private static void deleteUser(Scanner scanner) throws SQLException {
        System.out.println("Enter user id to delete:");
        int id = scanner.nextInt();
        scanner.nextLine();
        userDao.deleteUser(id, con);
    }
    private static void exit(){
        try {
            if (con != null && !con.isClosed()){
                con.close();
            }
            System.out.println("exiting");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

}

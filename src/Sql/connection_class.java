package Sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connection_class {

    public static void main(String[] args) throws SQLException {


    }
    public static Connection connect_to_db(String dbname, String user, String pass)
    {
        Connection con_obj=null;
        String url="jdbc:postgresql://localhost:5432/";

        try
        {
            Class.forName("org.postgresql.Driver");
            con_obj= DriverManager.getConnection(url+dbname,user,pass);
            if(con_obj!=null)
            {
                System.out.println("Connection established successfully !");
            }
            else
            {
                System.out.println("Connection failed !!");
            }

            UserDao userDao = new UserDao();

            String name = "John Doe";
            String email = "john.doe@e.com";
            userDao.createUser(name, email, con_obj);

            System.out.println("connect.User created successfully!");

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return con_obj;
    }
}

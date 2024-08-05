package Sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserDao {
    public void createUser(String name, String email, Connection con) {
        String createUserSql = "insert into users(name, email) values(?,?)";

        try(
        PreparedStatement pstmt = con.prepareStatement(createUserSql)){
            pstmt.setString(1,name);
            pstmt.setString(2, email);
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(int id, String name, String email, Connection con) throws SQLException {
        String updateUserSql ="update users set name = ?, email=? where id = ?";

        try (
                PreparedStatement pstmt = con.prepareStatement(updateUserSql)){
                pstmt.setString(1, name);
                pstmt.setString(2,email);
                pstmt.setInt(3,id);
                pstmt.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public User deleteUser(int id, Connection con) throws SQLException {
        String deleteUserSql = "delete from users where id =?";

        try(
                PreparedStatement pstmt = con.prepareStatement(deleteUserSql)){
            pstmt.setInt(1,id);
            pstmt.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<User> readUsers(Connection con){
        String readUsersSql = "select * from users";
        List<User> userList = new ArrayList<>();
        try(
                PreparedStatement pstmt = con.prepareStatement(readUsersSql);
                ResultSet rs = pstmt.executeQuery()){
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                User user = new User(id, name, email);
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }


}

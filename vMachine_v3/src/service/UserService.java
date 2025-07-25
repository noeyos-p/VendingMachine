package service;

import db.DBConn;
import dto.User;
import view.UserView;

import java.sql.*;

public class UserService {
    Connection conn = DBConn.getConnection();
    PreparedStatement psmt = null;
    String sql;


    public int newMember(User user) {
        try {
            sql = "INSERT INTO user(name, phone_num, card_num, password, money) ";
            sql = sql + "VALUES(?, ?, ?, ?, ?)";

            psmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            psmt.setString(1, user.getName());
            psmt.setString(2, user.getPhoneNum());
            psmt.setString(3, user.getCardNum());
            psmt.setString(4, user.getPassword());
            psmt.setInt(5, user.getMoney());

            int result = psmt.executeUpdate();

            if (result == 1) {
                ResultSet rs = psmt.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    return rs.getInt(1);
                }
                rs.close();
            }
            psmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    public boolean checkCard(String cardNum) {
        try {
            sql = "SELECT COUNT(*) FROM user WHERE card_num = ? ";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, cardNum);
            ResultSet rs = psmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            rs.close();
            psmt.close();

            return count > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public User checkID(int id) {
        try {
            sql = "SELECT COUNT(*) FROM user WHERE id = ?";
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, id);
            ResultSet rs = psmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            rs.close();
            psmt.close();

            if (count == 0) {
                System.out.println("존재하지 않는 아이디입니다.");
                return null;
            }
            return new User();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public User checkPass(int id, String password) {
        try {
            sql = "SELECT * FROM user WHERE id = ? AND password = ? ";
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, id);
            psmt.setString(2, password);
            ResultSet rs = psmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPhoneNum(rs.getString("phone_num"));
                user.setCardNum(rs.getString("card_num"));
                user.setPassword(rs.getString("password"));
                user.setMoney(rs.getInt("money"));
                rs.close();
                return user;
            }
            rs.close();
            psmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void updateMoney(int userId, int modifyMoney) {
        try {
            sql = "UPDATE user SET money = ? WHERE id = ?";
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, modifyMoney);
            psmt.setInt(2, userId);
            psmt.executeUpdate();
            psmt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}





package service.settingView.service;

import db.DBConn;
import dto.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserSettingService {
    Connection conn = DBConn.getConnection();
    public PreparedStatement psmt = null;
    public ResultSet rs = null;
    public String sql = null;

    public User checkUser(int userId) {
        User user = new User();
        try {
            sql = "SELECT * FROM user WHERE id = ? ";
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, userId);
            rs = psmt.executeQuery();
            if (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPhoneNum(rs.getString("phone_num"));
                user.setCardNum(rs.getString("card_num"));
                user.setPassword(rs.getString("password"));
                user.setMoney(rs.getInt("money"));
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    public void updateUser(int userId, String mysql, String modify) {
        List<String> allowedColumns = List.of("name", "phone_num", "password", "card_num");

        if (!allowedColumns.contains(mysql)) {
            System.out.println("수정할 수 없는 항목입니다.");
            return;
        }
        try {
            sql = "UPDATE user SET " + mysql + " = ? WHERE id = ? ";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, modify);
            psmt.setInt(2, userId);
            psmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteUser(int id) {
        try {
            sql = "DELETE FROM user WHERE id = ? ";
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, id);
            psmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

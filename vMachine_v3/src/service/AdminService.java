package service;

import db.DBConn;
import dto.Menu;
import dto.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminService {
    private List<User> users = new ArrayList<>();

    public Connection conn = DBConn.getConnection();
    public PreparedStatement psmt = null;
    public ResultSet rs = null;
    public String sql = null;

    Scanner sc = new Scanner(System.in);

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();
        try {
            sql = "SELECT *  FROM user";
            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPhoneNum(rs.getNString("phone_num"));
                user.setPassword(rs.getString("password"));
                user.setCardNum(rs.getString("card_num"));
                user.setMoney(rs.getInt("money"));
                users.add(user);
            }
            psmt.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return users;
    }


}

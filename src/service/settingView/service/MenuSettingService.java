package service.settingView.service;

import db.DBConn;
import dto.Menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MenuSettingService {
    public Connection conn = DBConn.getConnection();
    public PreparedStatement psmt = null;
    public ResultSet rs = null;
    public String sql = null;
    public boolean addMenuService(Menu newMenu) {
        try {
            sql = "INSERT INTO menu (name, price, stock) VALUES (?, ?, ?)";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, newMenu.getName());
            psmt.setInt(2, newMenu.getPrice());
            psmt.setInt(3, newMenu.getStock());
            int result = psmt.executeUpdate();
            psmt.close();
            return result == 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean updateMenuService(Menu menu) {
        try {
            sql = "UPDATE menu SET name = ?, price = ?, stock = ? WHERE id = ? ";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, menu.getName());
            psmt.setInt(2, menu.getPrice());
            psmt.setInt(3, menu.getStock());
            psmt.setInt(4, menu.getId());

            int result = psmt.executeUpdate();
            psmt.close();
            return result == 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void deleteMenuService(int id) {
        try {
            sql = "DELETE FROM menu WHERE id = ? ";
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, id);
            psmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

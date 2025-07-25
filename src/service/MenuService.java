package service;

import db.DBConn;
import dto.Menu;
import dto.User;
import view.UserView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuService {
    Connection conn = DBConn.getConnection();
    PreparedStatement psmt = null;
    String sql;
    Menu menu = new Menu();
    UserService userService = new UserService();
    private List<Menu> menus = new ArrayList<>();

    public List<Menu> allMenu() {
        List<Menu> menus = new ArrayList<>();
        try {
            sql = "SELECT *  FROM menu";
            psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();

            while (rs.next()) {
                Menu menu = new Menu();
                menu.setId(rs.getInt("id"));
                menu.setName(rs.getString("name"));
                menu.setPrice(rs.getInt("price"));
                menu.setStock(rs.getInt("stock"));
                menus.add(menu);
            }
            psmt.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return menus;
    }

    public Menu checkMenu(int id) {
        try {
            sql = "SELECT * FROM menu WHERE id = ? ";
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, id);
            ResultSet rs = psmt.executeQuery();

            if (rs.next()) {
                menu.setId(rs.getInt("id"));
                menu.setName(rs.getString("name"));
                menu.setPrice(rs.getInt("price"));
                menu.setStock(rs.getInt("stock"));
            }
            rs.close();
            psmt.close();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return menu;
    }

    public void saleMenu(User user, Menu check) {
        int modifyStock = menu.getStock() - 1;
        int modifyMoney = user.getMoney() - check.getPrice();
        updateStock(check.getId(), modifyStock);
        user.setMoney(modifyMoney);
        userService.updateMoney(user.getId(), modifyMoney);
        saleRecord(user.getId(), menu.getId(), check.getPrice());
    }

    public void updateStock(int menuId, int modifyStock) {
       try {
           sql = "UPDATE menu SET stock = ? WHERE id = ? ";
           psmt = conn.prepareStatement(sql);
           psmt.setInt(1, modifyStock);
           psmt.setInt(2, menuId);
           psmt.executeUpdate();
           psmt.close();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
    }

    public void saleRecord(int userId, int menuId, int price) {
        try {
            sql = "INSERT INTO sales (user_id, menu_id, total_price) VALUES (?, ? , ?) ";
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, userId);
            psmt.setInt(2, menuId);
            psmt.setInt(3, price);
            psmt.executeUpdate();
            psmt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

package sales;

import db.DBConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MenuSales {
    Connection conn = DBConn.getConnection();
    PreparedStatement psmt = null;
    String sql;
    public List<MenuSales> menuSalesStatus() {
        List<MenuSales> result = new ArrayList<>();

        try {
            String sql = "SELECT menu_id, COUNT(*) AS count, SUM(total_price) AS sum FROM sales GROUP BY menu_id";
            psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();

            while (rs.next()) {
                MenuSales ms = new MenuSales();
                ms.setMenuId(rs.getInt("menu_id"));
                ms.setCount(rs.getInt("count"));
                ms.setSum(rs.getInt("sum"));
                result.add(ms);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
        }

        return result;
    }

}


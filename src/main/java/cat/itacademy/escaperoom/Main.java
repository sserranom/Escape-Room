package cat.itacademy.escaperoom;


import cat.itacademy.escaperoom.database.mysql.MySqlConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {

        Statement stmt = null;
        ResultSet rs = null;


        Connection conn = MySqlConnection.getInstance();

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM patata");

            // or alternatively, if you don't know ahead of time that
            // the query will be a SELECT...

            if (stmt.execute("SELECT * FROM patata")) {
                rs = stmt.getResultSet();
                System.out.println("ResultSet");
                System.out.println(rs.next());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

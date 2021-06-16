/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author GMT
 */
public class SQLSingleton {

    private static SQLSingleton shared = null;
    private String dbURL;
    private final String databaseName = "ExamApp";
    private final String user = "sa";
    private final String password = "hien121101";

    private SQLSingleton() {
        dbURL = String.format("jdbc:sqlserver://localhost;databaseName=%s;user=%s;password=%s", databaseName,
                user, password);
    }

    public static SQLSingleton getInstance() {
        if (shared == null) {
            shared = new SQLSingleton();
        }
        return shared;
    }

    public <T extends Decoder> void query(String queryString, Supplier<T> supplier, Callback completion) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Connection conn = DriverManager.getConnection(dbURL);
                    if (conn != null) {
                        // Tạo đối tượng Statement.
                        Statement statement = conn.createStatement();
                        // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
                        ResultSet rs = statement.executeQuery(queryString);

                        List<T> results = new ArrayList<>();
                        Container container = new Container(supplier);
                        while (rs.next()) {
                            List<String> keys = container.createContents().getKeys();
                            HashMap<String, Object> map = new HashMap<>();
                            for (String key : keys) {
                                map.put(key, rs.getObject(key));
                            }
                            var decoder = container.createContents().clone();
                            ((T) decoder).setValues(map);
                            results.add((T) decoder);
                        }

                        conn.close();

                        completion.getDataDidComplete(results);
                        return;
                    }
                } catch (SQLException ex) {
                    System.err.println("Cannot connect database, " + ex);
                    completion.getDataDidComplete(null);
                }
            }
        });
        t.start();
        System.out.println("starting query to SQL server.");
    }

    public <T extends Decoder> List<T> query(String queryString, Supplier<T> supplier) {
        try {
            Connection conn = DriverManager.getConnection(dbURL);
            if (conn != null) {
                // Tạo đối tượng Statement.
                Statement statement = conn.createStatement();
                // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
                ResultSet rs = statement.executeQuery(queryString);

                List<T> results = new ArrayList<>();
                Container container = new Container(supplier);
                while (rs.next()) {
                    List<String> keys = container.createContents().getKeys();
                    HashMap<String, Object> map = new HashMap<>();
                    for (String key : keys) {
                        map.put(key, rs.getObject(key));
                    }
                    var decoder = container.createContents().clone();
                    ((T) decoder).setValues(map);
                    results.add((T) decoder);
                }

                conn.close();
                
                return results;
            }
        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
        }
        return null;
    }

    public interface Callback<T extends Decoder> {
        void getDataDidComplete(List<T> data);
    }
}

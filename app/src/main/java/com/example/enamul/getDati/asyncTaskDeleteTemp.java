package com.example.enamul.getDati;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.StrictMode;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.enamul.qrcode.MainActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class asyncTaskDeleteTemp extends AsyncTask<Void, Void, Void> {

    private int j = 0;

    Connection con;
    Statement st = null;
    ResultSet rs = null;

    String user = "root";
    String password = "p5naKHD[7nyz8.-2";

    String url = "jdbc:mysql://localhost:3306/lista_capodanno";

    @Override
    protected Void doInBackground(Void...voids) {

        try {

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            st.executeUpdate("TRUNCATE temporaryList");

        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger lgr = Logger.getLogger(Build.VERSION.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Build.VERSION.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
        return null;
    }

}

package com.example.enamul.getDati;

import android.os.AsyncTask;
import android.os.Build;
import android.os.StrictMode;

import com.example.enamul.qrcode.MainActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class asyncTaskPutTemporaryList extends AsyncTask<String, Void, String[]> {

    private int j = 0;

    Connection con;
    Statement st = null;
    ResultSet rs = null;

    String user = "root";
    String password = "p5naKHD[7nyz8.-2";

    String url = "jdbc:mysql://localhost:3306/lista_capodanno";

    @Override
    protected String[] doInBackground(String...strings) {

        String[] tot = new String[300];
        String resultRaw;

        try {

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            if (strings!=null) {
                st.executeUpdate("INSERT INTO temporaryList (tempElem) VALUES " + "('"
                        + strings[0].toString() + "')");
            }
            rs = st.executeQuery("SELECT * FROM temporaryList");
            while(rs.next()){
                resultRaw = rs.getString("tempElem");
                tot[j]=resultRaw;
                j++;
            }

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
        return tot;
    }
    @Override
    protected void onPostExecute(String[] s){
        Dati d = new Dati();
        d.setDatiTemp(s);
    }
}

package com.example.enamul.getDati;

import android.os.AsyncTask;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class getDati extends AsyncTask <Void, Void, String[]> {

        private String[] codici = new String[120];
        private String[] nomi = new String[120];
        private String[] cognomi = new String[120];
        private Integer[] importi_pagati = new Integer[120];
        private int j = 0;

        Connection con;
        Statement st = null;
        ResultSet rs = null;

        String user = "root";
        String password = "p5naKHD[7nyz8.-2";

        String url = "jdbc:mysql://localhost:3306/lista_capodanno";


        @Override
        protected String[] doInBackground(Void... voids) {
                String[] tot = new String[120];
                String resultRaw;
                //JSONArray sumRaw = new JSONArray();
                //JSONObject singleData = new JSONObject();
                try {

                        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                        StrictMode.setThreadPolicy(policy);

                        Class.forName("com.mysql.jdbc.Driver").newInstance();
                        con = DriverManager.getConnection(url, user, password);
                        st = con.createStatement();
                        rs = st.executeQuery("SELECT * FROM Invitati_cena");

                        while (rs.next()) {
                                //singleData.put("Codice", rs.getString("Codice"));
                                //singleData.put("Nome", rs.getString("Nome"));
                                //singleData.put("Cognome", rs.getString("Cognome"));
                                //singleData.put("Importo pagato", rs.getInt("Importo_pagato"));
                                //Log.e("numero: " + j, "aggiunto: " + codici[j]);
                                resultRaw = rs.getString("Codice") + " " + rs.getString("Nome") + " " + rs.getString("Cognome") + " " + rs.getInt("Importo_pagato");
                                     tot[j]=resultRaw;
                                     //
                                //sumRaw.put(j,singleData);
                                //result.add(j,sumRaw);
                                j++;
                        }


                } catch (SQLException | ClassNotFoundException ex) {
                        Logger lgr = Logger.getLogger(Build.VERSION.class.getName());
                        lgr.log(Level.SEVERE, ex.getMessage(), ex);

                } catch (IllegalAccessException e) {
                        e.printStackTrace();
                } catch (InstantiationException e) {
                        e.printStackTrace();
                } catch (NullPointerException e) {
                        e.printStackTrace();
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
                        } catch (NullPointerException e) {
                                e.printStackTrace();
                        }
                }

               return tot;
        }

        @Override
        protected void onPostExecute(String[] result) {

                for(int k=0;k<result.length;k++){
                        if(result[k]!=null) {
                                //Log.e("numero: " + k, "aggiunto: " + result[k]);
                                Classe.dati[k]=result[k];
                                //Log.e("numero: " + k, "aggiunto: " + Classe.dati[k]);
                        }
                        else{
                                break;
                        }
                }

        }

        /*public String[] getCod() {
                return codici;
        }

        public String[] getNomi() {
                return nomi;
        }

        public String[] getCognomi() {
                return cognomi;
        }

        public Integer[] getImportipagati() {
                return importi_pagati;
        }*/

}





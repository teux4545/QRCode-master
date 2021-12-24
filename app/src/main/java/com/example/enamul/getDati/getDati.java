package com.example.enamul.getDati;

import org.apache.poi.Version;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class getDati {

        private final String[] codici = new String[0];
        private final String[] nomi = new String[0];
        private final String[] cognomi = new String[0];
        private final Integer[] importi_pagati = new Integer[0];
        public int j = 0;

        public void Dati() {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        String url = "jdbc:jtds:sqlserver://localhost:3306/lista_capodanno";
        String user = "root";
        String password = "p5naKHD[7nyz8.-2";

        try {
                Class.forName("net.sourceforge.jtds.jdbc.Driver");
                con = DriverManager.getConnection(url, user, password);
                st = con.createStatement();
                rs = st.executeQuery("SELECT * FROM Invitati_cena");

                while (rs.next()) {
                        codici[j]=rs.getString("Codici");
                        nomi[j]=rs.getString("Nome");
                        cognomi[j]=rs.getString("Cognome");
                        importi_pagati[j]=rs.getInt("Importo_pagato");
                        j++;
                }

        } catch (SQLException | ClassNotFoundException ex) {
                Logger lgr = Logger.getLogger(Version.class.getName());
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
                        Logger lgr = Logger.getLogger(Version.class.getName());
                        lgr.log(Level.WARNING, ex.getMessage(), ex);
                }
        }
        }
        public String[] getCodici() {
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
        }
        
}

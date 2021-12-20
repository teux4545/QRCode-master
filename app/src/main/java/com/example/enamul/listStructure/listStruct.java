package com.example.enamul.listStructure;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class listStruct {

        private String[] codici = new String[0];
    private String[] nomi = new String[0];
    private String[] cognomi = new String[0];
    private Integer[] importi_pagati;
            /*{
            "Zds^03fdV&Ym",
            "X3epsLutqU#!",
            "rnOxiE4Y5fcQ",
            "pdxlWoD3sSsO",
            "jb6zBqMkGCQt",
            "RAa38vB0OcpN",
            "cnl6J8bxYwx2",
            "plj3iBTkeaRv",
            "tdfz0Ljdnwuc",
            "edJGsRYgqLOa",
            "vdYRCeKTKLsB",
            "vkvLFQwJdazL",
            "IxnJSXxNcVgt",
            "G4NMnc32aWcz",
            "H3cTErUQlu6W",
            "9fRDl0dEoq25",
            "AOpn6pvRi3QX",
            "xNgWEs4riE72",
            "UoqMOrUe3wBL",
            "FRPhKFjCMa6s",
            "AXj36XEsZ8ol",
            "5dmq0LmVXcIK",
            "ObGcOmhRlfLN",
            "If2n1PFLfpEa",
            "MnTv9N1zvVfZ",
            "GoBRQGSlpzyw",
            "zTRIdkrceGWt",
            "xz8rar4wDd1f",
            "ZHdMz8iyycFs",
            "BIXxBn6UD16f",
            "HBkIyxDqPTpW",
            "KIopHazirlHc",
            "nx0p2H62rGKd",
            "OHZFcoMXpeX9",
            "BvCzAw0iHfLV",
            "6oqxVQnUi3CZ",
            "vWB6layZ6g8C",
            "cMsO8OOe3Xyx",
            "Y4XMct9elOiK",
            "2xoRpHJo861B",
            "gzPkOE9pRM9e",
            "XvjdOdNpBs1w",
            "OCtO385wNrym",
            "fHI6NeSUmWsu",
            "SyvWNZoGg5le",
            "1gaELepxmv0X",
            "pY9n0wrc5swY",
            "abFTqVdIgVDn",
            "BTZ3Opb64yiH",
            "2RpxfXH96JJT",
            "HQ2M89EC56EB",
            "01B3Qe72eCIG",
            "uy8ajGrfRIHJ",
            "NoYJU4iAJ2VH",
            "Tv5twHTjut7y",
            "Lhoazi8VEJQN",
            "vhAyH52AYxtR",
            "LJovC0HM2d3j",
            "BpryOAyfauvX",
            "1qcM1YUDbsMf",
            "m7cdj8TV1MCE",
            "qmLuhDdFGKvt",
            "ZwLerOdvpnCA",
            "dSFNd1w1zzvy",
            "UkvJhtUTJh4l",
            "kpcqXy7mPieE",
            "w5pXPBYhloFO",
            "qAZFW5XWIPdR",
            "1MW2kEc4zRM7",
            "FdfUg3UzkL4o",
            "mCYDwT7em9pD",
            "VqmX14ZY5YRs",
            "F3ujGI8QrzQn",
            "xwW4wXzsVGW5",
            "Sf5zCdmIZF0m",
            "HPHY6eL8h9TA",
            "hd86NwWhu7XA",
            "9ZZn43xvP17G",
            "WgPKe7XBBlUd",
            "6XVpgDoONbhy",
            "UOCxXqHiupMP",
            "QQRn15CyGy5J",
            "EslnQL8WzXRA",
            "A45Mifl3gDd6",
            "czsDioxdbNj4",
            "XiDSU80zh4zC",
            "cWoF0xiIn56y",
            "S6l71m3Bvxdn",
            "uiDkjX8QVYnp",
            "iQQ40rsC5zJ1",
            "QxSvyDkbFHjQ",
            "lYrgh2AUvMfo",
            "oPk1FMWoyFLe",
            "q2dKQKJog4XD",
            "mRsgpuJkXFEA",
            "IbjSzDmtLBEi",
            "nr51lsytL6nz",
            "l4k8mO8mmYvf",
            "HmLP9dfUXVlO",
            "FoZQ6SRbDN4j",
            "sTBYjJJhLxmG",
            "lefDcsGjV1pc",
            "hJHE4yVLWnWv",
            "lgh2VyoB1OVu",
            "6l9cfQ95j2ay",
            "0XM7LCkKcsYK",
            "lfoAHe4TAaHV",
            "abNqA8sPEAKl",
            "USkoWMiGWixw",
            "XJcpZDlqfV8i",
            "8Ruf7RjXBgTs",
            "wmxFP5t0sXvk",
            "vP1bkW7NjwLE",
            "HSbrI6lxDbyE",
            "ud4CqZ4TVexl",
            "o3i56wTBQlYL",
            "jQA3yHbcpeak",
            "RM0woOm7coa5",
            "VvvLuKdsX4My",
            "CUvV3MlUQjL6",
            "L64SsYctWz1M",
            "19T0oysClQJL",
            "UbPbNaR1kOan",
            "IGDqSeNfjkFz",
            "kS0TDCmGClit",
            "c1jqKTizLavn",
            "0PBS4oGcKuGz",
            "6TN3Q4eDz3KJ",
            "xVk6IxlKxHXS",
            "j9pEonT9fk66",
            "NIFHDPvczZE6",
            "moHsXgfmKM1O",
            "fGiGc6QLZlKg",
            "B1WQrOgioobU",
            "3Vyr9tc4lkaA",
            "oO9hYDQE3ZPP",
            "r1sQJcoXAvnz",
            "F4Dfocq9uCln",
            "CNgHSZwY8Qgg",
            "WhWRcc3z5KtM",
            "XK4u29n3m7Qw",
            "oRorps7P8Ob5",
            "DXDY4bCGrCct",
            "PkPNqq2FUFmc",
            "TNSN7YWcjNpu",
            "gJKmgx8yAOxO",
            "TEpYMjW6BNau",
            "MoPRQ9GhyPDc",
            "29sqOvpZqXd6",
            "QcongkInlas5",
            "gkdaYlO07bV4",
            "q8FPHlKfFcCR"
    };*/
        public void getDati(){
            Connection conn = null;
            String ip,port,user,pass,database;

            ip="localhost";
            port="8001";
            user="root";
            pass="p5naKHD[7nyz8.-2";
            database="lista_capodanno";

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            try{
                Class.forName("net.sourceforge.jtds.jdbc.Driver");
                String ConnectionURL="jdbc.jtds.sqlserver://" + ip + ":" + port + ";" + "databasename=" + database + ";uname=" + user + ";password="+pass+";";
                conn = DriverManager.getConnection(ConnectionURL);

                if (conn!=null){

                    String query="Select * from Invitati_cena";
                    Statement st = conn.createStatement();
                    ResultSet rs = st.executeQuery(query);
                    int j=0;

                    while (rs.next()){
                        codici[j]=rs.getString(1);
                        nomi[j]=rs.getString(2);
                        cognomi[j]=rs.getString(3);
                        importi_pagati[j]=rs.getInt(4);
                        j++;
                    }
                }
            }

            catch (Exception ex){
                Log.e("Error", ex.getMessage());
            }
        }

        public Boolean contain(String code){
            for(String cod : codici){
                if(code.contains(cod)){
                    return true;
                }
            }
            return false;
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

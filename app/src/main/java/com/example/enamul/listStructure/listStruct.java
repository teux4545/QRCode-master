package com.example.enamul.listStructure;

import android.util.Log;

public class listStruct {
    public int i;

        public Boolean contain(String code, String[] scanned){
            for(i=0;i < scanned.length;i++){
                    Log.e("Dato: ","c: " + scanned[i]);
                    if (scanned[i].contains(code)) {
                        return true;
                }
            }
            return false;
        }

}

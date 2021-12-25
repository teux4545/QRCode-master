package com.example.enamul.listStructure;

public class listStruct {

        public Boolean contain(String code, String[] scanned){
            for(String cod : scanned){
                if(cod!=null) {
                    if (cod.contains(code)) {
                        return true;
                    }
                }
            }
            return false;
        }

}

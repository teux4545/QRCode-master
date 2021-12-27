package com.example.enamul.listStructure;

public class listStruct {
    private int i;

        public Boolean contain(String code, String[] scanned){
               i=0;

            for(String s : scanned){
                if(s!=null && code!=null){
                String[] splitted = s.split(" ");
                    if (code.contains(splitted[0])) {
                        return true;
                    }
                }
                    i++;
            }
            return false;
        }

    public int getI() {
            return i;
    }
}

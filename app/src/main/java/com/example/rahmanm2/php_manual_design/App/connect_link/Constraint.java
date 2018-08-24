package com.example.rahmanm2.php_manual_design.App.connect_link;

import com.example.rahmanm2.php_manual_design.App.Connection_PHP.connect;

public class Constraint extends connect {
    public static String ROOT_URL = "http://192.168.252.124/Android_php_project/DataModel/crud.php";
    String urlll = "http://10.1.10.73/Android_php_project/DataModel/crud.php?operasi=view";
    String url = "";
    String response = "";
   public Constraint(){}
    public  String ViewData(){
        try{
            url = urlll;
            System.out.println("View data"+urlll);
            response = call(url);

        }catch(Exception e){
            e.printStackTrace();
        }
        return response;
    }


}

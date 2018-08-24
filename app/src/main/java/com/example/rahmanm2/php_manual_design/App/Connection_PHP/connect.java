package com.example.rahmanm2.php_manual_design.App.Connection_PHP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class connect {
    public String call(String url){

        int BUFFER_SIZE = 2000;
        InputStream in = null;
        try{
            in = OpenHttpConnection(url);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        InputStreamReader isr = new InputStreamReader(in);
        int chared;
        String str = "";
        char []inputBuffer = new char[BUFFER_SIZE];
        try{
            while ((chared = isr.read(inputBuffer))>0){
                String readString = String.copyValueOf(inputBuffer,0,chared);
                str += readString;
                inputBuffer = new char[BUFFER_SIZE];
            }
            in.close();
        }catch(Exception e){
            e.printStackTrace();
            return "";
        }

        return str;
    }

    private InputStream OpenHttpConnection(String url) throws IOException {
        InputStream in = null;
        int response = -1;
        URL url1 = new URL(url);
        URLConnection conn = url1.openConnection();
        if(!(conn instanceof  HttpURLConnection))
             throw new IOException("Error Not in HttpConnection");
        try{
            HttpURLConnection httpURLConnection = (HttpURLConnection) conn;
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true );
            httpURLConnection.connect();
            response = httpURLConnection.getResponseCode();
            if(response == HttpURLConnection.HTTP_OK)
            {
                in = httpURLConnection.getInputStream();
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new IOException("Error connecting2");
        }

        return in;
    }

    public String reader(String url){
        int BUFFER_SIZE = 2000;
        OutputStream in = null;
        try{
            in = OpenHttpConnectionRead(url);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(in));
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        return"";
    }
    private OutputStream OpenHttpConnectionRead(String url) throws IOException {
        OutputStream in = null;
        int response = -1;
        URL url1 = new URL(url);
        URLConnection conn = url1.openConnection();
        if(!(conn instanceof  HttpURLConnection))
            throw new IOException("Error Not in HttpConnection");
        try{
            HttpURLConnection httpURLConnection = (HttpURLConnection) conn;
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true );
            httpURLConnection.connect();
            response = httpURLConnection.getResponseCode();
            if(response == HttpURLConnection.HTTP_OK)
            {
                in = httpURLConnection.getOutputStream();
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new IOException("Error connecting2");
        }

        return in;
    }

}

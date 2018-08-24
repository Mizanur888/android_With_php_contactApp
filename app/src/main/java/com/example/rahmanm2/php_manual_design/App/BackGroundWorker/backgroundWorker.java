package com.example.rahmanm2.php_manual_design.App.BackGroundWorker;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import DataModel.InitData;

public class backgroundWorker extends AsyncTask<String,Void,String> {
    AlertDialog mAlertDialog;
    Context mContext;
    public backgroundWorker(Context ctx){
        this.mContext = ctx;
    }

    @Override
    protected String doInBackground(String... parms) {
        String type = parms[0];
        String LOGIN_URL ="http://10.1.10.73/Android_php_project/DataModel/crud.php?operasi=view";
        if(type.equals("view")){
            try {
                String id = parms[1];
                String name = parms[2];
                String age = parms[3];
                URL url = new URL(LOGIN_URL);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("id","UTF-8")+URLEncoder.encode(id,"UTF-8")+"&"+
                                   URLEncoder.encode("nama","UTF-8")+URLEncoder.encode(name,"UTF-8")+"&"+
                                   URLEncoder.encode("age","UTF-8")+URLEncoder.encode(age,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result = "";
                String Line = "";
                while((Line = bufferedReader.readLine())!=null){
                    result+=Line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        mAlertDialog = new AlertDialog.Builder(mContext).create();
       mAlertDialog.setTitle("Login Status");
    }

    @Override
    protected void onPostExecute(String s) {

        mAlertDialog.setMessage(s);
        mAlertDialog.show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}

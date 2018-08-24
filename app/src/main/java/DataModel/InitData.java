package DataModel;

import android.util.Log;

import java.util.ArrayList;

public class InitData {
    public static ArrayList<biodataModel>DataList = new ArrayList<biodataModel>();
    public biodataModel connect;
    private static String TAG = InitData.class.getSimpleName();
    public static ArrayList<biodataModel> setUpData(){

        DataList.add(new biodataModel("1","Mizanur","100"));
        DataList.add(new biodataModel("2","Mizanur","100"));
        DataList.add(new biodataModel("3","Mizanur","100"));
        DataList.add(new biodataModel("4","Mizanur","100"));
        return DataList;
    }
    public static ArrayList<biodataModel> displayData(biodataModel model){
         DataList.add(model);
         return DataList;
    }

    public static void DeleteBioData(int id){

        int number;
        for(int i = 0; i<DataList.size();i++){
            biodataModel model = DataList.get(i);
            number = Integer.parseInt(model.getID());
           if(id>=0 && number>=0){
               if(id == number){
                   DataList.remove(model);
                   Log.d(InitData.TAG,"Does Delete Suceessful");
               }
               else {
                   Log.d(TAG,"ID does not match");
               }
           }
           else{
               Log.d(InitData.TAG,"Please Enter Valied ID");
           }

        }
    }

    public static void UpdataBiodata(String id, String name, String age){
        int number = 0, num = 0;
        num = Integer.parseInt(id);
        for(int i = 0;i<DataList.size();i++){
            biodataModel model = DataList.get(i);
            number = Integer.parseInt(model.getID());

            if(num == number){
                model.setName(name);
                model.setAge(age);
                InitData.DataList.add(model);
            }
        }
    }
}

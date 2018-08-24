package com.example.rahmanm2.php_manual_design.App.App;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rahmanm2.php_manual_design.App.BackGroundWorker.backgroundWorker;
import com.example.rahmanm2.php_manual_design.App.connect_link.Constraint;
import com.example.rahmanm2.php_manual_design.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import DataModel.InitData;
import DataModel.biodataModel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TableLayout mTableLayout;
    Button mButton;
    ProgressDialog mProgressDialog;

    ArrayList<Button>EditButton = new ArrayList<Button>();
    ArrayList<Button>DeleteButton = new ArrayList<Button>();
    TableRow brisTable;
    JSONArray mJSONArray;
    biodataModel mBiodataModel;

    public ArrayList<biodataModel> mBiodataModelArrayList;
    public ArrayList<biodataModel>newDataModel;

    public Constraint mConstraint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize Biodata here
        //biodataModel model = new biodataModel("1","mizan","100");
       // InitData.displayData(model);


        mTableLayout = (TableLayout)findViewById(R.id.tableBiodata);
        mButton = (Button)findViewById(R.id.buttonTamahBiodata);
        mButton.setOnClickListener(this);
        //Color red = Color.decode("#FF0000")
        int red = Color.parseColor("#FF0000");


        initialSetup();
        showData();//showData();


    }
    public void initialSetup(){
        brisTable = new TableRow(this);
        brisTable.setPadding(5,0,5,0);
        brisTable.setBackground(Drawable.createFromPath("@android.color.RED"));

        TextView viewHolderID = new TextView(this);
        TextView viewHolderName = new TextView(this);
        TextView viewHolderAge = new TextView(this);
        TextView viewHolderAction = new TextView(this);

        viewHolderID.setText("ID");
        viewHolderName.setText("Name");
        viewHolderAge.setText("Age");
        viewHolderAction.setText("Action");

        viewHolderID.setPadding(5,1,5,1);
        viewHolderName.setPadding(5,1,5,1);
        viewHolderAge.setPadding(5,1,5,1);
        viewHolderAction.setPadding(5,1,5,1);

        brisTable.addView(viewHolderID);
        brisTable.addView(viewHolderName);
        brisTable.addView(viewHolderAge);
        brisTable.addView(viewHolderAction);

        mTableLayout.addView(brisTable,new TableLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
    }
private void newShowdata(){
    mConstraint = new Constraint();
    try {
        mJSONArray = new JSONArray(mConstraint.ViewData());
        for(int i = 0;i<mJSONArray.length();i++){
            JSONObject jsonObject = mJSONArray.getJSONObject(i);
            //JSONObject jsonObject = mJSONArray.getJSONObject(i);
            String ID = jsonObject.optString("id");
            String Name =  jsonObject.optString("nama");
            String age = jsonObject.optString("age");

            System.out.println("ID | "+ID);
            System.out.println("name | "+Name);
            System.out.println("age | "+age);

            brisTable = new TableRow(this);

            TextView textView = new TextView(this);
            textView.setText(ID);
            textView.setPadding(5,1,5,1);
            brisTable.addView(textView);

            TextView textViewName = new TextView(this);
            textViewName.setText(Name);
            textViewName.setPadding(5,1,5,1);
            brisTable.addView(textViewName);

            TextView textViewAge = new TextView(this);
            textViewAge.setText(age);
            textViewAge.setPadding(5,1,5,1);
            brisTable.addView(textViewAge);

            EditButton.add(i,new Button(this));
            EditButton.get(i).setId(Integer.parseInt(ID));
            EditButton.get(i).setTag("Edit");
            EditButton.get(i).setText("Edit");
            EditButton.get(i).setOnClickListener(this);
            brisTable.addView(EditButton.get(i));

            DeleteButton.add(i,new Button(this));
            DeleteButton.get(i).setId(Integer.parseInt(ID));
            DeleteButton.get(i).setTag("Delete");
            DeleteButton.get(i).setText("Delete");
            DeleteButton.get(i).setOnClickListener(this);
            brisTable.addView(DeleteButton.get(i));
            mTableLayout.addView(brisTable,new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));

        }
    } catch (JSONException e) {
        e.printStackTrace();
    }

}
    private void showData(){
         String type = "view";
        for(int i = 0;i<InitData.DataList.size();i++){
            mBiodataModel = InitData.DataList.get(i);
            //JSONObject jsonObject = mJSONArray.getJSONObject(i);
            String ID = mBiodataModel.getID();
            String Name =  mBiodataModel.getName();
            String age = mBiodataModel.getAge();

            System.out.println("ID | "+ID);
            System.out.println("name | "+Name);
            System.out.println("age | "+age);

            brisTable = new TableRow(this);

            TextView textView = new TextView(this);
            textView.setText(ID);
            textView.setPadding(5,1,5,1);
            brisTable.addView(textView);

            TextView textViewName = new TextView(this);
            textViewName.setText(Name);
            textViewName.setPadding(5,1,5,1);
            brisTable.addView(textViewName);

            TextView textViewAge = new TextView(this);
            textViewAge.setText(age);
            textViewAge.setPadding(5,1,5,1);
            brisTable.addView(textViewAge);

            backgroundWorker worker = new backgroundWorker(this);
            worker.execute(type,ID,Name,age);

            EditButton.add(i,new Button(this));
            EditButton.get(i).setId(Integer.parseInt(ID));
            EditButton.get(i).setTag("Edit");
            EditButton.get(i).setText("Edit");
            EditButton.get(i).setOnClickListener(this);
            brisTable.addView(EditButton.get(i));

            DeleteButton.add(i,new Button(this));
            DeleteButton.get(i).setId(Integer.parseInt(ID));
            DeleteButton.get(i).setTag("Delete");
            DeleteButton.get(i).setText("Delete");
            DeleteButton.get(i).setOnClickListener(this);
            brisTable.addView(DeleteButton.get(i));
            mTableLayout.addView(brisTable,new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));

     }
}
    public void onClick(View view){
        if(view.getId() == R.id.buttonTamahBiodata){
            //TODO - insert Biodatra

            insertBiodata();
        }
        for(int i = 0;i<DeleteButton.size();i++){

             if(view.getId() == DeleteButton.get(i).getId() && view.getTag().toString().trim().equals("Delete")){
                int id = DeleteButton.get(i).getId();
               //biodataModel model = InitData.DataList.get(view.getId());
               InitData.DeleteBioData(id);
                finish();
                startActivity(getIntent());
            }
            else if(view.getId() == EditButton.get(i).getId() && view.getTag().toString().equals("Edit")){
                int id = EditButton.get(i).getId();
                 updateData(id);

            }
        }

    }

    private void insertBiodata() {

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        final EditText ID = new EditText(this);
        ID.setHint("ID");
        ID.setInputType(InputType.TYPE_CLASS_NUMBER);
        linearLayout.addView(ID);

        final EditText name = new EditText(this);
        name.setHint("Name");
        name.setInputType(InputType.TYPE_CLASS_TEXT);
        linearLayout.addView(name);

        final EditText age = new EditText(this);
        age.setHint("Age");
        age.setInputType(InputType.TYPE_CLASS_NUMBER);
        linearLayout.addView(age);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.ic_launcher_foreground);
        builder.setTitle("Insert Biodata");
        builder.setView(linearLayout);
        builder.setPositiveButton("Insert", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String type = "view";
                String id = ID.getText().toString();
                String nm = name.getText().toString();
                String ag = age.getText().toString();

                biodataModel model = new biodataModel(id,nm,ag);
                InitData.DataList.add(model);

                final String url = Constraint.ROOT_URL + "?operasi=insert&nama=" + model.getName() + "&age=" + model.getAge();
                insertDataInto_PHP(url,model);

                Log.d("Insert", "Insert Data Call");
                finish();
                startActivity(getIntent());
            }
        });
        builder.show();
    }

    private void insertDataInto_PHP(String url, final biodataModel model){
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Please Wait...............");
        mProgressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        mProgressDialog.dismiss();
                        Log.d("Insert","Insert Call");
                      Toast.makeText(getApplicationContext(),"Insert Successful",Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mProgressDialog.hide();
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String>parms = new HashMap<>();
                parms.put("id",model.getID());
                parms.put("nama",model.getName());
                parms.put("age",model.getAge());
                return parms;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void updateData(int id){

        LinearLayout linearLayout = new LinearLayout(this);
        final EditText ID, nameText, ageText;
        String name = null, age = null;
        biodataModel mm = InitData.DataList.get(id);

        name = mm.getName().toString();
        age = mm.getAge().toString();

        linearLayout.setOrientation(LinearLayout.VERTICAL);

        final TextView idTextView = new TextView(this);
        idTextView.setText(String.valueOf(id));
        idTextView.setTextColor(Color.TRANSPARENT);
        linearLayout.addView(idTextView);

        nameText = new EditText(this);
        nameText.setText(name);
        linearLayout.addView(nameText);

        ageText = new EditText(this);
        ageText.setText(age);
        linearLayout.addView(ageText);

        AlertDialog.Builder builderEditButton = new AlertDialog.Builder(this);
        builderEditButton.setIcon(R.drawable.ic_launcher_background);
        builderEditButton.setTitle("Edit Biodata");
        builderEditButton.setView(linearLayout);
        builderEditButton.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                InitData.UpdataBiodata(idTextView.getText().toString(),nameText.getText().toString(),ageText.getText().toString());
                finish();
                startActivity(getIntent());
            }
        });
        builderEditButton.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        builderEditButton.show();
    }

}

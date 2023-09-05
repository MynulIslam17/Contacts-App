package com.noyon.listviewpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    ImageView plusImg;

    public String name;
    public String number;
    ListView lView;
    ArrayList< HashMap<String,String> > list= new ArrayList<>();

    HashMap <String,String> map;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);








        //finding id of mainActivity
        plusImg=findViewById(R.id.plusImg);
        lView=findViewById(R.id.lView);

        //set click event

        plusImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //creating dialog box part
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.cstm_dialog);


                //find id of dialogBox .jehetu ei xml dialog er shate setconteainview kora hoice tai eke dialog.find korte hobe karon oi id gula main xml er naa

                EditText edName=dialog.findViewById(R.id.edName);
                EditText edNumber= dialog.findViewById(R.id.edNumber);
                Button btn= dialog.findViewById(R.id.btn);
                dialog.show();

               //-----end---

                //add clicl event to dialog box okbtn
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        //get informaton from user
                        name=edName.getText().toString();
                        number=edNumber.getText().toString();

                        //if name or number string is empty then execute this one
                         if(name.isEmpty() || number.isEmpty()){
                            Toast.makeText(MainActivity.this,"Can't be empty",Toast.LENGTH_LONG).show();

                         }

                         //otherwise performe this one
                        else {
                             //put the information in hasmap
                             map = new HashMap<>();
                             map.put("Name", name);
                             map.put("Number", number);


                             //then add it on arraylist
                             list.add(map);


                             //creat object of base adapter
                             MyAdapter adapter = new MyAdapter();
                             lView.setAdapter(adapter);


                             dialog.dismiss();

                         }

                    }
                });






            }
        });


    }

    // ============== creating adapter class

    public class MyAdapter extends BaseAdapter {

        LayoutInflater layInflater;


        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            layInflater= (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           View myView= layInflater.inflate(R.layout.item,viewGroup,false);

           //geting value from arrylist

           /* Note : jehet ArrayyList er index er modde hashmap store ache
                  tai value Arraylist theke get kore new hashmap er modde rakhlam
            */
            HashMap <String,String> newMap= list.get(i);

            //then abar hashmap theke value get korlam
            String userName= newMap.get("Name");
            String userNumber= newMap.get("Number");

            //displaing output on screen part

            // first finding id from item

            TextView tvName= myView.findViewById(R.id.tvName);
            TextView tvNum= myView.findViewById(R.id.tvNum);

            tvName.setText(userName);
            tvNum.setText(userNumber);


            return myView;
        }
    }



    //=================end class




}
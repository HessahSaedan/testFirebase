package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class updateCar extends AppCompatActivity
{
    EditText etModel;
    EditText etPlate;
    EditText etPrice;
    EditText etYear;

    Button updateB;
    Button clearB;
    Button searchB;

    String model;
    String plate;
    int price;
    int year;

    boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_car);

        etModel = findViewById(R.id.updateCet1);
        etPlate = findViewById(R.id.updateCet2);
        etPrice = findViewById(R.id.updateCet3);
        etYear  = findViewById(R.id.updateCet4);

        updateB = findViewById(R.id.updateBUpdateAct);
        clearB  = findViewById(R.id.clearBUpdateAct);
        searchB = findViewById(R.id.searchB);
        flag = false;

        searchB.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                flag = true;

                FirebaseDatabase database = FirebaseDatabase.getInstance("https://se328project-2cda3-default-rtdb.firebaseio.com");
                DatabaseReference ref = database.getReference("Cars");

                EditText searchET = findViewById(R.id.ed1Search);
                String target = searchET.getText().toString().trim();

                Query query = ref.orderByChild("plate").equalTo(target);

                query.addValueEventListener(new ValueEventListener()
                {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot ds)
                    {
                        if (ds.getValue() == null)
                        {
                            Toast.makeText(updateCar.this,"Not Found",Toast.LENGTH_LONG).show();
                            return;
                        }

                        String v = ds.getValue().toString();
                        int index = v.indexOf("={");
                        String s = v.substring(index+2);
                        index = s.indexOf("}}");
                        s = s.substring(0,index);

                        String all[] = s.split(",");

                        price = Integer.parseInt(all[0].substring(all[0].indexOf('=')+1));
                        plate = all[1].substring(all[1].indexOf('=')+1);
                        year = Integer.parseInt(all[2].substring(all[2].indexOf('=')+1));
                        model = all[3].substring(all[3].indexOf('=')+1);

                        etModel.setText(model);
                        etPlate.setText(plate);
                        etPrice.setText(String.valueOf(price));
                        etYear.setText(String.valueOf(year));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) { }
                });
            }
        });

        updateB.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (flag == false)
                {
                    Toast.makeText(updateCar.this,"You must first press search",Toast.LENGTH_LONG).show();
                    return;
                }

                FirebaseDatabase database = FirebaseDatabase.getInstance("https://se328project-2cda3-default-rtdb.firebaseio.com");
                DatabaseReference ref = database.getReference("Cars");

                EditText searchET = findViewById(R.id.ed1Search);
                String target = searchET.getText().toString().trim();

                Query query = ref.orderByChild("plate").equalTo(target);

                query.addValueEventListener(new ValueEventListener()
                {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot ds)
                    {
                        model = etModel.getText().toString().trim();
                        plate = etPlate.getText().toString().trim();
                        price = Integer.parseInt(etPrice.getText().toString().trim());
                        year  = Integer.parseInt(etYear.getText().toString().trim());

                        myUpdate(ds);
                    }

                   @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) { }
                });
            }
        });

        clearB.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                clearEditTexts();
            }
        });
    }

    public void myUpdate(DataSnapshot ds)
    {
        for(DataSnapshot ds1 : ds.getChildren())
        {
            Map<String, Object> map = new HashMap<>();
            map.put("plate", plate);
            map.put("model", model);
            map.put("price", price);
            map.put("year", year);
            ds1.getRef().updateChildren(map);
        }
    }

    public void clearEditTexts()
    {
        etModel.setText("");
        etPlate.setText("");
        etPrice.setText("");
        etYear.setText("");
    }
}
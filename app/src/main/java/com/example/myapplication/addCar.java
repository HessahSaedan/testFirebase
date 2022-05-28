package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class addCar extends AppCompatActivity
{
    EditText etModel;
    EditText etPlate;
    EditText etPrice;
    EditText etYear;

    Button addB;
    Button clearB;

    String model;
    String plate;
    int price;
    int year;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        getMyViews();
        firebaseDatabase = FirebaseDatabase.getInstance("https://se328project-2cda3-default-rtdb.firebaseio.com");
        databaseReference = firebaseDatabase.getReference("Cars");

        addB.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Car car = getCarInfo();
                databaseReference.push().setValue(car);
                Toast.makeText(addCar.this, "Car Added..", Toast.LENGTH_SHORT).show();
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

    public void getMyViews()
    {
        etModel = findViewById(R.id.addCet1);
        etPlate = findViewById(R.id.addCet2);
        etPrice = findViewById(R.id.addCet3);
        etYear  = findViewById(R.id.addCet4);
        addB   = findViewById(R.id.addBAddAct);
        clearB = findViewById(R.id.clearBAddAct);
    }

    public Car getCarInfo()
    {
        model = etModel.getText().toString().trim();
        plate = etPlate.getText().toString().trim();
        price = Integer.parseInt(etPrice.getText().toString().trim());
        year  = Integer.parseInt(etYear.getText().toString().trim());

        return new Car(model,plate,price,year);
    }

    public void clearEditTexts()
    {
        etModel.setText("");
        etPlate.setText("");
        etPrice.setText("");
        etYear.setText("");
    }
}
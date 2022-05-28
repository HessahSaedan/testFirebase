package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private Button addCarB;
    private Button updateB;
    private Button deleteB;
    private Button exitB;

    private DatabaseReference mDatabase;
    private FirebaseDatabase myDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addCarB = findViewById(R.id.addB);
        updateB = findViewById(R.id.updateB);
        deleteB = findViewById(R.id.deletB);
        exitB = findViewById(R.id.exitB);

        addCarB.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this,addCar.class);
                startActivity(intent);
            }
        });

        updateB.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this,updateCar.class);
                startActivity(intent);
            }
        });

        deleteB.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this,deleteCar.class);
                startActivity(intent);
            }
        });

        exitB.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
                System.exit(0);
            }
        });
    }
}
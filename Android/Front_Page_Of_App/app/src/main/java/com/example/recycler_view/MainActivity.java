package com.example.recycler_view;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import android.widget.Button;
import android.content.Intent;

import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import android.graphics.Color;

public class MainActivity extends AppCompatActivity {
    RecyclerView lsvDish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button messageButton = findViewById(R.id.messageButton);

        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle button click here
                Intent intent = new Intent(MainActivity.this, Message_Application.class);
                startActivity(intent);
            }
        });

        lsvDish = findViewById(R.id.lsvDish);

        ArrayList<Dish> arr =new ArrayList<>();


        arr.add(new Dish(
                R.drawable.img,
                565-865-7845,
                "SwiftRescue",
                "swiftrescue@email.com",
                "Swiftville, USA",
                "Emergency Services",
                "2022-01-15"
        ));
        arr.add(new Dish(
                R.drawable.res1,
                555-987-6543,
                "HelpForce",
                "helpforce@email.com",
                "Helpville, USA",
                "Disaster Relief",
                "2021-11-30"
        ));
        arr.add(new Dish(
                R.drawable.res2,
                555-789-1023,
                "AidGuard",
                "aidguard@email.com",
                "Aidville, USA",
                "Humanitarian Aid",
                "2022-03-10"
        ));
        arr.add(new Dish(
                R.drawable.res3,
                555-234-5678,
                "SaveLink",
                "savelink@email.com",
                "Saveland, USA",
                "Community Support",
                "2022-02-20"
        ));
        arr.add(new Dish(
                R.drawable.res4,
                555-876-5432,
                "Lifeline",
                "lifeline@email.com",
                "Lifeville, USA",
                "Healthcare Services",
                "2021-12-05"
        ));
        arr.add(new Dish(
                R.drawable.res5,
                555-345-6789,
                "RescueOne",
                "rescueone@email.com",
                "Rescueville, USA",
                "Search and Rescue",
                "2022-01-25"
        ));
        arr.add(new Dish(
                R.drawable.res6,
                555-654-2310,
                "HeroReach",
                "heroreach@email.com",
                "Heroville, USA",
                "Disaster Response",
                "2022-02-15"
        ));
        arr.add(new Dish(
                R.drawable.res7,
                555-432-1098,
                "SafetyNet",
                "safetynet@email.com",
                "Safetyville, USA",
                "Child Safety",
                "2022-03-05"
        ));
        arr.add(new Dish(
                R.drawable.res8,
                555-256-9810,
                "RescueWave",
                "rescuewave@email.com",
                "Waveville, USA",
                "Water Rescue",
                "2021-12-20"
        ));
        arr.add(new Dish(
                R.drawable.res9,
                555-239-6578,
                "SaviorCall",
                "saviorcall@email.com",
                "Saviorville, USA",
                "Emergency Response",
                "2022-03-15"
        ));



        //DishAdapter adapter = new DishAdapter(this,0,arr);

        DishRecyclerViewAdapter adapter = new DishRecyclerViewAdapter(this,arr);
        lsvDish.setLayoutManager(new LinearLayoutManager(this));
        lsvDish.setAdapter(adapter);


    }
}
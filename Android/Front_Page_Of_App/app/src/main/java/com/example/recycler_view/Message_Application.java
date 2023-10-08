package com.example.recycler_view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.List;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
public class Message_Application extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Application");
        setContentView(R.layout.activity_message_application);

        // Initialize UI components

        Spinner spinnerPeopleNeeded = findViewById(R.id.spinnerPeopleNeeded);
        CheckBox checkBoxShelter = findViewById(R.id.checkBoxShelter);
        CheckBox checkBoxMedicine = findViewById(R.id.checkBoxMedicine);
        CheckBox checkBoxFood = findViewById(R.id.checkBoxFood);
        CheckBox checkBoxMedical = findViewById(R.id.checkBoxMedical);
        CheckBox checkBoxRescue = findViewById(R.id.checkBoxRescue);
        CheckBox checkBoxPeople = findViewById(R.id.checkBoxPeople);
        EditText editTextComments = findViewById(R.id.editTextComments);
        Button buttonSendAlert = findViewById(R.id.buttonSendAlert);

        // Create a list of numbers from 1 to 25
        List<String> numbersList = new ArrayList<>();
        for (int i = 5; i <= 25; i++) {
            numbersList.add(String.valueOf(i));
        }

        // Create an ArrayAdapter for the Spinner
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, numbersList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the Spinner's adapter
        spinnerPeopleNeeded.setAdapter(spinnerAdapter);


        // Set up event handling for the button click
        buttonSendAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Check if the app has SMS permissions
                if (ContextCompat.checkSelfPermission(Message_Application.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                    // Permission is not granted, request it
                    ActivityCompat.requestPermissions(Message_Application.this, new String[]{Manifest.permission.SEND_SMS}, MY_PERMISSIONS_REQUEST_SEND_SMS);
                } else {
                    // Permission is already granted, you can send the SMS
                    sendSMS();
                }
            }
        });
    }

    // Helper methods to get selected options
    private String getSelectedPeopleAffected() {
        RadioButton radioButton1_25 = findViewById(R.id.radioButton1_25);
        RadioButton radioButton26_50 = findViewById(R.id.radioButton26_50);
        RadioButton radioButton51_100 = findViewById(R.id.radioButton51_100);
        RadioButton radioButton100Plus = findViewById(R.id.radioButton100Plus);

        if (radioButton1_25.isChecked()) {
            return "1-25";
        } else if (radioButton26_50.isChecked()) {
            return "26-50";
        } else if (radioButton51_100.isChecked()) {
            return "51-100";
        } else if (radioButton100Plus.isChecked()) {
            return "100+";
        } else {
            return "Not Valid";
        }
    }

    private String getSelectedPeopleNeeded() {
        Spinner spinnerPeopleNeeded = findViewById(R.id.spinnerPeopleNeeded);
        return spinnerPeopleNeeded.getSelectedItem().toString();
    }

    private String getSelectedHelpTypes() {
        CheckBox checkBoxShelter = findViewById(R.id.checkBoxShelter);
        CheckBox checkBoxMedicine = findViewById(R.id.checkBoxMedicine);
        CheckBox checkBoxFood = findViewById(R.id.checkBoxFood);
        CheckBox checkBoxMedical = findViewById(R.id.checkBoxMedical);
        CheckBox checkBoxRescue = findViewById(R.id.checkBoxRescue);
        CheckBox checkBoxPeople = findViewById(R.id.checkBoxPeople);

        StringBuilder selectedHelpTypes = new StringBuilder();

        if (checkBoxShelter.isChecked()) {
            selectedHelpTypes.append("Shelter, ");
        }
        if (checkBoxMedicine.isChecked()) {
            selectedHelpTypes.append("Medicine, ");
        }
        if (checkBoxFood.isChecked()) {
            selectedHelpTypes.append("Food, ");
        }
        if (checkBoxMedical.isChecked()) {
            selectedHelpTypes.append("Medical Equipment, ");
        }
        if (checkBoxRescue.isChecked()) {
            selectedHelpTypes.append("Rescue Equipment, ");
        }
        if (checkBoxPeople.isChecked()) {
            selectedHelpTypes.append("People, ");
        }

        // Remove the trailing comma and space if any help types were selected
        if (selectedHelpTypes.length() > 0) {
            selectedHelpTypes.deleteCharAt(selectedHelpTypes.length() - 2);
        }

        return selectedHelpTypes.toString();
    }

    private String getUserComments() {
        EditText editTextComments = findViewById(R.id.editTextComments);
        return editTextComments.getText().toString();
    }
    // Send SMS method
    private void sendSMS() {
        String phoneNumber = "8660035894";  // Replace with the recipient's phone number
        String message = "People Affected: " + getSelectedPeopleAffected() + "\n"
                + "People Needed: " + getSelectedPeopleNeeded() + "\n"
                + "Help Types: " + getSelectedHelpTypes() + "\n"
                + "Comments: " + getUserComments();

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
            Toast.makeText(this, "SMS sent successfully", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Failed to send SMS", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    // Handle permission request result
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_PERMISSIONS_REQUEST_SEND_SMS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, you can send the SMS
                sendSMS();
            } else {
                // Permission denied, inform the user
                Toast.makeText(this, "Permission to send SMS denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
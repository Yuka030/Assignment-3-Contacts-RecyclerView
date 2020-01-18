package com.derrick.park.assignment3_contacts.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.derrick.park.assignment3_contacts.R;
import com.derrick.park.assignment3_contacts.models.Contact;

public class AddContactActivity extends AppCompatActivity {

    private TextView nameText;
    private TextView phoneText;
    private Contact contact;

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public void submitContact(View view){
        String enteredFullName = nameText.getText().toString();
        String enteredPhoneText = phoneText.getText().toString();

        if(enteredFullName.isEmpty() || enteredPhoneText.isEmpty()){
            Toast.makeText(this, "Please enter a valid name or phone number.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!enteredFullName.contains(" ")){
            Toast.makeText(this, "Please add a space between first name and last name.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!isNumeric(enteredPhoneText)||enteredPhoneText.length() != 10){
            Toast.makeText(this, "Please enter a valid phone number (10 digits).", Toast.LENGTH_SHORT).show();
            return;
        }

        String enteredFirstName = enteredFullName.substring(0, enteredFullName.indexOf(" "));
        String enteredLastName = nameText.getText().toString().substring(enteredFullName.indexOf(" ") + 1);

        Contact.Name name = new Contact.Name(enteredFirstName, enteredLastName);
        contact = new Contact(name, enteredPhoneText);
        System.out.println("entered new contact is: " + contact);

        Intent MainIntent = new Intent(AddContactActivity.this, MainActivity.class);
        MainIntent.putExtra("contact",contact);
        MainIntent.putExtra("phoneNumber", enteredPhoneText);
        MainIntent.putExtra("firstName", enteredFirstName);
        MainIntent.putExtra("lastName", enteredLastName);
        AddContactActivity.this.startActivity(MainIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        nameText = findViewById(R.id.nameText);
        phoneText = findViewById(R.id.phoneText);

    }
}

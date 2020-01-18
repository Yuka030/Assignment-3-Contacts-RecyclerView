package com.derrick.park.assignment3_contacts.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.derrick.park.assignment3_contacts.R;
import com.derrick.park.assignment3_contacts.adapters.ContactsAdapter;
import com.derrick.park.assignment3_contacts.models.Contact;
import com.derrick.park.assignment3_contacts.models.ContactList;
import com.derrick.park.assignment3_contacts.network.ContactClient;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Contact> mContactList;
    public static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    protected void setRecyclerview() {
        recyclerView = (RecyclerView) findViewById(R.id.contactListView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        ContactsAdapter adapter = new ContactsAdapter(this, mContactList);
        recyclerView.setAdapter(adapter);
    }


    public void addContact(View view){
        Intent addContactIntent = new Intent(MainActivity.this, AddContactActivity.class);
        MainActivity.this.startActivity(addContactIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Call<ContactList> call = ContactClient.getContacts(10);

        call.enqueue(new Callback<ContactList>() {
            @Override
            public void onResponse(Call<ContactList> call, Response<ContactList> response) {
                if (response.isSuccessful()) {
                     mContactList = response.body().getContactList();
                    Intent intent = getIntent();
                    if(intent.getParcelableExtra("contact") != null){
                        String firstName = intent.getStringExtra("firstName");
                        String lastName = intent.getStringExtra("lastName");
                        String phoneNumber = intent.getStringExtra("phoneNumber");

                        Contact.Name name = new Contact.Name(firstName, lastName);
                        Contact contact = new Contact(name, phoneNumber);
                        mContactList.add(contact);
                    }

                    Collections.sort(mContactList);
                    setRecyclerview();
                }
            }

            @Override
            public void onFailure(Call<ContactList> call, Throwable t) {
                // Error Handling
                Log.d(TAG, "onFailure: " + t.getMessage());
            }

        });
    }
}

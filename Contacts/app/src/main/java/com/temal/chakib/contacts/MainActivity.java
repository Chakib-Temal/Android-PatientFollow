package com.temal.chakib.contacts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static private final int ADD_CONTACT = 1;
    static private final int EDIT_CONTACT = 1;
    private Contacts contacts;
    private ListView list;
    private ArrayAdapter<Contact> adapter;


    private BasicSpinnerAdapter adapter2;
    private List<String> itemSpinner;


    public static boolean active = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        contacts = new Contacts();
        contacts.pupulate();

        itemSpinner = new ArrayList<String>();
        itemSpinner.add("2");
        itemSpinner.add("3");


        list = (ListView) findViewById(R.id.listContact);
        //list.setDrawingCacheBackgroundColor(getResources().getColor(R.color.blank));
        //adapter = new ContactAdapter(this, 0,contacts.getContacts());
        //list.setAdapter(adapter);

        adapter2 = new BasicSpinnerAdapter(contacts.getContacts(), itemSpinner, this);
        list.setAdapter(adapter2);


        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(MainActivity.this, EditContactActivity.class);
                startActivityForResult(intent, ADD_CONTACT );


            }
        });

        /*
        ArrayList<Object> ze = new ArrayList<Object>();
        Contact c = new Contact("chakib", " ", "", " ", true);
        ze.add(c);
        TinyDB tinydb = new TinyDB(getApplicationContext());
        tinydb.putListObject("mywwList", ze );


        :: RECEIVE
        TinyDB tinydb = new TinyDB(getApplicationContext());
                ArrayList<Object> ze = new ArrayList<Object>();
                ze = tinydb.getListObject("mywwList", Contact.class);


                for (Object obj: ze){
                    Contact c = (Contact) obj;
                    System.out.println(c.getFirstName());
                }

        */

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                TextView text = (TextView) view.findViewById(R.id.firstName);
                Spinner frequency = (Spinner) view.findViewById(R.id.spinner1);

                Contact obj = contacts.getContacts().get(i);


                System.out.println("voici la reponse  " + i + "  " + frequency.getSelectedItem().toString());





                if (view.getDrawingCacheBackgroundColor() == getResources().getColor(R.color.colorAccent)){
                    view.setDrawingCacheBackgroundColor(getResources().getColor(R.color.blank));
                    view.setBackgroundColor(getResources().getColor(R.color.blank));


                }else {
                    view.setDrawingCacheBackgroundColor(getResources().getColor(R.color.colorAccent));
                    view.setBackgroundColor(getResources().getColor(R.color.colorAccent));

                }
            }


        });



        list.setOnItemLongClickListener (new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                contacts.getContacts().remove(i);

                ((BaseAdapter) list.getAdapter()).notifyDataSetChanged();
                return false;
            }
        });






    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class  ContactAdapter extends ArrayAdapter<Contact>{

        public ContactAdapter(@NonNull Context context, int resource, @NonNull List<Contact> objects) {
            super(context,android.R.layout.simple_list_item_1 , objects);

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View root = inflater.inflate(R.layout.contact_item, null);


            TextView firstname_ui = (TextView) root.findViewById(R.id.firstName);
            TextView lastname_ui = (TextView) root.findViewById(R.id.lastName);
            TextView phone_ui = (TextView) root.findViewById(R.id.phoneNumber);
            TextView age_ui = (TextView) root.findViewById(R.id.age);

            Spinner dropdown = (Spinner) root.findViewById(R.id.spinner1);



            Contact contact = getItem(position);
            firstname_ui.setText(contact.getFirstName());
            lastname_ui.setText(contact.getLastName());
            phone_ui.setText(contact.getPhoneNumber());

//            age_ui.setText(contact.getAge());
            return root;
        }


    }

    //retour
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == ADD_CONTACT){
            String firstname = data.getStringExtra("firstName");
            String lastName = data.getStringExtra("lastName");
            String phoneNumber = data.getStringExtra("phoneNumber");
            String age = data.getStringExtra("age");
            boolean gender = data.getBooleanExtra("gender", false);
            Contact c = new Contact(firstname, lastName, phoneNumber, age, gender);
            contacts.addContact(c);
            System.out.println(contacts.toString());
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        active = true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("Destroyed");
        active = false;
    }



}

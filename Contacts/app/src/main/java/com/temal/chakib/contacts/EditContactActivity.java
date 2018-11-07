package com.temal.chakib.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class EditContactActivity extends AppCompatActivity {
    private EditText firstName;
    private EditText lastName;
    private EditText phoneNumber;
    private EditText age;
    private RadioGroup gender;
    private RadioButton female;
    private RadioButton mal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        System.out.println("2eme Oncreate");
        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        age = (EditText) findViewById(R.id.age);
        gender = (RadioGroup) findViewById(R.id.gender);
        female = (RadioButton) findViewById(R.id.female);
        mal = (RadioButton) findViewById(R.id.mal);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_contact, menu);
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
        }else if (id == R.id.actionAdd){
            doAdd();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void doAdd(){
        String firstName = this.firstName.getText().toString();
        String lastName = this.lastName.getText().toString();
        String phone = this.phoneNumber.getText().toString();
        String age = this.age.getText().toString();

        boolean female = true;
        if (gender.getCheckedRadioButtonId() == R.id.female){
            female = true;
            System.out.println("c une femme");
        }else if (gender.getCheckedRadioButtonId() == R.id.mal){
            female = false;
            System.out.println("c un mec");
        }

        Intent intent = getIntent();
        intent.putExtra("firstName", firstName);
        intent.putExtra("lastName", lastName);
        intent.putExtra("phoneNumber", phone);
        intent.putExtra("age", age);
        intent.putExtra("gender", female);


        if (MainActivity.active){

            setResult(RESULT_OK, intent );
            //revient vers l'arriere
            finish();
        }else {
            Intent intents = new Intent(EditContactActivity.this, MainActivity.class);
            startActivity(intents);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        if (MainActivity.active){
            Intent intent = getIntent();
            setResult(RESULT_OK, intent );
            //revient vers l'arriere
            finish();
        }else {
            Intent intent = new Intent(EditContactActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }


    }

    @Override
    protected void onStop() {
        super.onStop();
        Intent intent = new Intent(EditContactActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

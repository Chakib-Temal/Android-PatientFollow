package com.chakibtemal.fr.patientfollow;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.File;

import Modele.DataStorage.DataBase;
import Modele.modelesClass.Drug;
import Modele.modelesClass.Prescription;


public class MainActivity extends AppCompatActivity {

    private final static String DATA_BASE = "db";
    private Intent intent;
    private DataBase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        if (intent.hasExtra(DATA_BASE)) {
            this.db = (DataBase) getIntent().getExtras().getParcelable(DATA_BASE);
        } else {
            deleteAllPictures();
            /**
             * in db instance of DataBase we store all data of applications
             */
            this.db = new DataBase(this);
        }


        for (Prescription actualPerscription : db.getPrescriptionList()){
            for (Drug actualDrugs : actualPerscription.getDrugList()){
                System.out.println("------------------------------------------------");
                System.out.println(actualDrugs.getName());
                System.out.println("------------------------------------------------");
            }
        }


        //Calendar rightNow = Calendar.getInstance();
        //int currentHourIn24Format = rightNow.get(Calendar.HOUR_OF_DAY);


    }

    public void goListDrugs(View view) {
        this.intent = new Intent(MainActivity.this, ListDrugsActivity.class);
        intent.putExtra("db", this.db);
        startActivity(intent);
    }


    public void goListPrescription(View view) {

    }

    public void goListDrugDay(View view) {
        this.intent = new Intent(MainActivity.this, ListDrugsActivity.class);
        startActivity(intent);
    }

    public void goAboutUs(View view) {
        View parentLayout = findViewById(android.R.id.content);
        //Snackbar.make(parentLayout, "Temal_Chakib-UHA-2018", Snackbar.LENGTH_LONG).show();
        Snackbar snackbar = Snackbar.make(parentLayout, "Contact : chakibtemal@gmail.com", Snackbar.LENGTH_LONG);
        View snackbarView = snackbar.setActionTextColor(Color.WHITE).getView();
        snackbarView.setBackgroundColor(Color.BLACK);
        snackbar.show();
    }

    public void deleteAllPictures(){
        String dir = getFilesDir().getAbsolutePath();
        File myDir = new File(this.getFilesDir().getPath()+ "/Pictures");
        if (myDir.isDirectory())
        {
            String[] children = myDir.list();
            for (int i = 0; i < children.length; i++)
            {
                new File(myDir, children[i]).delete();
            }
        }
    }
}
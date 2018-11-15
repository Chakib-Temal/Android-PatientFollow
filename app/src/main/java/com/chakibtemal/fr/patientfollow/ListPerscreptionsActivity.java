package com.chakibtemal.fr.patientfollow;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import Modele.DataStorage.DataBase;
import Modele.adapter.AdapterPrescription;

public class ListPerscreptionsActivity extends AppCompatActivity {

    private DataBase db;
    private ListView listViewPrescription;
    private AdapterPrescription adapter;

    private Intent intent;
    private Context context;

    private final static int REQUEST_CODE_ADD = 0;
    private final static int REQUEST_CODE_UPDATE = 1;
    private final static String DATA_BASE = "db";
    private final static String REQUEST_CODE = "requestCode";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_perscreptions);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        this.context = this;
        this.db = (DataBase) getIntent().getExtras().getParcelable(DATA_BASE);

        this.adapter = new AdapterPrescription(db.getPrescriptionList(), this);
        this.listViewPrescription = (ListView) findViewById(R.id.listPrescription);
        this.listViewPrescription.setAdapter(adapter);










        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }




    @Override
    public void onBackPressed() {
        this.intent = new Intent(context, MainActivity.class);
        intent.putExtra(DATA_BASE, this.db);
        startActivity(intent);
        finish();
    }

}

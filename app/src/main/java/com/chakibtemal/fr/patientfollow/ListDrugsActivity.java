package com.chakibtemal.fr.patientfollow;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import Modele.DataStorage.DataBase;
import Modele.adapter.BasicAdapter;
import Modele.modelesClass.Drug;

public class ListDrugsActivity extends AppCompatActivity {
    private DataBase db;
    private ListView listViewDrug;
    private List<Drug> listDrug;
    private BasicAdapter adapter;

    private Intent intent;
    private Context context;

    private final static int REQUEST_CODE_ADD = 0;
    private final static int REQUEST_CODE_UPDATE = 1;
    private final static String DATA_BASE = "db";
    private final static String REQUEST_CODE = "requestCode";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_drugs);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        this.context = this;
        this.db = (DataBase) getIntent().getExtras().getParcelable(DATA_BASE);
        this.listDrug = db.getAllDrugs();


        this.adapter = new BasicAdapter(this.listDrug, this);
        this.listViewDrug = (ListView) findViewById(R.id.listViewDrugs);
        this.listViewDrug.setAdapter(adapter);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(context,DrugActivity.class);
                intent.putExtra(DATA_BASE, db);
                intent.putExtra(REQUEST_CODE, REQUEST_CODE_ADD);
                startActivityForResult(intent, REQUEST_CODE_ADD);
            }
        });

        this.listViewDrug.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                intent = new Intent(context,DrugActivity.class);
                intent.putExtra(DATA_BASE, db);
                intent.putExtra(REQUEST_CODE, REQUEST_CODE_UPDATE);
                intent.putExtra("idActualDrug", i );
                startActivityForResult(intent, REQUEST_CODE_UPDATE);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_ADD) {
            if (resultCode == RESULT_OK) {

            }
        }else if (requestCode == REQUEST_CODE_UPDATE){
            if (resultCode == RESULT_OK){

            }
        }

    }


}

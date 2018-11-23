package com.chakibtemal.fr.patientfollow;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import Modele.DataStorage.DataBase;
import Modele.adapter.AdapterPrescription;
import Modele.modelesClass.Prescription;

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
                intent = new Intent(context,ListDrugPrescreptionActivity.class);
                intent.putParcelableArrayListExtra("Drugs", (ArrayList<? extends Parcelable>) db.getAllDrugs());
                intent.putExtra("Prescripton", new Prescription());
                intent.putExtra(REQUEST_CODE, REQUEST_CODE_ADD);
                startActivityForResult(intent, REQUEST_CODE_ADD);
            }
        });

        this.listViewPrescription.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                intent = new Intent(context,ListDrugPrescreptionActivity.class);
                intent.putExtra("Prescripton", db.getPrescriptionList().get(i));
                intent.putExtra(REQUEST_CODE, REQUEST_CODE_UPDATE);
                intent.putExtra("idActualPrescription", i );
                intent.putParcelableArrayListExtra("Drugs", (ArrayList<? extends Parcelable>) db.getAllDrugs());

                startActivityForResult(intent, REQUEST_CODE_UPDATE);
            }
        });


        this.listViewPrescription.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                db.deletePrescription(i);
                adapter.notifyDataSetChanged();
                return true;
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE_ADD && resultCode == RESULT_OK) {
            Prescription prescription = (Prescription) data.getParcelableExtra("Prescription");
            if (prescription.getDrugList().size() != 0){
                this.db.getPrescriptionList().add(prescription);
            }
        }else if (requestCode == REQUEST_CODE_UPDATE && resultCode == RESULT_OK){
            Prescription prescription = (Prescription) data.getParcelableExtra("Prescription");
            int id = data.getIntExtra("idActualPrescription", 22);
            db.updatePrescription(prescription, id);

            if (prescription.getDrugList().size() == 0){
                db.removePrescreption(id);
            }
        }
        adapter.notifyDataSetChanged();
    }



    @Override
    public void onBackPressed() {
        this.intent = new Intent(context, MainActivity.class);
        intent.putExtra(DATA_BASE, this.db);
        startActivity(intent);
        finish();
    }

}

package com.chakibtemal.fr.patientfollow;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import Modele.adapter.BasicAdapter;
import Modele.modelesClass.Prescription;

public class ListDrugPrescreptionActivity extends AppCompatActivity {

    private ListView listViewDrug;
    private BasicAdapter adapter;

    private Intent intent ;
    private Context context;
    private Prescription actualPrescription;

    private final static int REQUEST_CODE_ADD = 0;
    private final static int REQUEST_CODE_UPDATE = 1;
    private int idActualPrescription;
    private int REQUEST_CODE_VALUE = 22 ;
    private static String REQUEST_CODE_KEY = "requestCode" ;
    private final static String REQUEST_CODE = "requestCode";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_drug_prescreption);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        this.context = this;
        intent = getIntent();
        REQUEST_CODE_VALUE = (int) intent.getExtras().getInt(REQUEST_CODE_KEY, 22);


        if (REQUEST_CODE_VALUE == REQUEST_CODE_ADD){
            this.listViewDrug = (ListView) findViewById(R.id.listDrugsPrescription);

        } else if (REQUEST_CODE_VALUE == REQUEST_CODE_UPDATE){
            this.listViewDrug = (ListView) findViewById(R.id.listDrugsPrescription);
            this.actualPrescription = (Prescription) this.intent.getParcelableExtra("Prescripton");
            this.idActualPrescription = intent.getIntExtra("idActualPrescription", 22);
            this.adapter = new BasicAdapter(this.actualPrescription.getDrugList(), this);
            this.listViewDrug.setAdapter(adapter);
        }else {
            /**
             * Here we will add a new Drug to the Prescription
             */

        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        this.listViewDrug.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                intent = new Intent(context,DrugActivity.class);
                intent.putExtra("Drug", actualPrescription.getDrugList().get(i));
                intent.putExtra(REQUEST_CODE, REQUEST_CODE_UPDATE);
                intent.putExtra("idActualDrug", i );
                startActivityForResult(intent, REQUEST_CODE_UPDATE);
            }
        });

        this.listViewDrug.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                actualPrescription.deleteDrug(i);
                adapter.notifyDataSetChanged();
                return true;
            }
        });

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE_ADD && resultCode == RESULT_OK) {
            //this.db.getAllDrugs().add((Drug) data.getParcelableExtra("Drug"));

        }else if (requestCode == REQUEST_CODE_UPDATE && resultCode == RESULT_OK){
            //Drug drug = (Drug) data.getParcelableExtra("Drug");
            //int id = data.getIntExtra("idActualDrug", 1000);
            //db.updateDrug(drug, id);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
         if (REQUEST_CODE_VALUE == REQUEST_CODE_UPDATE) {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("Prescription", actualPrescription);
            returnIntent.putExtra("idActualPrescription", idActualPrescription);
            setResult(Activity.RESULT_OK,returnIntent);
            finish();
        }else {
             super.onBackPressed();
         }
    }
}

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

import Modele.DataStorage.DataBase;
import Modele.adapter.BasicAdapter;
import Modele.modelesClass.Drug;

public class ListDrugsActivity extends AppCompatActivity {
    private DataBase db;
    private ListView listViewDrug;
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

        this.adapter = new BasicAdapter(db.getAllDrugs(), this);
        this.listViewDrug = (ListView) findViewById(R.id.listViewDrugs);
        this.listViewDrug.setAdapter(adapter);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(context,DrugActivity.class);
                intent.putExtra(REQUEST_CODE, REQUEST_CODE_ADD);
                startActivityForResult(intent, REQUEST_CODE_ADD);
            }
        });

        this.listViewDrug.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                intent = new Intent(context,DrugActivity.class);
                intent.putExtra("Drug", db.getAllDrugs().get(i));
                intent.putExtra(REQUEST_CODE, REQUEST_CODE_UPDATE);
                intent.putExtra("idActualDrug", i );
                startActivityForResult(intent, REQUEST_CODE_UPDATE);
            }
        });

        this.listViewDrug.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Drug actualDrug = db.getAllDrugs().get(i);
                String nameDrug = actualDrug.getName();

                for (int t = 0 ; t < db.getPrescriptionList().size(); t++){
                    for (int j= 0 ; j < db.getPrescriptionList().get(t).getDrugList().size(); j++){
                        if (db.getPrescriptionList().get(t).getDrugList().get(j).getName().equals(nameDrug)){
                            db.getPrescriptionList().get(t).getDrugList().remove(j);
                        }
                    }
                }

                db.deleteDrug(i);
                adapter.notifyDataSetChanged();
                return true;
            }
        });

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE_ADD && resultCode == RESULT_OK) {
            this.db.getAllDrugs().add((Drug) data.getParcelableExtra("Drug"));

        }else if (requestCode == REQUEST_CODE_UPDATE && resultCode == RESULT_OK){
            Drug drug = (Drug) data.getParcelableExtra("Drug");
            int id = data.getIntExtra("idActualDrug", 1000);
            db.updateDrug(drug, id);
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

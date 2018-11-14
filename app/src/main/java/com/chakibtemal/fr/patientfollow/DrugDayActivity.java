package com.chakibtemal.fr.patientfollow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Modele.adapter.AdapterDrugDay;
import Modele.modelesClass.Drug;
import Modele.modelesClass.DrugDay;
import Modele.modelesClass.Prescription;

public class DrugDayActivity extends AppCompatActivity {

    private final static String DATA_PERSCRETION = "perscreption";
    private ListView drugDayListView;
    private List<Prescription> listPrescription = new ArrayList<Prescription>();
    private List<DrugDay> listDrugDay = new ArrayList<DrugDay>();
    private AdapterDrugDay adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_day);

        Bundle bundle = getIntent().getExtras();
        this.listPrescription  = bundle.getParcelableArrayList(DATA_PERSCRETION);

        for (Prescription prescription : this.listPrescription){
            for (Drug drug : prescription.getDrugList()){
                for (Integer hour : drug.getTimeToTake()){
                    listDrugDay.add(new DrugDay(drug.getName(), hour));
                }
            }
        }

        Collections.sort(listDrugDay, new Comparator<DrugDay>(){
            public int compare(DrugDay o1, DrugDay o2){
                return o1.getTakeHours() - o2.getTakeHours();
            }
        });

        this.adapter = new AdapterDrugDay(this.listDrugDay, this);
        this.drugDayListView = (ListView) findViewById(R.id.listDrugDay);
        this.drugDayListView.setAdapter(this.adapter);
    }

}

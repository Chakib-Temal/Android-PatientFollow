package com.chakibtemal.fr.patientfollow;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;

import Modele.DataStorage.DataBase;
import Modele.modelesClass.Drug;

public class DrugActivity extends AppCompatActivity {
    private final static int REQUEST_CODE_ADD = 0;
    private final static int REQUEST_CODE_UPDATE = 1;
    private final static String DATA_BASE = "db";
    private static String REQUEST_CODE_KEY = "requestCode" ;
    private static int REQUEST_CODE_VALUE ;
    private DataBase db;
    private Intent intent;
    private Context context;
    private int idActualDrug;
    private Drug actualDrug;

    private EditText nameDrugEditText;
    private EditText descriptionDrugEditText;
    private EditText typeDrugEditText;
    private EditText frequecyDrugEditText;
    private EditText takingTimeEditText;
    private ImageView drugImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug);
        this.context = this;
        intent = getIntent();

        this.db = (DataBase) intent.getExtras().getParcelable(DATA_BASE);
        REQUEST_CODE_VALUE = (int) intent.getExtras().getInt(REQUEST_CODE_KEY);


        this.nameDrugEditText = (EditText) findViewById(R.id.nameDrug);
        this.descriptionDrugEditText = (EditText) findViewById(R.id.drugDescription);
        this.typeDrugEditText = (EditText) findViewById(R.id.drugType);
        this.frequecyDrugEditText = (EditText) findViewById(R.id.drugFrequencyPerDey);
        this.takingTimeEditText = (EditText) findViewById(R.id.takingTime);

        this.drugImageView = (ImageView) findViewById(R.id.photoDrug);


        if (REQUEST_CODE_VALUE == REQUEST_CODE_ADD){
            this.nameDrugEditText.setHint("add name...");

        }
        else if (REQUEST_CODE_VALUE == REQUEST_CODE_UPDATE){
            this.idActualDrug = this.intent.getIntExtra("idActualDrug", 99);
            this.actualDrug = db.getAllDrugs().get(idActualDrug);

            this.nameDrugEditText.setText(actualDrug.getName());
            this.descriptionDrugEditText.setText(actualDrug.getDescription());
            this.typeDrugEditText.setText(actualDrug.getType());
            this.frequecyDrugEditText.setText(Integer.toString(actualDrug.getFrequencyPerDay()));
            String timeToTake= "";
            for (int i=0 ; i < actualDrug.getTimeToTake().size(); i++){
                timeToTake += Integer.toString(actualDrug.getTimeToTake().get(i)) + " H / ";
            }
            this.takingTimeEditText.setText(timeToTake);

            String uri = "@mipmap/"+actualDrug.getNamePhoto();
            int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
            Drawable drawable = ContextCompat.getDrawable(context, imageResource);
            this.drugImageView.setImageDrawable(drawable);

        }


    }
}

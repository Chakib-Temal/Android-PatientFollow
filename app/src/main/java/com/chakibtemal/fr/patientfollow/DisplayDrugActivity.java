package com.chakibtemal.fr.patientfollow;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import Modele.modelesClass.Drug;

public class DisplayDrugActivity extends AppCompatActivity {
    private Drug actualDrug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_drug);
        Intent intent = getIntent();
        this.actualDrug = (Drug) intent.getParcelableExtra("Drug");

        TextView nameDrugEditText = (TextView) findViewById(R.id.nameDrug);
        TextView descriptionDrugEditText = (TextView) findViewById(R.id.drugDescription);
        TextView typeDrugEditText = (TextView) findViewById(R.id.drugType);
        TextView frequecyDrugEditText = (TextView) findViewById(R.id.drugFrequencyPerDey);
        TextView takingTimeEditText = (TextView) findViewById(R.id.takingTime);
        ImageView drugImageView = (ImageView) findViewById(R.id.photoDrug);


        nameDrugEditText.setText(actualDrug.getName());

        descriptionDrugEditText.setText(actualDrug.getDescription());
        typeDrugEditText.setText(actualDrug.getType());
        frequecyDrugEditText.setText(Integer.toString(actualDrug.getFrequencyPerDay()));

        String timeToTake= "";
        for (int i=0 ; i < actualDrug.getTimeToTake().size(); i++){
            timeToTake += Integer.toString(actualDrug.getTimeToTake().get(i)) + " H / ";
        }
        takingTimeEditText.setText(timeToTake);

        String uri = actualDrug.getNamePhoto();
        Bitmap bitmap = BitmapFactory.decodeFile(this.getFilesDir().getPath() + "/Pictures/" + uri);
        drugImageView.setImageBitmap(bitmap);

    }
}

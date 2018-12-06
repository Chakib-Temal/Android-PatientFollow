package com.chakibtemal.fr.patientfollow;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import Modele.modelesClass.Drug;
import Modele.savaPicture.SavePictureHelper;

public class DrugActivity extends AppCompatActivity {
    private final static int REQUEST_CODE_ADD = 0;
    private final static int REQUEST_CODE_UPDATE = 1;
    private final static String DATA_BASE = "db";
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private static String REQUEST_CODE_KEY = "requestCode" ;
    private static int REQUEST_CODE_VALUE ;

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
        REQUEST_CODE_VALUE = (int) intent.getExtras().getInt(REQUEST_CODE_KEY);


        this.nameDrugEditText = (EditText) findViewById(R.id.nameDrug);
        this.descriptionDrugEditText = (EditText) findViewById(R.id.drugDescription);
        this.typeDrugEditText = (EditText) findViewById(R.id.drugType);
        this.frequecyDrugEditText = (EditText) findViewById(R.id.drugFrequencyPerDey);
        this.takingTimeEditText = (EditText) findViewById(R.id.takingTime);

        this.drugImageView = (ImageView) findViewById(R.id.photoDrug);


        if (REQUEST_CODE_VALUE == REQUEST_CODE_ADD){
            this.nameDrugEditText.setHint("add name...");
            this.actualDrug = new Drug();
            this.takingTimeEditText.setHint("exemple : 8/10/14");

        }
        else if (REQUEST_CODE_VALUE == REQUEST_CODE_UPDATE){
            this.actualDrug = (Drug) this.intent.getParcelableExtra("Drug");
            this.idActualDrug = intent.getIntExtra("idActualDrug", 1000);

            this.nameDrugEditText.setText(actualDrug.getName());

            this.descriptionDrugEditText.setText(actualDrug.getDescription());
            this.typeDrugEditText.setText(actualDrug.getType());
            this.frequecyDrugEditText.setText(Integer.toString(actualDrug.getFrequencyPerDay()));
            String timeToTake= "";
            for (int i=0 ; i < actualDrug.getTimeToTake().size(); i++){
                timeToTake += Integer.toString(actualDrug.getTimeToTake().get(i)) + " H / ";
            }
            this.takingTimeEditText.setText(timeToTake);

            String uri = actualDrug.getNamePhoto();
            Bitmap bitmap = BitmapFactory.decodeFile(context.getFilesDir().getPath() + "/Pictures/" + uri);
            this.drugImageView.setImageBitmap(bitmap);
            this.takingTimeEditText.setFocusable(false);

            this.takingTimeEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if (b) {
                        takingTimeEditText.setTextColor(getResources().getColor(R.color.colorRed));
                    }
                }
            });

        }
    }

    /**
     * Event save Button
     */
    public void saveActualDrug(View view) {
        try {
            actualDrug.setName(nameDrugEditText.getText().toString());
            actualDrug.setDescription(descriptionDrugEditText.getText().toString());
            actualDrug.setType(typeDrugEditText.getText().toString());
            actualDrug.setFrequencyPerDay((int) Integer.parseInt(frequecyDrugEditText.getText().toString()));

            if (REQUEST_CODE_VALUE == REQUEST_CODE_ADD) {
                String[] paths = takingTimeEditText.getText().toString().split("/");
                List<Integer> time = new ArrayList<Integer>();
                for (int i = 0; i < paths.length ; i++){
                    time.add(Integer.valueOf(paths[i])) ;
                }
                actualDrug.setTimeToTake(time);

                Intent returnIntent = new Intent();
                returnIntent.putExtra("Drug", actualDrug);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();

            } else if (REQUEST_CODE_VALUE == REQUEST_CODE_UPDATE) {
                String[] paths = takingTimeEditText.getText().toString().split(" H / ");
                List<Integer> time = new ArrayList<Integer>();
                for (int i = 0; i < paths.length ; i++){
                    time.add(Integer.valueOf(paths[i])) ;
                }
                actualDrug.setTimeToTake(time);

                Intent returnIntent = new Intent();
                returnIntent.putExtra("Drug", actualDrug);
                returnIntent.putExtra("idActualDrug", idActualDrug);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        }catch (Exception e){
            e.getStackTrace();
        }
    }

    public void changePicture(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            SaveImage(imageBitmap);
            Bitmap bitmap = BitmapFactory.decodeFile(context.getFilesDir().getPath() + "/Pictures/" + actualDrug.getNamePhoto());
            drugImageView.setImageBitmap(bitmap);
        }
    }

    public void SaveImage(Bitmap finalBitmap) {
        SavePictureHelper savePictureHelper = new SavePictureHelper(context);
        actualDrug.setNamePhoto(savePictureHelper.saveImage(finalBitmap));

    }
}

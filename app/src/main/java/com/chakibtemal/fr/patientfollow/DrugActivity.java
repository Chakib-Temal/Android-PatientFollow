package com.chakibtemal.fr.patientfollow;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import Modele.DataStorage.DataBase;
import Modele.modelesClass.Drug;

public class DrugActivity extends AppCompatActivity {
    private final static int REQUEST_CODE_ADD = 0;
    private final static int REQUEST_CODE_UPDATE = 1;
    private final static String DATA_BASE = "db";
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private static String REQUEST_CODE_KEY = "requestCode" ;
    private static int REQUEST_CODE_VALUE ;

    private String CURRENT_PHOTO_PATH;

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



    public void saveActualDrug(View view) {
        db.getAllDrugs().set(idActualDrug, actualDrug);
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
            drugImageView.setImageBitmap(imageBitmap);
            SaveImage(imageBitmap);


            Bitmap bitmap = BitmapFactory.decodeFile(context.getFilesDir().getPath() + "/Pictures/" + CURRENT_PHOTO_PATH);
            drugImageView.setImageBitmap(bitmap);

        }
    }





    private void SaveImage(Bitmap finalBitmap) {

        String root = context.getFilesDir().getPath();

        File myDir = new File(root + "/Pictures");
        System.out.println("roooooot" + root);
        myDir.mkdirs();

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fname = "JPEG_" + timeStamp + "_";
        CURRENT_PHOTO_PATH = fname;
        File file = new File (myDir, fname);
        if (file.exists ()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

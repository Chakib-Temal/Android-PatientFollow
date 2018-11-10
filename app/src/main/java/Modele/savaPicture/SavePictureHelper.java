package Modele.savaPicture;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import Modele.modelesClass.Drug;

public class SavePictureHelper {

    private Context context;

    public SavePictureHelper(Context context) {
        this.context = context;
    }

    public String  saveImage(Bitmap finalBitmap){
        String root = context.getFilesDir().getPath();

        File myDir = new File(root + "/Pictures");
        myDir.mkdirs();

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fname = "JPEG_" + timeStamp + "_";

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
        return fname;
    }

    public void savePrincipalPicture (Drug actualDrug, int id){
        //String uri = actualDrug.getNamePhoto();
        //int imageResource = context.getResources().getIdentifier("@mipmap/" + uri, null, context.getPackageName());

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), id);
        actualDrug.setNamePhoto(this.saveImage(bitmap));

    }
}

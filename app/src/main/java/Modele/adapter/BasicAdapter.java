package Modele.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.chakibtemal.fr.patientfollow.R;

import java.util.List;

import Modele.modelesClass.Drug;

public class BasicAdapter extends BaseAdapter {
    private List<Drug> listDrug;
    private Context context;

    public BasicAdapter(List<Drug> listDrug, Context context) {
        this.listDrug = listDrug;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listDrug.size();
    }

    @Override
    public Object getItem(int i) {
        return listDrug.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View root = inflater.inflate(R.layout.item_list_drug, null);
        Drug drug = (Drug) getItem(i);

        String uri = drug.getNamePhoto();
        Bitmap bitmap = BitmapFactory.decodeFile(context.getFilesDir().getPath() + "/Pictures/" + uri);

        TextView nameDrug = (TextView) root.findViewById(R.id.nameDrug);
        ImageView imageDrug = (ImageView) root.findViewById(R.id.namePhotoDrug);


        nameDrug.setText(drug.getName());
        imageDrug.setImageBitmap(bitmap);

        return root;
    }
}

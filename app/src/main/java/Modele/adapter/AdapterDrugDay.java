package Modele.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chakibtemal.fr.patientfollow.R;

import java.util.Calendar;
import java.util.List;

import Modele.modelesClass.DrugDay;

public class AdapterDrugDay extends BaseAdapter {
    protected List<DrugDay> listDrug;
    protected Context context;

    public AdapterDrugDay(List<DrugDay> listDrug, Context context) {
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

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View root = inflater.inflate(R.layout.item_list_drug_day, null);
        DrugDay drug = (DrugDay) getItem(i);

        LinearLayout body = (LinearLayout) root.findViewById(R.id.body);
        TextView nameDrug = (TextView) root.findViewById(R.id.nameDrug);
        TextView hoursDrug = (TextView) root.findViewById(R.id.hoursTakeDrug);


        nameDrug.setText(drug.getName());
        hoursDrug.setText(Integer.toString(drug.getTakeHours()) + " H");

        Calendar rightNow = Calendar.getInstance();
        int currentHourIn24Format = rightNow.get(Calendar.HOUR_OF_DAY);

        if (currentHourIn24Format > drug.getTakeHours()){
            body.setBackgroundColor(context.getResources().getColor(R.color.colorRed));
        }else if (currentHourIn24Format < drug.getTakeHours()){
            body.setBackgroundColor(context.getResources().getColor(R.color.colorGreen));
        }else if(currentHourIn24Format == drug.getTakeHours()) {
            body.setBackgroundColor(context.getResources().getColor(R.color.colorYelow));
        }

        return root;
    }
}

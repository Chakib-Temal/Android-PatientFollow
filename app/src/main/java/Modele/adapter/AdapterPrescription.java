package Modele.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.chakibtemal.fr.patientfollow.R;

import java.util.List;

import Modele.modelesClass.Drug;
import Modele.modelesClass.Prescription;

public class AdapterPrescription extends BaseAdapter {
    protected List<Prescription> listPrescription;
    protected Context context;

    public AdapterPrescription(List<Prescription> listPrescription, Context context) {
        this.listPrescription = listPrescription;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listPrescription.size();
    }

    @Override
    public Object getItem(int i) {
        return listPrescription.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View root = inflater.inflate(R.layout.item_prescription, null);
        final Prescription prescription = (Prescription) getItem(i);

        final LinearLayout body = (LinearLayout) root.findViewById(R.id.body);
        TextView idPrescription = (TextView) root.findViewById(R.id.idPrescription);
        TextView drugsOfPresritpion = (TextView) root.findViewById(R.id.listDrug);
        Switch simpleSwitch = (Switch) root.findViewById(R.id.simpleSwitch);
        simpleSwitch.setFocusable(false);
        if(prescription.isActivated()){
            simpleSwitch.setChecked(true);
        }else {
            simpleSwitch.setChecked(false);
        }

        idPrescription.setText(Integer.toString(i+1));
        String allDrugs = "";
        for (Drug drug : prescription.getDrugList()){
            allDrugs += drug.getName() + " / ";
        }
        drugsOfPresritpion.setText(allDrugs);

        if (prescription.isActivated()){
            body.setBackgroundColor(context.getResources().getColor(R.color.colorClearBlue));
        }else {
            body.setBackgroundColor(context.getResources().getColor(R.color.DefaultColor));
        }


        simpleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                if (isChecked){
                    prescription.setActivated(true);
                    body.setBackgroundColor(context.getResources().getColor(R.color.colorClearBlue));
                }else {
                    prescription.setActivated(false);
                    body.setBackgroundColor(context.getResources().getColor(R.color.DefaultColor));
                }
            }
        });


        return root;
    }
}








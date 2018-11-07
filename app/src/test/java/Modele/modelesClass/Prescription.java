package Modele.modelesClass;

import java.util.ArrayList;
import java.util.List;

public class Prescription {
    private List<Drug> drugList = new ArrayList<Drug>();

    public List<Drug> getDrugList() {
        return drugList;
    }

    public void setDrugList(List<Drug> drugList) {
        this.drugList = drugList;
    }

    public void addDrug(Drug drug){
        this.drugList.add(drug);
    }

    public void populate(){
        this.drugList.add(new Drug("Doliprane", "doliprane.png", "pellule", 3, new int[]{1,2,0}));
        this.drugList.add(new Drug("Lyrica", "Lyrica.png", "pellule", 2, new int[]{1,2,0}));
    }
}

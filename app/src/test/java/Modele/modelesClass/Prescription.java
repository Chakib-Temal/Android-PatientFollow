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
}

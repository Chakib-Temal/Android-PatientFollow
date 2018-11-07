package Modele.DataStorage;

import java.util.ArrayList;
import java.util.List;

import Modele.modelesClass.Prescription;

public class Modele {
    private List<Prescription> drugsList = new ArrayList<Prescription>();


    public List<Prescription> getDrugsList() {
        return drugsList;
    }

    public void setDrugsList(List<Prescription> drugsList) {
        this.drugsList = drugsList;
    }

    public void addPrescription (Prescription p){
        this.drugsList.add(p);
    }
}

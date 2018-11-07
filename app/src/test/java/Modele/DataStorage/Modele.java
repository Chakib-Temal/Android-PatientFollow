package Modele.DataStorage;

import java.util.ArrayList;
import java.util.List;

import Modele.modelesClass.Prescription;

public class Modele {
    private List<Prescription> PrescriptionList = new ArrayList<Prescription>();


    public List<Prescription> getDrugsList() {
        return PrescriptionList;
    }

    public void setDrugsList(List<Prescription> drugsList) {
        this.PrescriptionList = drugsList;
    }

    public void addPrescription (Prescription p){
        this.PrescriptionList.add(p);
    }

    public void populatePrescription(){
        this.PrescriptionList.add(new Prescription().populateDrugs());
    }


}

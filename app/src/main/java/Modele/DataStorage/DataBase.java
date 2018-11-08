package Modele.DataStorage;

import java.util.ArrayList;
import java.util.List;

import Modele.modelesClass.Drug;
import Modele.modelesClass.Prescription;

public class DataBase {

    private List<Prescription> PrescriptionList = new ArrayList<Prescription>();
    private List<Drug> allDrugs = new ArrayList<Drug>();

    public DataBase(){
        this.populatePrescriptionStart();
        this.populateSameDugsStart();
    }

    public List<Prescription> getPrescriptionList() {
        return PrescriptionList;
    }

    public void setPrescriptionList(List<Prescription> prescriptionList) {
        PrescriptionList = prescriptionList;
    }

    public List<Drug> getAllDrugs() {
        return allDrugs;
    }

    public void setAllDrugs(List<Drug> allDrugs) {
        this.allDrugs = allDrugs;
    }

    public List<Prescription> getDrugsList() {
        return PrescriptionList;
    }

    public void setDrugsList(List<Prescription> drugsList) {
        this.PrescriptionList = drugsList;
    }

    public void addPrescription (Prescription p){
        this.PrescriptionList.add(p);
    }

    public void populatePrescriptionStart(){
        this.PrescriptionList.add(new Prescription().populateDrugs());
        this.PrescriptionList.add(new Prescription().populateDrugs());
    }

    public void populateSameDugsStart(){
        this.allDrugs.add(new Drug("Doliprane", "doliprane.png", "pellule", 3, new int[]{1,2,0}));
        this.allDrugs.add(new Drug("Lyrica", "Lyrica.png", "pellule", 2, new int[]{1,2,0}));
    }


}

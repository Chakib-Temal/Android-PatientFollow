package Modele.DataStorage;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import Modele.modelesClass.Drug;
import Modele.modelesClass.Prescription;

public class DataBase implements Parcelable {

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
        List<Integer> time = new ArrayList<Integer>();
        time.add(1);time.add(1);time.add(1);
        this.allDrugs.add(new Drug("Doliprane", "doliprane", "doliprane", "pellule", 3, time));
        this.allDrugs.add(new Drug("Lyrica", "lyrica", "lyrica", "pellule", 3, time));
    }



    protected DataBase(Parcel in) {
        if (in.readByte() == 0x01) {
            PrescriptionList = new ArrayList<Prescription>();
            in.readList(PrescriptionList, Prescription.class.getClassLoader());
        } else {
            PrescriptionList = null;
        }
        if (in.readByte() == 0x01) {
            allDrugs = new ArrayList<Drug>();
            in.readList(allDrugs, Drug.class.getClassLoader());
        } else {
            allDrugs = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (PrescriptionList == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(PrescriptionList);
        }
        if (allDrugs == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(allDrugs);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<DataBase> CREATOR = new Parcelable.Creator<DataBase>() {
        @Override
        public DataBase createFromParcel(Parcel in) {
            return new DataBase(in);
        }

        @Override
        public DataBase[] newArray(int size) {
            return new DataBase[size];
        }
    };
}

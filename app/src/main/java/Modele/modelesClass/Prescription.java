package Modele.modelesClass;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Prescription implements Parcelable {
    private List<Drug> drugList;

    public Prescription(){
        this.drugList = new ArrayList<Drug>();
    }

    public List<Drug> getDrugList() {
        return drugList;
    }

    public void setDrugList(List<Drug> drugList) {
        this.drugList = drugList;
    }

    public void addDrug(Drug drug){
        this.drugList.add(drug);
    }

    public Prescription populateDrugs(){
        List<Integer> time = new ArrayList<Integer>();
        time.add(1);time.add(1);time.add(1);
        this.drugList.add(new Drug("Doliprane","doliprane", "doliprane", "pellule", 3,time));
        this.drugList.add(new Drug("Lyrica","lyrica", "lyrica", "pellule", 3, time));
        return this;
    }

    protected Prescription(Parcel in) {
        if (in.readByte() == 0x01) {
            drugList = new ArrayList<Drug>();
            in.readList(drugList, Drug.class.getClassLoader());
        } else {
            drugList = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (drugList == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(drugList);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Prescription> CREATOR = new Parcelable.Creator<Prescription>() {
        @Override
        public Prescription createFromParcel(Parcel in) {
            return new Prescription(in);
        }

        @Override
        public Prescription[] newArray(int size) {
            return new Prescription[size];
        }
    };
}
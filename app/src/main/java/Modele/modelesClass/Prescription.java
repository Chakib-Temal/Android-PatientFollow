package Modele.modelesClass;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Prescription implements Parcelable {
    private List<Drug> drugList;
    private boolean isActivated;

    public Prescription() {
        this.drugList = new ArrayList<Drug>();
        this.isActivated = true;
    }

    public List<Drug> getDrugList() {
        return drugList;
    }

    public void setDrugList(List<Drug> drugList) {
        this.drugList = drugList;
    }

    public void addDrug(Drug drug) {
        this.drugList.add(drug);
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    public Prescription populateDrugs(List<Drug> drugList) {
        this.drugList = drugList;
        return this;
    }

    public void deleteDrug(int i){
        this.getDrugList().remove(i);
    }

    protected Prescription(Parcel in) {
        if (in.readByte() == 0x01) {
            drugList = new ArrayList<Drug>();
            in.readList(drugList, Drug.class.getClassLoader());
        } else {
            drugList = null;
        }
        isActivated = in.readByte() != 0x00;
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
        dest.writeByte((byte) (isActivated ? 0x01 : 0x00));
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
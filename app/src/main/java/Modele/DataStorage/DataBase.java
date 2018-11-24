package Modele.DataStorage;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;

import com.chakibtemal.fr.patientfollow.R;

import java.util.ArrayList;
import java.util.List;

import Modele.modelesClass.Drug;
import Modele.modelesClass.Prescription;
import Modele.savaPicture.SavePictureHelper;

public class DataBase implements Parcelable {

    private List<Prescription> PrescriptionList = new ArrayList<Prescription>();
    private List<Drug> allDrugs = new ArrayList<Drug>();

    public DataBase(Context context){
        this.populateSameDugsStart(context);
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

    public String getOldNameOfDrug(int id) {
        return this.getAllDrugs().get(id).getName();
    }

    public void setDrugsList(List<Prescription> drugsList) {
        this.PrescriptionList = drugsList;
    }

    public void addPrescription (Prescription p){
        this.PrescriptionList.add(p);
    }

    public  void removePrescreption (int id){
        this.getPrescriptionList().remove(id);
    }

    public void updateDrug(Drug drug, int id){
        this.allDrugs.set(id,drug);
    }

    public void deleteDrug(int index){
        this.getAllDrugs().remove(index);
    }

    public void addDrug(Drug drug){
        this.allDrugs.add(drug);
    }

    public void updatePrescription (Prescription prescription, int id){
        this.getPrescriptionList().set(id, prescription);
    }

    public void deletePrescription(int i){
        this.getPrescriptionList().remove(i);
    }


    public void populateSameDugsStart(Context context){
        List<Integer> time = new ArrayList<Integer>();
        time.add(0);time.add(12);time.add(22);
        Drug doliprane = new Drug("Doliprane", "Good Drug", "doliprane", "Capsule", 3, time);
        Drug lyrica = new Drug("Lyrica", "Pfizer Drug", "lyrica", "Capsule", 3, time);

        SavePictureHelper savePictureHelper = new SavePictureHelper(context);
        savePictureHelper.savePrincipalPicture(doliprane, R.mipmap.doliprane);
        SystemClock.sleep(1000);
        savePictureHelper.savePrincipalPicture(lyrica, R.mipmap.lyrica);


        this.allDrugs.add(doliprane);
        this.allDrugs.add(lyrica);

        this.PrescriptionList.add(new Prescription().addDrugs(doliprane).addDrugs(lyrica));
        this.PrescriptionList.add(new Prescription().addDrugs(lyrica).addDrugs(doliprane));
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

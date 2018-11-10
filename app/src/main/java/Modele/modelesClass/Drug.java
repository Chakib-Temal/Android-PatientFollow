package Modele.modelesClass;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Drug implements  Parcelable {
    private String name;
    private String description;
    private String namePhoto;
    private String type;
    private int frequencyPerDay;
    private List<Integer> timeToTake;

    public Drug(String name,String description, String namePhoto, String type, int frequencyPerDay, List<Integer> timeToTake) {
        this.name = name;
        this.description = description;
        this.namePhoto = namePhoto;
        this.type = type;
        this.frequencyPerDay = frequencyPerDay;
        this.timeToTake = timeToTake;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        description = description;
    }

    public String getNamePhoto() {
        return namePhoto;
    }

    public void setNamePhoto(String namePhoto) {
        this.namePhoto = namePhoto;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getFrequencyPerDay() {
        return frequencyPerDay;
    }

    public void setFrequencyPerDay(int frequencyPerDay) {
        this.frequencyPerDay = frequencyPerDay;
    }

    public List<Integer> getTimeToTake() {
        return timeToTake;
    }

    public void setTimeToTake(List<Integer> timeToTake) {
        this.timeToTake = timeToTake;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int describeContents() {
        return 0;
    }


    protected Drug(Parcel in) {
        name = in.readString();
        description = in.readString();
        namePhoto = in.readString();
        type = in.readString();
        frequencyPerDay = in.readInt();
        if (in.readByte() == 0x01) {
            timeToTake = new ArrayList<Integer>();
            in.readList(timeToTake, Integer.class.getClassLoader());
        } else {
            timeToTake = null;
        }
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(namePhoto);
        dest.writeString(type);
        dest.writeInt(frequencyPerDay);
        if (timeToTake == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(timeToTake);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Drug> CREATOR = new Parcelable.Creator<Drug>() {
        @Override
        public Drug createFromParcel(Parcel in) {
            return new Drug(in);
        }

        @Override
        public Drug[] newArray(int size) {
            return new Drug[size];
        }
    };
}
package Modele.modelesClass;

import java.util.ArrayList;
import java.util.List;

public class Drug {
    private String name;
    private String namePhoto;
    private String type;
    private int frequencyPerDay;
    private List<Integer> timeToTake = new ArrayList<Integer>();

    public Drug(String name, String namePhoto, String type, int frequencyPerDay, List<Integer> timeToTake) {
        this.name = name;
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

    public void addTime(int time){
        this.timeToTake.add(time);
    }
}

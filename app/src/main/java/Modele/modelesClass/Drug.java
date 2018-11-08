package Modele.modelesClass;

public class Drug {
    private String name;
    private String namePhoto;
    private String type;
    private int frequencyPerDay;
    private int [] timeTotake;

    public Drug(String name, String namePhoto, String type, int frequencyPerDay, int [] timeToTake) {
        this.name = name;
        this.namePhoto = namePhoto;
        this.type = type;
        this.frequencyPerDay = frequencyPerDay;
        this.timeTotake = timeToTake;
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


    public int[] getTimeTotake() {
        return timeTotake;
    }

    public void setTimeTotake(int[] timeTotake) {
        this.timeTotake = timeTotake;
    }


}

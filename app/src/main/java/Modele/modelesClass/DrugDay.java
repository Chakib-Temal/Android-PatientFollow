package Modele.modelesClass;

public class DrugDay {
    private String name;
    private int takeHours;

    public DrugDay(String name, int takeHours) {
        this.name = name;
        this.takeHours = takeHours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTakeHours() {
        return takeHours;
    }

    public void setTakeHours(int takeHours) {
        this.takeHours = takeHours;
    }
}

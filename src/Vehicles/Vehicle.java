package Vehicles;

public abstract class Vehicle {

    private int time;
    private boolean isEmergency;

    public Vehicle() {

        time = 0;
        isEmergency = false;
    }

    public Vehicle(int time, boolean isEmergency) {

        this.time = time;
        this.isEmergency = isEmergency;
    }

    public abstract int getTime(); {}

    public abstract boolean getIsEmergency(); {}
}

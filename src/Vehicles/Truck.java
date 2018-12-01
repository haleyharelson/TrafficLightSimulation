package Vehicles;

public class Truck extends Vehicle {

    public Truck() {

        super(15, false);
    }

    @Override
    public int getTime() {
        return 15;
    }

    @Override
    public boolean getIsEmergency() {
        return false;
    }
}

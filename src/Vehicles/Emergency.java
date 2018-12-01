package Vehicles;

public class Emergency extends Vehicle {

    public Emergency() {

        super(5, true);
    }

    @Override
    public int getTime() {
        return 5;
    }

    @Override
    public boolean getIsEmergency() {
        return false;
    }
}

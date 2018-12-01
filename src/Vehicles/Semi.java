package Vehicles;

public class Semi extends Vehicle {

    public Semi() {

        super(20, false);
    }

    @Override
    public int getTime() {
        return 20;
    }

    @Override
    public boolean getIsEmergency() {
        return false;
    }
}

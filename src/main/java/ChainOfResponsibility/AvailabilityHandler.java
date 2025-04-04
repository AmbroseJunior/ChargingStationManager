package ChainOfResponsibility;

import vao.ChargingStation;
import vao.User;

public class AvailabilityHandler extends ChargingHandler{
    @Override
    public boolean handle(User user, ChargingStation station) {
        if(!station.isAvailable()){
            System.out.println("Charging Station not available");
            return false;
        }
        return nextHandler != null? nextHandler.handle(user, station) : true;
    }

}

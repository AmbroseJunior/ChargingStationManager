package ChainOfResponsibility;

import vao.ChargingStation;
import vao.User;

public class CompatibilityHandler extends ChargingHandler{
    @Override
    public boolean handle(User user, ChargingStation station) {
        if (!isCompatible(user.getCarType(), station.getConnectorType())) {
            System.out.println("The car type is not compatible with the connector type");
            return false;
        }
       return nextHandler != null? nextHandler.handle(user, station) : true;
    }

    public boolean isCompatible(String carType, String connectionType) {
        return carType.equalsIgnoreCase(connectionType);
    }
}

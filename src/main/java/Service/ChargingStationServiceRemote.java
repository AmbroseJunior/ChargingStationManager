package Service;

import jakarta.ejb.Remote;
import vao.ChargingStation;
import vao.User;

@Remote
public interface ChargingStationServiceRemote {
    boolean canChargeAtStation(User user, ChargingStation chargingStation);
}

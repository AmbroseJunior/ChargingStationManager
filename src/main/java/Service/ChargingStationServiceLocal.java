package Service;

import vao.ChargingStation;
import vao.User;
import jakarta.ejb.Local;
import java.util.List;

@Local
public interface ChargingStationServiceLocal {
    void addChargingStation(ChargingStation station);
    ChargingStation getChargingStationById(int id);
    List<ChargingStation> getAllChargingStations();
    void updateChargingStation(ChargingStation station);
    void deleteChargingStation(int id);
    boolean canCarChargeAtStation(User user, ChargingStation station);
}
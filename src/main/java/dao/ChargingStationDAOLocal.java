package dao;

import jakarta.ejb.Local;
import vao.ChargingStation;
import java.util.List;

@Local
public interface ChargingStationDAOLocal {

    void save (ChargingStation station);
    ChargingStation findById (int id);
    List<ChargingStation> findAll();
    void update (ChargingStation station);
    void delete (int id);
}

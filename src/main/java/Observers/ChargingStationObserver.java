package Observers;

import vao.ChargingStation;

public interface ChargingStationObserver {
    void update(ChargingStation station, String previousStatus);

}

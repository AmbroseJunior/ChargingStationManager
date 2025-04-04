package Observers;

import vao.ChargingStation;

public class ProviderNotifier implements ChargingStationObserver {
    @Override
    public void update(ChargingStation station, String previousStatus) {
        String status = station.getStatus();
        if (!status.equals(previousStatus)) {
            System.out.println("\n🏢 Ponudnik obveščen: Polnilnica " + station.getLocation()
                    + " pri ponudniku " + station.getProvider().getName() + " je zdaj " + getStatusText(status));
        }
    }

    private String getStatusText(String status) {
        switch(status) {
            case "OCCUPIED": return "zasedena";
            case "MAINTENANCE": return "v vzdrževanju";
            default: return "prosta";
        }
    }
}
package Observers;

import vao.ChargingStation;

public class ChargingStationDisplay implements ChargingStationObserver {
    @Override
    public void update(ChargingStation station, String previousStatus) {
        System.out.println("\n📟 [Charging Station Display] Current Status of the Charging Station:");
        System.out.println(formatChargingStationInfo(station));
        System.out.println("📟 [Charging Station Display] Previous Status: " + previousStatus);
        System.out.println("📟 [Charging Station Display] Current Status: " + station.getStatus());
    }

    private String formatChargingStationInfo(ChargingStation station) {
        // Customize this method according to the charging station's attributes
        return "Station Location: " + station.getLocation() + "\n" +
                "Charging Speed: " + station.getChargingSpeed() + " kW\n" +
                "Current User Email: " + station.getCurrentUserEmail() + "\n" +
                "Provider: " + station.getProvider().getName();
    }
}


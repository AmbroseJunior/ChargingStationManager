package Observers;

import vao.ChargingStation;

public class UserNotifier implements ChargingStationObserver {
    @Override
    public void update(ChargingStation station, String previousStatus) {
        if ("OCCUPIED".equals(station.getStatus())) {
            sendStartEmail(station.getCurrentUserEmail(), "Charging Session Started");
        } else if ("OPERATIONAL".equals(station.getStatus()) && "OCCUPIED".equals(previousStatus)) {
            sendEndEmail(station.getCurrentUserEmail(), "Charging Session Stopped");
        }
    }

    public static void sendStartEmail(String email, String subject) {
        System.out.println("\n📩 [EMAIL] From: noreply@chargingstations.com");
        System.out.println("📩 To: " + email);
        System.out.println("📩 Subject: " + subject + " ⚡");
        System.out.println("Hello,\n\nYour charging session has started successfully.\n\nBest regards,\nEV Platform Team");
    }

    public static void sendEndEmail(String email, String subject) {
        System.out.println("\n📩 [EMAIL] From: noreply@chargingstations.com");
        System.out.println("📩 To: " + email);
        System.out.println("📩 Subject: " + subject + " ✅");
        System.out.println("Hello,\n\nYour charging session has ended.\n\nBest regards,\nEV Platform Team");
    }
}
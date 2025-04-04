package Iterators;

import vao.ChargingStation;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class ChargerSpeedIterator implements Iterator<ChargingStation> {
    private final List<ChargingStation> stations;
    private final double minSpeed;
    private int currentIndex = 0;

    public ChargerSpeedIterator(List<ChargingStation> stations, double minSpeed) {
        this.stations = stations;
        this.minSpeed = minSpeed;
    }

    @Override
    public boolean hasNext() {
        while (currentIndex < stations.size()) {
            if (stations.get(currentIndex).getChargingSpeed() >= minSpeed) {
                return true;
            }
            currentIndex++;
        }
        return false;
    }

    @Override
    public ChargingStation next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more stations with speed above " + minSpeed);
        }
        return stations.get(currentIndex++);
    }
}

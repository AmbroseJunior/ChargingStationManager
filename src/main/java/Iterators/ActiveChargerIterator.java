package Iterators;

import vao.ChargingStation;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class ActiveChargerIterator implements Iterator<ChargingStation> {
    private final List<ChargingStation> stations;
    private int currentIndex = 0;

    public ActiveChargerIterator(List<ChargingStation> stations) {
        this.stations = stations;
    }

    @Override
    public boolean hasNext() {
        while (currentIndex < stations.size()) {
            if ("OPERATIONAL".equalsIgnoreCase(stations.get(currentIndex).getStatus())) {
                return true;
            }
            currentIndex++;
        }
        return false;
    }

    @Override
    public ChargingStation next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more active stations.");
        }
        return stations.get(currentIndex++);
    }
}

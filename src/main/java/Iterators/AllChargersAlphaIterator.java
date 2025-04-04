package Iterators;

import vao.ChargingStation;

import java.util.*;

public class AllChargersAlphaIterator implements Iterator<ChargingStation> {
    private final List<ChargingStation> stations;
    private int currentIndex = 0;

    public AllChargersAlphaIterator(List<ChargingStation> stations) {
        this.stations = new ArrayList<>(stations);
        Collections.sort(this.stations, Comparator.comparing(ChargingStation::getLocation, String.CASE_INSENSITIVE_ORDER));
    }

    @Override
    public boolean hasNext() {
        return currentIndex < stations.size();
    }

    @Override
    public ChargingStation next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more charging stations.");
        }
        return stations.get(currentIndex++);
    }
}

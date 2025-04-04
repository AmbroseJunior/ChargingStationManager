package Iterators;

import vao.ChargingStation;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class ChargerRegionIterator implements Iterator<ChargingStation> {
    private final List<ChargingStation> stations;
    private final String region;
    private int currentIndex = 0;

    public ChargerRegionIterator(List<ChargingStation> stations, String region) {
        this.stations = stations;
        this.region = region;
    }

    @Override
    public boolean hasNext() {
        while (currentIndex < stations.size()) {
            if (stations.get(currentIndex).getRegion().equalsIgnoreCase(region)) {
                return true;
            }
            currentIndex++;
        }
        return false;
    }

    @Override
    public ChargingStation next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more stations in region: " + region);
        }
        return stations.get(currentIndex++);
    }
}

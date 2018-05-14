package macheda.com.outerspacemanager.outerspacemanager.services.responses;

import java.util.ArrayList;
import java.util.List;

import macheda.com.outerspacemanager.outerspacemanager.classes.Building;

/**
 * Created by adammacheda on 16/04/2018.
 */

public class BuildingsResponse {
    private int size;
    private List<Building> buildings;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<Building> buildings) {
        this.buildings = buildings;
    }

    public BuildingsResponse(int size, List<Building> buildings) {
        this.size = size;
        this.buildings = buildings;
    }
}

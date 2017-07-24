package com.redtoorange.warbound.buildings;

/**
 * BuildingFactory.java - Description
 *
 * @author Andrew McGuiness
 * @version 7/18/2017
 */
public class BuildingFactory {
    /**
     * Construct a default farm.
     * @param controller    Building controller for the game
     * @return  Constructed building ready to be placed.
     */
    public static Building BuildBarracks( BuildingController controller){
        return new Barracks( "Barracks", 4, 4,  controller);
    }

    public static Building BuildFarm( BuildingController controller){
        return new Farm( "Farm", 2, 2,  controller);
    }


}

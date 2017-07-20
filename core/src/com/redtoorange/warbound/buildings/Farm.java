package com.redtoorange.warbound.buildings;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.redtoorange.warbound.Resource;
import com.redtoorange.warbound.controllers.BuildingController;

/**
 * Barracks.java - Description
 *
 * @author Andrew McGuiness
 * @version 7/19/2017
 */
public class Farm extends Building {
    private int productionAmount = 10;

    public Farm( String name, TextureRegion texture, int width, int height, BuildingController controller ) {
        super( name, texture, width, height, controller );
    }

    @Override
    public boolean placeBuilding() {
        boolean success = super.placeBuilding();

        if( success && state == State.PRODUCING )
            owner.getResourceController().changeResource( Resource.FOOD_STORED, productionAmount );

        return success;
    }
}
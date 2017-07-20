package com.redtoorange.warbound.units;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.redtoorange.warbound.GameObject;
import com.redtoorange.warbound.ai.Facing;
import com.redtoorange.warbound.ai.UnitOrder;
import com.redtoorange.warbound.controllers.PlayerController;
import com.redtoorange.warbound.controllers.UnitController;
import com.redtoorange.warbound.map.MapTile;

/**
 * Unit.java - Description
 *
 * @author Andrew McGuiness
 * @version 6/21/2017
 */
public abstract class Unit implements GameObject {
    public static String TAG = Unit.class.getSimpleName();

    protected Facing currentFacing = Facing.SOUTH;

    protected UnitController controller;
    protected PlayerController owner;

    protected float speed = 5;

    protected UnitOrder nextOrder;
    protected UnitOrder currentOrder;

    protected MapTile currentTile = null;
    protected Sprite sprite;
    protected boolean selected = false;

    public Unit( MapTile startTile, TextureRegion texture, UnitController controller){
        sprite = new Sprite( texture );
        sprite.setSize( 1, 1 );
        sprite.setPosition( startTile.getWorldPosition().x, startTile.getWorldPosition().y );

        setCurrentTile( startTile );
        this.controller = controller;
        owner = controller.getOwner();
    }

    public void update( float deltaTime){
        if( currentOrder != null ) {
            currentOrder.executeOrder( deltaTime );

            if( currentOrder.isCompleted() )
                currentOrder = null;
            else
                currentFacing = currentOrder.unitFacing;
        }

        if( currentOrder == null && nextOrder != null ){
            currentOrder = nextOrder;
            nextOrder = null;
            currentOrder.receiveOrder( this );
        }
    }

    public void draw( SpriteBatch batch){
        sprite.draw( batch );
    }

    public void select( boolean selected ){
        this.selected = selected;
    }

    public Rectangle getBoundingBox(){
        return sprite.getBoundingRectangle();
    }

    public void cancelCurrentOrder(){
        if( currentOrder != null ){
            currentOrder.cancelOrder();
        }
    }

    public void giveOrder( UnitOrder order ){
        if( currentOrder != null )
            currentOrder.cancelOrder();

        nextOrder = order;
    }

    public void move( Vector2 newPos ){
        sprite.setPosition( newPos.x, newPos.y );
    }

    public void translate( Vector2 amount ){
        sprite.translate( amount.x, amount.y );
    }

    public float getSpeed() {
        return speed;
    }

    public Vector2 getPosition(){
        return new Vector2( sprite.getX(), sprite.getY() );
    }

    public MapTile getCurrentTile() {
        return currentTile;
    }

    public void setCurrentTile( MapTile newTile ) {
        if( currentTile != null)
            currentTile.setOccupier( null );

        currentTile = newTile;
        currentTile.setOccupier( this );

    }
}

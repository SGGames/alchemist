package model.ES.processor.command;

import model.ModelManager;
import model.ES.component.command.PlanarNeededRotation;
import model.ES.component.command.PlayerControl;
import model.ES.component.motion.PlanarStance;
import model.ES.component.motion.PlanarVelocityToApply;
import util.geometry.geom2d.Point2D;
import util.math.AngleUtil;

import com.simsilica.es.Entity;
import com.simsilica.es.EntitySet;

import controller.entityAppState.Processor;

public class PlayerRotationControlProc extends Processor {
	
	@Override
	protected void registerSets() {
		register(PlanarStance.class, PlayerControl.class);
	}
	
	@Override
	protected void onUpdated(float elapsedTime) {
		if(ModelManager.command.target == null)
			return;
		
        for(EntitySet set : sets)
        	for (Entity e : set){
        		PlanarStance stance = e.get(PlanarStance.class);
        		double angleToTarget = ModelManager.command.target.getSubtraction(stance.getCoord()).getAngle();

        		// rotation
        		double neededRotAngle = 0;
        		Point2D front = stance.getCoord().getTranslation(stance.getOrientation(), 1);
        		int turn = AngleUtil.getTurn(stance.getCoord(), front, ModelManager.command.target);
        		if(turn != AngleUtil.NONE){// || angleToTarget != stance.getOrientation()){
        			double diff = AngleUtil.getSmallestDifference(stance.getOrientation(), angleToTarget);
        			if(turn >= 0)
        				neededRotAngle = diff;
        			else
        				neededRotAngle = -diff;
        		}
        		
        		if(Math.abs(neededRotAngle) > 0.01){
        			PlanarNeededRotation neededRotation = new PlanarNeededRotation(neededRotAngle);
            		setComp(e, neededRotation);
        		}
        	}
	}
}
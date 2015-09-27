package model.ES.processor.shipGear;

import com.simsilica.es.Entity;

import controller.entityAppState.Processor;
import model.ES.component.ToRemove;
import model.ES.component.shipGear.Attrition;

public class AttritionProc extends Processor {

	@Override
	protected void registerSets() {
		register(Attrition.class);
	}
	
	@Override
	protected void onEntityUpdated(Entity e, float elapsedTime) {
		if(e.get(Attrition.class).actualHitpoints <= 0)
			setComp(e, new ToRemove());
	}
}
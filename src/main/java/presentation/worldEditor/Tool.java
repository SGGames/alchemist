package presentation.worldEditor;

import model.state.SceneSelectorState;

public abstract class Tool {

	protected SceneSelectorState selector;
	
	public Tool() {
		super();
	}

	public void setSelector(SceneSelectorState selector){
		this.selector = selector;
	};
	
	public void doPrimary(){}
	public void doSecondary(){}
	public void begin(){}
}
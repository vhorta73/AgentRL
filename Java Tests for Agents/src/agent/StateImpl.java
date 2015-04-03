package agent;

import java.util.List;
/**
 * The State known by the Environment.
 * 
 * @author Vasco
 *
 */
public class StateImpl extends ValueImpl implements State {
	/**
	 * The Actions available at this state, 
	 * set by the environment.
	 */
	private final List<Action> actionsAvailable;

	/**
	 * State Constructor.
	 * 
	 * @param actions List of Actions available for this state
	 */
	public StateImpl(List<Action> actions) {
		this.actionsAvailable = actions;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Action> getAvailableActions() {
		return actionsAvailable;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addSample(Double value) {
		super.addSample(value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Double getMovingAverage() {
		return super.getMovingAverage();
	}

}

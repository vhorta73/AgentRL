package agent;
/**
 * The State action state value implementation.
 * 
 * @author Vasco
 *
 */
public class StateActionStateValueImpl implements StateActionStateValue {
	/**
	 * The previous State
	 */
	private final State previousState;
	
	/**
	 * The actions taken on previous State
	 */
	private final Action action;
	
	/**
	 * The current State
	 */
	private final State currentState;
	
	/**
	 * The Reward at current State
	 */
	private final Double reward;
	
	/**
	 * Constructor.
	 * 
	 * @param previousState the previous State
	 * @param action the action taken at previous State
	 * @param currentState the current State
	 * @param reward the value given by the environment at current State
	 */
	public StateActionStateValueImpl(State previousState, Action action, State currentState, Double reward) {
		this.previousState = previousState;
		this.action = action;
		this.currentState = currentState;
		this.reward = reward;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public State getPreviousState() {
		return previousState;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Action getAction() {
		return action;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public State getCurrentState() {
		return currentState;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Double getReward() {
		return reward;
	}

}

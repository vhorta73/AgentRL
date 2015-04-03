package agent;
/**
 * The State action state value immutable object.
 * 
 * @author Vasco
 *
 */
public interface StateActionStateValue {

	/**
	 * The Previous State.
	 * 
	 * @return State
	 */
	public State getPreviousState();
	
	/**
	 * The Action taken on previous State
	 * 
	 * @return Action
	 */
	public Action getAction();
	
	/**
	 * The Current State.
	 * 
	 * @return State
	 */
	public State getCurrentState();
	
	/**
	 * The Reward given by the Environment.
	 * 
	 * @return Double
	 */
	public Double getReward();
}

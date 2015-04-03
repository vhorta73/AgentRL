package agent;


/**
 * The PathTrack interface to track agent events between start and goal.
 * Events will then be back-propagated with decrements of gamma^index
 * from the goal to the origin, using the AgentEventsImpl.
 * 
 * @author Vasco
 *
 */
public interface PathTrack {
	/**
	 * Adds an event to a list of past events until a reward is found.
	 * At the reward point, all events would back-propagate with values
	 * to the AgentEvents.
	 * 
	 * @param previousState the state agent was in
	 * @param action the action taken at previous state
	 * @param currentState the current state agent is in
	 * @param reward the reward given by the environment at current state
	 */
	public void addEvent(State previousState, Action action, State currentState, Double reward);

}

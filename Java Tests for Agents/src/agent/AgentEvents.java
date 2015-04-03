package agent;

import java.util.List;

/**
 * Interface for managing and storing the environment ActionResponses.
 * 
 * @author Vasco
 *
 */
public interface AgentEvents {
	/**
	 * Adding a new event to the Agent's memory.
	 * 
	 * @param previousState the previous State
	 * @param action the action taken
	 * @param currentState the current State
	 * @param reward the reward given at current State.
	 */
	void addEvent(State previousState, Action action, State currentState, Double reward);

	/**
	 * For a given state, returns the best known action.
	 * 
	 * @param state the State to check
	 * @return best Action
	 */
	Action getBestAction(State state);
	
	/**
	 * For a given state, returns the sorted list of actions by value descending.
	 * 
	 * If the state has not known actions, returns an empty list.
	 * 
	 * @param state the current state
	 * @return List of Actions
	 */
	List<Action> getBestActions(State state);
}

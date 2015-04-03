package agent;

import java.util.List;

/**
 * The Environment Agent Action response.
 * 
 * @author Vasco
 *
 */
public interface ActionResponse {
	/**
	 * The State where the agent is currently in.
	 * 
	 * @return current State
	 */
    State getState();
    
    /**
     * The reward given by the environment.
     * 
     * @return Double value reward
     */
    Double getReward();
    
    /**
     * The available actions a this state.
     * 
     * @return List of actions available
     */
    List<Action> getAvailableActions();
}

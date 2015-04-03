package agent;
/**
 * The Agent interface.
 * 
 * @author Vasco
 *
 */
public interface Agent extends AgentEvents {
	/**
	 * Returns true whenever the Agent has made all required computations
	 * to know what the next Action should be.
	 * 
	 * @return true if ready
	 */
    Boolean isReady();
    
    /**
     * The Action the agent wants to make, that will be available when all
     * computations are made. This is done in conjunction with the isReady flag.
     * 
     * @return Action to apply
     */
    Action getAction();
    
    /**
     * The Environment response to previous Action.
     * Response consists of a new State and a list of possible actions on the new given state.
     * 
     * Agent updates the event accordingly and triggers an internal event to prepare for the 
     * next action to be had.
     * 
     * When the internal event finishes, it will firstly update what the next action should be, 
     * and then the ready flag.
     * 
     * @param response the Environment response
     */
    void update(ActionResponse response);

    /**
     * This is the reset button on the back of the Agent to put it on a specific state and make its
     * way from there to any known goal.
     * 
     * @param init the initial agent state and options
     */
    void reset(ActionResponse init);
}

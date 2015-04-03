package agent;

import java.util.LinkedList;
import java.util.List;

public class AgentImpl extends PathTrackImpl implements Agent {

	/**
	 * Quick access to tell the environment if the agent
	 * has finished the process of the next action to be had.
	 */
	private boolean isReadyForAction;
	
	/**
	 * The next action to be taken by the environment.
	 */
	private Action nextAction;
	
	/**
	 * The Action taken by the environment and executed.
	 */
	private Action previousAction;
	
	/**
	 * The Previous State.
	 */
	private State previousState;
	
	/**
	 * The current State.
	 */
	private State currentState;

	/**
	 * and try some other action, knowing a better one.
	 * The probabilistic value of times the Agent would go wild
	 * 
	 * e-greedy
	 */
	private final Double eGreedy = 0.2;

	/**
	 * The Constructor requesting State, action, state, reward initial values.
	 * 
	 * @param init ActionRequest
	 */
	public AgentImpl(ActionResponse init, Double gamma) {
		super(gamma);
		validateActionResponse(init);

		// Initialise internal variables.
		this.nextAction       = null;
		this.isReadyForAction = false;
		this.previousAction   = null;
		this.previousState    = null;
		this.currentState     = init.getState();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean isReady() {
		return isReadyForAction;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Action getAction() {
		// Defence ourselves against bad code practices made by the environment.
		// The Agent has the right to think about what to do before being forced to do it.
		if ( !isReadyForAction ) throw new IllegalStateException("Cannot get an Action before checking if Agent is ready.");
		if ( nextAction == null ) throw new IllegalStateException("Agent in ready state with a null Action.");

		// All is well 
		isReadyForAction = false;

		return nextAction;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(ActionResponse response) {
		validateActionResponse(response);
		State currentState = response.getState();
		Double reward = response.getReward();
		this.currentState = currentState;

		// Acknowledge the event
		addEvent(previousState, previousAction, currentState, reward);

		// Let's go greedy and do the best possible action
		if ( Math.random() < eGreedy ) {
			setNextAction(getBestAction(currentState));
		}
        // Let's not go greedy and pick a random action
		else {
			setNextAction(getRandomAction(currentState));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void reset(ActionResponse init) {
		validateActionResponse(init);

		// Initialise internal variables.
		this.nextAction       = null;
		this.isReadyForAction = false;
		this.previousAction   = null;
		this.previousState    = null;
		this.currentState     = init.getState();
	}
	
	/**
	 * Calculates a random action from a list of actions found in the given state.
	 * 
	 * @param currentState the state with available actions
	 * @return Action
	 */
	private Action getRandomAction(State currentState) {
		// Check we have a valid state.
		if ( currentState == null ) 
			throw new IllegalArgumentException("Cannot get a random action from a null state.");
		
		// Check we have actions to choose from
		List<Action> availableActions = currentState.getAvailableActions();
		if ( availableActions == null ) 
			throw new IllegalArgumentException("Actions have not been defined for given state.");
		if ( availableActions.size() == 0 ) 
			throw new IllegalAccessError("No available actions defined for given state");
		
		// Only one? Return the one.
		if ( availableActions.size() == 1 ) return availableActions.get(0);
		
		// More than one action, let's pick up one from the bowl
		int actions = availableActions.size();
		int index = (int) ( Math.random() * actions );
		return availableActions.get(index);
	}

	/**
	 * Given an action, moves the Agent state to the next,
	 * ready to apply action for the environment to pick up and process.
	 * 
	 * @param action
	 */
	private void setNextAction(Action action) {
		// Ensure expected variables have values before proceeding.
		if ( currentState == null ) throw new IllegalStateException("Cannot select next Action. Current State is not defined.");
		
		// Lose the previous state information, update the new.
		this.previousState = currentState;
		this.previousAction = action;
		this.currentState = null;
		
		// All updated, set Agent into ready state
		this.isReadyForAction = true;
	}
	
	/**
	 * Easy ActionResponse validation checks.
	 * 
	 * @param response ActionResponse to validate.
	 */
	private void validateActionResponse(ActionResponse response) {
		if ( response == null ) 
			throw new IllegalArgumentException("Null ActionResponse found.");

		// Check State
		State currentState = response.getState();
		if ( currentState == null ) 
			throw new IllegalArgumentException("Null State found.");

		// Check available actions
		List<Action> availableActions = new LinkedList<Action>();
				availableActions = response.getAvailableActions();

		if ( availableActions == null ) 
			throw new IllegalArgumentException("Null available Actions list found.");
		if ( availableActions.size() == 0 ) 
			throw new IllegalArgumentException("No Actions found for this State.");
	}
}

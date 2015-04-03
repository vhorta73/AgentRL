package agent;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Agent Memory implementation class.
 * 
 * @author Vasco
 *
 */
public class AgentEventsImpl implements AgentEvents {
	/**
	 * The state-action-state value.
	 */
	private Map<State,Map<Action,Map<State,Value>>> stateActionStateValue;

	/**
	 * Constructor setting up the initial variables.
	 */
	public AgentEventsImpl() {
		stateActionStateValue = new HashMap<State, Map<Action,Map<State,Value>>>();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addEvent(State previousState, Action action, State currentState, Double reward) {
		// Apply initial validation checks.
		if ( previousState == null ) 
			throw new IllegalArgumentException("Previous state cannot be null.");
		if ( action == null ) 
			throw new IllegalArgumentException("Taken Action cannot be null.");
		if ( currentState == null ) 
			throw new IllegalArgumentException("Current state cannot be null.");

		Value value = new ValueImpl();
		value.addSample(reward);

		if ( stateActionStateValue.get(previousState) != null ) {
			if ( stateActionStateValue.get(previousState).get(action) != null ) {
				if ( stateActionStateValue.get(previousState).get(action).get(currentState) != null ) {
					stateActionStateValue.get(previousState).get(action).get(currentState).addSample(reward);
				}
				else {
					stateActionStateValue.get(previousState).get(action).put(currentState, value);
				}
			}
			else {
				stateActionStateValue.get(previousState).put(action, new HashMap<State, Value>());
				stateActionStateValue.get(previousState).get(action).put(currentState, value);
			}
		}
		else {
			stateActionStateValue.put(previousState, new HashMap<Action, Map<State,Value>>());
			stateActionStateValue.get(previousState).put(action, new HashMap<State, Value>());
			stateActionStateValue.get(previousState).get(action).put(currentState, value);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Action getBestAction(State state) {
		Action bestAction = null;
		Double previousValue = 0.0;

		// Loop over all possible actions to find which one has the most score
		for( Action action : stateActionStateValue.get(state).keySet() ) {
			// Loop over each current state for this action and find the averaged value across them all.
		    Double actionValue = getAverage(stateActionStateValue.get(state).get(action));

		    // Check if this action value is higher than previous action.
		    if ( actionValue > previousValue ) {
		    	bestAction = action;
		    	previousValue = actionValue;
		    }
		}

		return bestAction;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Action> getBestActions(State state) {
		if ( stateActionStateValue == null ) return new LinkedList<Action>();
		if ( stateActionStateValue.get(state) == null ) return new LinkedList<Action>();

		List<Action> finalActionList = new LinkedList<Action>();
		
		// A single Action Value map.
		Map<Action,Double> actionValue = new HashMap<Action,Double>();
		List<Map<Action,Double>> tempActionValueList = new LinkedList<Map<Action,Double>>();

		// Loop over all possible actions to find which one has the most score
		for( Action action : stateActionStateValue.get(state).keySet() ) {
			// Loop over each current state for this action and find the averaged value across them all.
		    Double value = getAverage(stateActionStateValue.get(state).get(action));
		    actionValue = new HashMap<Action, Double>();
		    actionValue.put(action, value);
		    tempActionValueList.add(actionValue);
		}

		// Sort the Map
		Collections.sort(tempActionValueList, new ActionDoubleMapComparator());

		// Add sorted Action values to final List to be returned.
		for( Map<Action,Double> map : tempActionValueList ) {
			finalActionList.add(map.keySet().iterator().next());
		}

		return finalActionList;
	}

	/**
	 * Return the average value across all states for the given State,Value Map.
	 * 
	 * @param stateValues Map of all State,Value
	 * @return Double averaged value.
	 */
	private Double getAverage(Map<State,Value> stateValues) {
		Double total = 0.0;
		Long count = 0L;

		for( State state : stateValues.keySet() ) {
			Value currentValue = stateValues.get(state);
			total += currentValue.getMovingAverage();
			count++;
		}
		
		if ( count > 0 ) total = total / count;

		return total;
	}
}
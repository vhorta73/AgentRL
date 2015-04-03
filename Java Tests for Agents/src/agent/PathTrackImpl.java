package agent;

import java.util.LinkedList;
import java.util.List;

/**
 * PathTrack Implementation using AgentEventsImpl, to track events to goal
 * and then back-propagate them.
 * 
 * @author Vasco
 *
 */
public class PathTrackImpl extends AgentEventsImpl implements PathTrack {
	/**
	 * The track list from goal to beginning
	 */
	public List<StateActionStateValue> pathTrackList;
	
	/**
	 * The gamma value to defined the gradient of the back-propagation.
	 */
	public final Double GAMMA;

	/**
	 * Constructor, requesting a gamma value between 0 and 1, 
	 * to define the fade-out gradient for the back-propagation values.
	 * 
	 * @param gamma
	 */
	public PathTrackImpl(Double gamma) {
		pathTrackList = new LinkedList<StateActionStateValue>();
		GAMMA = gamma;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addEvent(State previousState, Action action, State currentState, Double reward) {
		// Add this sample to the list.
		StateActionStateValue sasv = new StateActionStateValueImpl(previousState, action, currentState, reward);
		pathTrackList.add(0, sasv);

		// If we have found a reward, do the back-propagation now.
		if ( reward > 0 ) backPropagate();
	}
	
	/**
	 * Add all elements of the pathTrackList into the AgentEvents memory with gradient decaying values,
	 * decaying by a factor of gamma^index.
	 */
	private void backPropagate() {
		// Get the first element to know what is the first starting value
		StateActionStateValue firstSasv = pathTrackList.get(0);
		Double goalValue = firstSasv.getReward();

		// Start from index 0.
		int index = 0;
		for( StateActionStateValue sasv : pathTrackList ) {
			Double value = Math.pow(GAMMA, index) * goalValue;
			super.addEvent(sasv.getPreviousState(), sasv.getAction(), sasv.getCurrentState(), value);
			index++;
			System.out.println(value);
		}
		
		// Reset the path list.
		pathTrackList = new LinkedList<StateActionStateValue>();
	}

}

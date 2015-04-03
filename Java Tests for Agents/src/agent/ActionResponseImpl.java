package agent;

import java.util.List;

public class ActionResponseImpl implements ActionResponse {
	/**
	 * The current State.
	 */
	private final State state;

	/**
	 * The reward given by the Environment at current State.
	 */
	private final Double reward;

	/**
	 * List of available actions at this State.
	 */
	private final List<Action> actions;

	/**
	 * Constructor.
	 * 
	 * @param state current State
	 * @param value current State value
	 */
	public ActionResponseImpl(State state, Double reward) {
		this.state = state;
		this.reward = reward;
		this.actions = state.getAvailableActions();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public State getState() {
		return state;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Action> getAvailableActions() {
		return actions;
	}

	@Override
	public Double getReward() {
		return reward;
	}
}

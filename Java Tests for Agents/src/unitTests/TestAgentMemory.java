package unitTests;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import agent.Action;
import agent.AgentEvents;
import agent.AgentEventsImpl;
import agent.State;
import agent.StateImpl;

/**
 * AgentMemory implementation tests.
 * 
 * @author Vasco
 *
 */
public class TestAgentMemory {
	/**
	 * AgentMemory object handler.
	 */
	private AgentEvents agentMemory;
	
	/**
	 * The current State
	 */
	private State currentState;

	/**
	 * The previous State
	 */
	private State previousState;
	
	/**
	 * The previous action applied.
	 */
	private final Action action = Action.DOWN;

	/**
	 * State actions available.
	 */
	private List<Action> availableActions;

	/**
	 * The Reward.
	 */
	private final Double REWARD = 12.0;
	
	/**
	 * Setting up initial values for next test.
	 */
	@Before
	public void before() {
		availableActions = new LinkedList<Action>();
		availableActions.add(action);
		previousState = new StateImpl(availableActions);
		currentState = new StateImpl(availableActions);
		agentMemory = new AgentEventsImpl();
	}

	@Test
	public void testBestActionReturned() {
		agentMemory.addEvent(previousState, action, currentState, REWARD);
		Action foundAction = agentMemory.getBestAction(previousState);
		assertEquals(action, foundAction);
	}
}
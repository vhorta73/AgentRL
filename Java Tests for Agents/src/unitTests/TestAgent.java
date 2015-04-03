package unitTests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import agent.Action;
import agent.ActionResponse;
import agent.ActionResponseImpl;
import agent.Agent;
import agent.AgentImpl;
import agent.State;
import agent.StateImpl;

/**
 * Agent Unit Tests.
 * 
 * @author Vasco
 *
 */
public class TestAgent {
	/**
	 * The Agent object handler.
	 */
	private Agent agent;
	
	/**
	 * The Environment default Action Response.
	 */
	private ActionResponse actionResponse;
	
	/**
	 * The current State.
	 */
	private State currentState;
	
	/**
	 * The current Environment reward.
	 */
	private Double reward = 123.0;

	/**
	 * List of the available actions at the current State.
	 */
	private List<Action> availableActions;
	
	/**
	 * Gamma factor.
	 */
	private final Double GAMMA = 0.8;

	/**
	 * Test initialisations.
	 */
	@Before
	public void before() {
		initActions();
		currentState = new StateImpl(availableActions);
		ActionResponse ar = new ActionResponseImpl(currentState, reward);
		actionResponse = new ActionResponseImpl(currentState, reward);
		agent = new AgentImpl(ar, GAMMA);
		agent.reset(actionResponse);
	}

	/**
	 * The agent should always have an action after an update.
	 */
	@Test
	public void testHasAction() {
		assertNotNull(agent.isReady());
		assertFalse(agent.isReady());
		agent.update(actionResponse);
		assertTrue(agent.isReady());
	}

	/**
	 * Test that the agent is returning an action from the
	 * actions available on the current state.
	 */
	@Test
	public void testReturnedAction() {
		Action foundAction = agent.getAction();
		assertNotNull(foundAction);
		verify(availableActions, foundAction);
	}

	/**
	 * Testing the update.
	 */
	@Test
	public void testUpdate() {
		// To test the update, update Agent with a one only list of available actions on a State.
		// When getting the next action, the action returned should be the one and only supplied.
		List<Action> oneAction = new LinkedList<Action>();
		oneAction.add(Action.DOWN);

		State testState = new StateImpl(oneAction);
		ActionResponse envResponse = new ActionResponseImpl(testState, reward);
		agent.update(envResponse);

		Action foundAction = agent.getAction();
		verify(oneAction, foundAction);
	}

	/**
	 * Setting up Actions.
	 */
	private void initActions() {
		availableActions = new LinkedList<Action>();
		availableActions.add(Action.DOWN);
		availableActions.add(Action.LEFT);
		availableActions.add(Action.LOWER_RIGHT);
		availableActions.add(Action.UPPER_RIGHT);
		availableActions.add(Action.RIGHT);
	}

	/**
	 * Check if the found action existing in the list of available actions.
	 * 
	 * @param actions list of available actions
	 * @param foundAction the returned action
	 */
	private void verify(List<Action> actions, Action foundAction) {
		// Must exist an action
		assertNotNull(foundAction);

		// Go over the action list in search of the found Action.
		boolean isActionFound = false;
		for( Action a : actions ) {
			if ( a.equals(foundAction) ) {
				isActionFound = true;
				break;
			}
		}

		// Check if the action was found.
		assertTrue(isActionFound);
	}
}

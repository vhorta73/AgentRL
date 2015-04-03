package unitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import agent.Action;
import agent.ActionResponse;
import agent.ActionResponseImpl;
import agent.State;
import agent.StateImpl;

/**
 * Testing ActionResponse implementation.
 * 
 * @author Vasco
 *
 */
public class TestActionResponse {
	/**
	 * The ActionResponse object handler.
	 */
	private ActionResponse actionResponse;

	/**
	 * The current state.
	 */
	private State state;

	/**
	 * The state value.
	 */
	private Double VALUE = 123.0;

	/**
	 * The actions available on this state.
	 */
	private List<Action> actions;

	/**
	 * Required initial setup before each test.
	 */
	@Before
	public void before() {
		initActions();
		initState();
		actionResponse = new ActionResponseImpl(state, VALUE);
	}

	/**
	 * Check if the initialised actions available list matches the expected.
	 */
	@Test
	public void testActionsAvailableAtState() {
		List<Action> foundActions = actionResponse.getAvailableActions();

		// Actions cannot be null
		assertNotNull(foundActions);

		// We expect to get some
		assertTrue(foundActions.size() > 0);

		// Check that the list matches
		verify(actions, foundActions);
	}

	/**
	 * Check that the returned state matches the expected.
	 */
	@Test
	public void testState() {
		State foundState = actionResponse.getState();
		verify(state, foundState);
	}

	/**
	 * Check that the value matches the expected.
	 */
	@Test
	public void testValue() {
		Double foundValue = actionResponse.getReward();
		verify(VALUE, foundValue);
	}

	/**
	 * The available actions on the current state.
	 */
	private void initActions() {
		actions = new LinkedList<Action>();
		actions.add(Action.DOWN);
		actions.add(Action.IDLE);
		actions.add(Action.LOWER_LEFT);
		actions.add(Action.UP);
		actions.add(Action.UPPER_RIGHT);
	}

	/**
	 * The current State initialisation.
	 */
	private void initState() {
		state = new StateImpl(actions);
	}

	/**
	 * Check if the expected list of actions matches the list found.
	 * 
	 * @param expected List of Actions
	 * @param found List of Actions
	 */
	private void verify(List<Action> expected, List<Action> found) {
		// Must not be null
		assertNotNull(found);

		// Must have same size
		assertEquals(expected.size(), found.size());

		// Check on each expected and see if it matches on the found list.
		for(Action expectedAction : expected ) {
			boolean isMatched = false;
			for(Action foundAction : found ) {
				if ( foundAction.equals(expectedAction) ) {
					isMatched = true;
					break;
				}
			}
			// For each of the expected actions, 
			// check if matched on the found list.
			assertTrue(isMatched);
		}
	}

	/**
	 * Check if the expected state matched the state found.
	 * 
	 * @param expected State
	 * @param found State
	 */
	private void verify(State expected, State found) {
		assertNotNull(found);
		assertEquals(expected, found);
	}

	/**
	 * Check if the expected value matches the value found.
	 * 
	 * @param expected Long value
	 * @param found Long value
	 */
	private void verify(Double expected, Double found) {
		assertEquals(expected, found);
	}
}

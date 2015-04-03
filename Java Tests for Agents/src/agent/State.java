package agent;

import java.util.List;

public interface State {
	/**
	 * The available actions that can be performed on this state.
	 * 
	 * @return List of actions available
	 */
	public List<Action> getAvailableActions();
	
	/**
	 * To add a sample to the moving averaged sample pool.
	 * 
	 * @param value Double.
	 */
    public void addSample(Double value);
    
    /**
     * Return the moving averaged value.
     * 
     * @return Double value.
     */
    public Double getMovingAverage();

}

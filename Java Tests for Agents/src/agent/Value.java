package agent;
/**
 * The Value of the State-Action-State.
 * 
 * @author Vasco
 *
 */
public interface Value {
	/**
	 * The calculated moving averaged value.
	 * 
	 * @return Double
	 */
	public Double getMovingAverage();
	
	/**
	 * Add a sample value to the calculation.
	 * 
	 * @param value
	 */
	public void addSample(Double value);
	
	/**
	 * Add a sample value to the calculation.
	 * 
	 * @param value
	 */
	public void addWeightedSample(Double value, Double weight);
	
	/**
	 * The amount of samples added to Value so far.
	 * 
	 * @return amount of samples
	 */
	public Integer getTotalSamples();
	
	/**
	 * Set the sample rate for the Moving average.
	 * 
	 * @param samples to be used on the moving average
	 */
	public void setSampleRate(Integer samples);
	
	/**
	 * Set the amount of samples to be moving averaged unlimited.
	 */
	public void unsetFixedSamples();

	/**
	 * Normalised moving average.
	 * 
	 * @return double normalised moving average
	 */
	public Double getMovingAverageNormalised();
	
	/**
	 * The maximum sample recorded so far.
	 * 
	 * @return Double maximum sample so far
	 */
	public Double getMax();
	
	/**
	 * The minimum sample recorded so far.
	 * 
	 * @return Double minimum sample so far
	 */
	public Double getMin();
}

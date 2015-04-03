package agent;


/**
 * The Value implementation.
 * 
 * @author Vasco
 *
 */
public class ValueImpl implements Value {
	/**
	 * The value.
	 */
	private Double value = 0.0;

	/**
	 * The times a value has been added.
	 */
	private Integer count = 0;
	
	/**
	 * Set the fixed samples flag on or off.
	 */
	private boolean fixedSamples = false;
	
	/**
	 * The number of samples to be used
	 */
	private Integer samples = 100;
	
	/**
	 * The maximum value found so far.
	 */
	private Double maxValue = 0.0;
	
	/**
	 * The minimum value found so far.
	 */
	private Double minValue = 0.0;

	/**
	 * {@inheritDoc
	 */
	@Override
	public Double getMovingAverage() {
		return value;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addSample(Double value) {
		if ( fixedSamples ) {
			this.value = ( this.value * (samples-1) + value ) / samples;
			count++;
		}
		else {
			this.value = ( this.value * count + value ) / ++count;
		}
		
		if ( maxValue < value ) maxValue = value;
		if ( minValue > value ) minValue = value;
	}

	/**
	 * {@inheritDoc
	 */
	@Override
	public Integer getTotalSamples() {
		return count;
	}

	/**
	 * {@inheritDoc
	 */
	@Override
	public void setSampleRate(Integer samples) {
		this.samples = samples;
		this.fixedSamples = true;
	}

	/**
	 * {@inheritDoc
	 */
	@Override
	public void unsetFixedSamples() {
		this.fixedSamples = false;
	}

	/**
	 * {@inheritDoc
	 */
	@Override
	public Double getMovingAverageNormalised() {
		return (value - minValue)/(maxValue - minValue);
	}

	/**
	 * {@inheritDoc
	 */
	@Override
	public Double getMax() {
		return maxValue;
	}

	/**
	 * {@inheritDoc
	 */
	@Override
	public Double getMin() {
		return minValue;
	}

	/**
	 * {@inheritDoc
	 */
	@Override
	public void addWeightedSample(Double value, Double weight) {
		Double weightedValue = value * weight;
		
		if ( fixedSamples ) {
			this.value = ( this.value * (samples-1) + weightedValue ) / samples;
			count++;
		}
		else {
			this.value = ( this.value * count + weightedValue ) / ++count;
		}
		
		if ( maxValue < value ) maxValue = weightedValue;
		if ( minValue > value ) minValue = weightedValue;
	}
}

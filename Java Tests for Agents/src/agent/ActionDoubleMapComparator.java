package agent;

import java.util.Comparator;
import java.util.Map;
/**
 * Map Action, Double comparator.
 * 
 * @author Vasco
 *
 */
public class ActionDoubleMapComparator implements Comparator<Map<Action,Double>> {
	/**
	 * Comparing between two Map entries, sorting by the Double values descending.
	 */
	@Override
	public int compare(Map<Action, Double> o1, Map<Action, Double> o2) {
		if( o1.values().iterator().next() > o2.values().iterator().next() ) return -1;
		if ( o1.values().iterator().next() < o2.values().iterator().next() ) return 1;
		return 0;
	}
}

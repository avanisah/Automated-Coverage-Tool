package CS6367_Phase1;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;

/* Pair class to store the test number - name - coverage mappings */
class Pair {
	int intKey;
	String stringValue;
	String stringKey;
	Set<Integer> set;

	public Pair(int intKey, String stringValue) {
		this.intKey = intKey;
		this.stringValue = stringValue;
	}

	public Pair(String stringKey, Set<Integer> set) {
		this.stringKey = stringKey;
		this.set = set;
	}

	public int getIntKey() {
		return this.intKey;
	}

	public String getStringKey() {
		return this.stringKey;
	}

	public String getStringValue() {
		return this.stringValue;
	}

	public Set<Integer> getSet() {
		return this.set;
	}

}

public class StatementCoverage {

	public static ArrayList<Pair> Test_Number_Name_Pair;
	public static TreeMap<Integer, Set<Pair>> Test_Number_Coverages;
	public static Set<Pair> testCase;

	public static void testStatement(String className, int statementNumber) {
		if (testCase == null)
			return;
		
		if(className == null)
			return;

		Set<Integer> set = null;
		for (Pair p : testCase) {
			if (p.getStringKey().equals(className)) {
				set = p.getSet();
				break;
			}
		}

		boolean flag = false;
		if (set == null) {
			set = new HashSet<Integer>();
			flag = true;
		}

		if (!set.contains(statementNumber))
			set.add(statementNumber);

		if (flag) {
			Pair pair = new Pair(className, set);
			testCase.add(pair);
		}

	}
}

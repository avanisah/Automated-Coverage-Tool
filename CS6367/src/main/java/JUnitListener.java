package CS6367_Phase1;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.junit.runner.Result;
import org.junit.runner.Description;
import org.junit.runner.notification.RunListener;

public class JUnitListener extends RunListener {

	static int testCaseNumber = 0;

	@Override
	public void testRunStarted(Description description) throws Exception {
		super.testRunStarted(description);
		StatementCoverage.Test_Number_Name_Pair = new ArrayList<Pair>();
		StatementCoverage.Test_Number_Coverages = new TreeMap<Integer, Set<Pair>>();
	}
	
	@Override
	public void testStarted(Description description) throws Exception {
		super.testStarted(description);

		StringBuilder des = new StringBuilder();
		des.append(description.getClassName());
		des.append(":");
		des.append(description.getMethodName());
		
		StatementCoverage.Test_Number_Name_Pair.add(new Pair(testCaseNumber++,des.toString()));
		StatementCoverage.testCase = new HashSet<Pair>();
	}

	@Override
	public void testFinished(Description description) throws Exception {
		StatementCoverage.Test_Number_Coverages.put(testCaseNumber, StatementCoverage.testCase);
		super.testFinished(description);

	}


	@Override
	public void testRunFinished(Result result) throws Exception {
		super.testRunFinished(result);
		try {
			String dir = "CS6367_Phase1";
			String logPath = dir + File.separator + "Phase1_Statement_Coverage.txt";
				
			File directory = new File(dir);
			if (!directory.exists()) {
				directory.mkdir();
			}
	
			File file = new File(logPath);
			if (!file.exists())
				file.createNewFile();
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
			StringBuilder sb = new StringBuilder();

			for (int testNumber : StatementCoverage.Test_Number_Coverages.keySet()) {

				StringBuilder t = new StringBuilder();
				for (Pair pair : StatementCoverage.Test_Number_Name_Pair) {
					if (pair.getIntKey() == testNumber) {
						t.append(pair.getStringValue());
						break;
					}
				}

				StringBuilder testName = new StringBuilder();
				testName.append("Test Case Number: ");
				testName.append(Integer.toString(testNumber));
				testName.append(" ");
				testName.append(t.toString());
				
				sb.append(testName.toString() + "\n");
				Set<Pair> testCase = StatementCoverage.Test_Number_Coverages.get(testNumber);

				for (Pair className : testCase) {
					String name = className.getStringKey();
					for (int val : className.getSet()) {
						sb.append("Class Execeuted: " + name + " & Line Number: " + val + "\n");
					}
				}
			}
			bw.write(sb.toString());
			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

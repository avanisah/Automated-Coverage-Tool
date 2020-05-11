# CS6367_SoftwareTesting

CS6367_Phase1 <br/>
Use ASM byte-code manipulation framework [1] to build an automated coverage collection tool
that can capture the statement coverage for the program under test. Then, apply your tool to
10 real-world Java projects (>1000 lines of code) with JUnit tests (>50 tests) from GitHub [2] to
collect the statement coverage for its JUnit tests. Note that your tool should (1) use Java Agent
[3] to perform on-the-fly code instrumentation, (2) be able to store the coverage for each test
method in the file system, and (3) be integrated with the Maven build system [4] so that your
tool can be triggered by simply typing “mvn test” after changing the pom.xml file of the project
under test. More implementation details are shown in the appendix. 
<br/><br/><br/><br/>
CS6367_Phase2 <br/>
Further augment the coverage collection tool implemented in Phase-1 to trace more
information about program internal states, e.g., tracing accessible field/variable values for the
beginning of each method execution. Then, infer the possible single-variable invariants based
on the Daikon technique.
o Reference:
§ https://plse.cs.washington.edu/daikon/
o Evaluate your tool to infer invariants for one real-world library project from
Phase-1, such as commons-utils or joda-time

NOTE:<br/>
We did not create git repository during the project lifeycle since we were initially sharing the data over email. But as per the professor's instruction we have created the repo on 1st May, 2020 and included all the software codes along with the report. 

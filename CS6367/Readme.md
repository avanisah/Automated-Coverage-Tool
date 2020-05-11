
# Statement Coverage Tool

CS 6367.001 - Software Testing, Validation and Verification - S20

Project Phase-1

Team Members:<br/>Ekansh Goyal (EXG180021)<br/>Avani Sah (AXS180221)<br/>Aarya Vardhan Reddy Paakaala (AXP170019)<br/>Vinyas Raju (VXR170005)

## Usage

Clone this repostory and in the `CS6367_Phase1` directory run

```
$ mvn clean 
$ mvn install
```

Copy the generated JavaAgent-1.0-SNAPSHOT.jar file from target to a project under test.

```
$ cp target/JavaAgent-1.0-SNAPSHOT.jar [destination]
```

Add these lines lines to the `pom.xml` file of the target project

```
<plugin>
   <groupId>org.apache.maven.plugins</groupId>
   <artifactId>maven-surefire-plugin</artifactId>
   <configuration>
      <argLine>-javaagent:./JavaAgent-1.0-SNAPSHOT.jar</argLine>
      <properties>
         <property>
            <name>listener</name>
            <value>CS6367_Phase1.JUnitListener</value>
         </property>
      </properties>
   </configuration>
</plugin>
```

In the same folder of the project under test run:

```
$ mvn test
```

The Statement Coverage log is generated and stored in `CS6367_Phase1` directory in the `Phase1_Statement_Coverage.txt` file. 

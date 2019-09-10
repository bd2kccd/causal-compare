# causal-compare
A command-line interface (CLI) for running algorithm comparison tool on simulated data.

## Building the software

### Prerequisites - You must have the following installed:
* [Java SE Development Kit 8]([https://www.oracle.com/technetwork/java/javase/downloads/index.html](https://www.oracle.com/technetwork/java/javase/downloads/index.html))
* [Apache Maven 3.x](https://maven.apache.org/download.cgi)

### Compiling the code:

 1. Download the source code.
 2. Extract the source code.
 3. Go to the parent folder **causal-compare**.
 4. Type the following command: ```mvn clean package```

The jar file, **causal-compare-x.x.x-jar-with-dependencies.jar**, is in the **target** directory.

### Running the program:

#### Command-line Options

```--config``` Indicate the location of  an XML configuration file.
```--out``` Indicate the location to write files to.
```--prefix``` Set the prefix name of output files.

#### XML Configuration File

File Structure
```xml
<comparisontool>
    <!--list of simulations-->
    <simulations>
        <simulation>...</simulation>
        <simulation>...</simulation>
    </simulations>
    
    <!--list of algorithms-->
    <algorithms>
        <algorithm>....</algorithm>
        <algorithm>...</algorithm>
    </algorithms>
    
    <!--list of statistics-->
    <statistics>
        <statistic>...</statistic>
        <statistic>...</statistic>
        <statistic>...</statistic>
    </statistics>
    
    <!--list of parameters-->
    <parameters>
        <parameter>...</parameter>
        <parameter>...</parameter>
        <parameter>...</parameter>
    </parameters>
    
    <!--list of comparison properties-->
    <comparison>
        <property>...</property>
        <property>...</property>
        <property>...</property>
    </comparison>
</comparisontool>
```
An example of a configuration file: 
```xml
<?xml version="1.0" encoding="UTF-8"?>
<comparisontool>
    <simulations>
        <simulation source="generate">
            <graphtype>RandomForward</graphtype>
            <modeltype>SemSimulation</modeltype>
        </simulation>
        <simulation source="directory">
            <path>src/test/resources/data/simulation</path>
        </simulation>
    </simulations>
    <algorithms>
        <algorithm name="gfci">
            <test>fisher-z-test</test>
            <score>sem-bic</score>
        </algorithm>
        <algorithm name="fges">
            <score>sem-bic</score>
        </algorithm>
    </algorithms>
    <statistics>
        <statistic>adjacencyPrecision</statistic>
        <statistic>arrowheadRecall</statistic>
        <statistic>adjacencyRecall</statistic>
    </statistics>
    <parameters>
        <parameter name="numRuns">1</parameter>
        <parameter name="numMeasures">4,6</parameter>
        <parameter name="avgDegree">4</parameter>
    </parameters>
    <comparison>
        <property name="showAlgorithmIndices">true</property>
        <property name="showSimulationIndices">true</property>
        <property name="sortByUtility">true</property>
        <property name="showUtilities">true</property>
        <property name="saveSearchGraphs">true</property>
        <property name="tabDelimitedTables">true</property>
    </comparison>
</comparisontool>
```
#### Execution
A simple command to run the program:
```java -jar causal-compare-x.x.x-jar-with-dependencies.jar --config comparison-tool.xml```
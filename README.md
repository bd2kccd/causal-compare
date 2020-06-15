# causal-compare
A command-line interface (CLI) for running algorithm comparison tool on simulated data.

## Running the program:

### Prerequisites - You must have the following installed:
* [Java SE Development Kit 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) **or** OpenJDK 11

Please follow the [Java installation guide](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html) for Java SE Development Kit 11.

### Execution

Make sure you are using Java 11.  You can check by typing the following: ```java -version```
1. If you are not building from source, you can download the jar file [here](https://cloud.ccd.pitt.edu/nexus/content/repositories/releases/edu/pitt/dbmi/causal-compare/0.2.0/causal-compare-0.2.0-jar-with-dependencies.jar).
2. Download the sample configuration file from [src/test/resources/data/sample_configuration.xml](src/test/resources/data/sample_configuration.xml) to the same directory as the jar file.
4. To run the program, open a terminal from the directory in which the jar file is located and type:
```java -jar causal-compare-x.x.x-jar-with-dependencies.jar --config sample_configuration.xml```

> Replace the x.x.x with the version number.  For an example, causal-compare-**0.2.0**-jar-with-dependencies.jar

#### Command-line Options
```
  --config
    indicate the location of  an XML configuration file
    
  --out
    indicate the location to write files to

  --prefix
    set the prefix name of output files
```

#### XML Configuration File
There are two ways of using the comparison tool to compare search algorithms.

The first way is to use Tetrad to generate simulated datasets and run search algorithms on those datasets.  You will need to specify the following in the XML configuration file:
* A list of data and algorithms
	* A list of simulation to generate data.
	* A list of search algorithms.
	* A list of algorithm parameters.
* A list of [comparison statistics](#comparison-statistics).
* A list of [comparison properties](#comparison-properties).

File structure for comparison running search algorithms on simulated data:
```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<comparison>
    <compareBy>
        <!--compare by search algorithms-->
        <search>

            <!--list of data simulations-->
            <simulations>
            
                <!--run simulation to generate data-->
                <simulation source="generate">
                    <graphtype>...</graphtype>
                    <modeltype>...</modeltype>
                </simulation>
            </simulations>

            <!--list of search algorithms-->
            <algorithms>
                <algorithm name="...">...</algorithm>
                <algorithm name="...">...</algorithm>
            </algorithms>

            <!--list of search algorithm parameters-->
            <parameters>
                <parameter name="...">...</parameter>
                <parameter name="...">...</parameter>
            </parameters>
        </search>
    </compareBy>

    <!--list of comparison statistics-->
    <statistics>
        <statistic>...</statistic>
        <statistic>...</statistic>
    </statistics>

    <!--list of comparison tool properties-->
    <properties>
        <property name="...">true</property>
        <property name="...">true</property>
    </properties>
</comparison>
```
 You can use Tetrad to generate simulated datasets and use them later in the comparison tool.  You just need to specify the path to where the simulate datasets are saved in the XML file:
 ```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<comparison>
    <compareBy>
        <!--compare by search algorithms-->
        <search>

            <!--list of data simulations-->
            <simulations>
                
                <!--path to where data simulation are saved-->
                <simulation source="directory">
                    <path>...</path>
                </simulation>
            </simulations>
        </search>
    </compareBy>
    
    ...
    
</comparison>
```
 The second way is to compare result graphs obtained from other search algorithms.    Note that **the true graphs and result graphs have to be in a Tetrad format**.  You would still use Tetrad to generate simulated datasets for other search algorithms to run on.   You will need to specify the following in the XML configuration file:
* A list of graphs
	* The path to the true graph.
	* The path to where Tetrad saves the simulated datasets.
	* A list of result graphs produced by other algorithms.
* A list of [comparison statistics](#comparison-statistics).
* A list of [comparison properties](#comparison-properties).

File structure for comparison using result graphs obtained from other search algorithms running on Tetrad simulated data:
```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<comparison>
    <compareBy>
        <!--compare by graphs obtained from search algorithm-->
        <graph>
            <!--path to true graph file-->
            <trueGraph>...</trueGraph>

            <!--path to where data simulation are saved-->
            <simulationPath>...</simulationPath>

            <!--list of result graphs-->
            <resultGraphs>
                <graph>
                    <!--description for the result graph-->
                    <description>...</description>

                    <!--the time it takes for the search algorithm to finish-->
                    <elapseTime>...</elapseTime>

                    <!--path to the result graph file-->
                    <graphFile>...</graphFile>
                </graph>
                <graph>
                    <description>...</description>
                    <elapseTime>...</elapseTime>
                    <graphFile>...</graphFile>
                </graph>
            </resultGraphs>
        </graph>
    </compareBy>

    <!--list of comparison statistics-->
    <statistics>
        <statistic>...</statistic>
        <statistic>...</statistic>
    </statistics>

    <!--list of comparison tool properties-->
    <properties>
        <property name="...">...</property>
        <property name="...">...</property>
    </properties>
</comparison>
```

## Building the software
If you prefer to compile the code, please follow the instruction below.

### Prerequisites - You must have the following installed:
* [Git](https://git-scm.com/downloads)
* [Java SE Development Kit 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) or OpenJDK 11
* [Apache Maven 3.x](https://maven.apache.org/download.cgi)

Please follow the [Git](https://git-scm.com/docs), [Java installation guide](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html) for Java SE Development Kit 11 and the [Maven installation guide](https://maven.apache.org/install.html).

### Compiling the code from command-line:

 Download the source code: 
 ```$ git clone https://github.com/bd2kccd/causal-compare.git```
 
Go to the project directory:
 ```$ cd causal-compare```
 
 Build the jar file from the source code:
 ```[causal-compare]$ mvn clean package```

The jar file, **causal-compare-x.x.x-jar-with-dependencies.jar**, is in the **target** directory.

## Configuration Options
### Comparison Statistics
|Property|Description                                           |
|--------|------------------------------------------------------|
|AR      |Adjacency Recall                                      |
|AHP     |Arrowhead precision                                   |
|AHR     |Arrowhead recall                                      |
|AHPC    |Arrowhead precision (common edges)                    |
|AHRC    |Arrowhead recall (common edges)                       |
|ATN     |Adjacency True Negatives                              |
|ATP     |Adjacency True Positives                              |
|ATPR    |Adjacency True Positive Rate                          |
|AFN     |Adjacency False Negatives                             |
|AFP     |Adjacency False Positives                             |
|AHTN    |Arrowhead True Negatives                              |
|AHTP    |Arrowhead True Positives                              |
|F1Adj   |F1 statistic for adjacencies                          |
|F1All   |F1 statistic for adjacencies and orientations combined|
|F1Arrow |F1 statistic for arrows                               |
|McAdj   |Matthew's correlation coefficient for adjacencies     |
|McArrow |Matthew's correlation coefficient for arrowheads      |
|SHD     |Structural Hamming Distance                           |
|NICP    |Node in cycle precision                               |
|NICR    |Node in cycle recall                                  |
|AMB     |Number of Ambiguous Triples                           |
|%AMB    |Percent Ambiguous Triples                             |
|BID     |Percent Bidirected Edges                              |
|EdgesEst|Number of Edges in the Estimated Graph                |
|EdgesT  |Number of Edges in the True Graph                     |
|TP      |Tail precision                                        |
|TR      |Tail recall                                           |
|2CP     |2-cycle precision                                     |
|2CR     |2-cycle recall                                        |
|E       |Elapsed Time                                          |

### Comparison Properties
|Property               |Description                                                                                                                         |
|-----------------------|------------------------------------------------------------------------------------------------------------------------------------|
|setShowSimulation      |True if simulation indices should be shown in the comparison table, false if not                                                    |
|setShowAlgorithmIndices|True if algorithm indices should be shown in the comparison table, false if not.                                                    |
|setShowUtilities       |True if utilities should be shown in the comparison table, false if not                                                             |
|setSortByUtility       |True if results should be sorted high to low by utility, false if not.                                                              |
|setSavePatterns        |True if patterns of DAGs should be saved out with the results.                                                                      |
|setSavePags            |True if PAGs (partial ancestral graphs) should be saved out with the results.                                                       |
|setTabDelimitedTables  |True if tables should be output in tab-delimited form, false if they should be printed in space-delimited form with aligned columns.|
|setComparisonGraph     |Sets the type of graph results are compared to. The options are: true DAG, pattern of the true DAG, PAG o the true DAG              |

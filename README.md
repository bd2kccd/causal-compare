# causal-compare
A command-line interface (CLI) for running algorithm comparison tool on simulated data.

## Running the program:

### Prerequisites - You must have the following installed:
* [Java SE Development Kit 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) **or** OpenJDK 11

Please follow the [Java installation guide](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html) for Java SE Development Kit 11.

### Execution

Make sure you are using Java 11.  You can check by typing the following: ```java -version```
1. If you are not building from source, you can download the distribution zip file [here](https://cloud.ccd.pitt.edu/nexus/content/repositories/releases/edu/pitt/dbmi/causal-compare/0.2.0/causal-compare-0.2.0-jar-with-dependencies.jar).
2. Download the sample configuration file from [src/test/resources/data/compare_search.xml](src/test/resources/data/compare_search.xml) to the same directory as the jar file.
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
File structure for comparison using result graphs obtained from search algorithms running on simulated data:
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
File structure for comparison running search algorithms on simulated data:
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

## Building the software
If you prefer to compile the code, please follow the instruction below.

### Prerequisites - You must have the following installed:
* [Java SE Development Kit 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) or OpenJDK 11
* [Apache Maven 3.x](https://maven.apache.org/download.cgi)

Please follow the [Java installation guide](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html) for Java SE Development Kit 11 and the [Maven installation guide](https://maven.apache.org/install.html).

### Compiling the code:

 1. Download the source code.
 2. Extract the source code.
 3. Go to the parent folder **causal-compare**.
 4. Type the following command: ```mvn clean package```

The jar file, **causal-compare-x.x.x-jar-with-dependencies.jar**, is in the **target** directory.

# causal-compare
A command-line interface (CLI) for running algorithm comparison tool on simulated data.

## Running the program:

### Prerequisites - You must have the following installed:
* [Java SE Development Kit 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) **or** OpenJDK 11

Please follow the [Java installation guide](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html) for Java SE Development Kit 11.

### Execution

Make sure you are using Java 11.  You can check by typing the following: ```java -version```
1. If you are not building from source, you can download the distribution zip file [here](https://cloud.ccd.pitt.edu/nexus/content/repositories/releases/edu/pitt/dbmi/causal-compare/0.1.3/causal-compare-0.1.3-distribution.zip) and extract the pre-compiled jar.
2. Download the sample configuration file from [src/test/resources/data/sample_configuration.xml](src/test/resources/data/sample_configuration.xml) to the same directory as the jar file.
4. To run the program, open a terminal from the directory in which the jar file is located and type:
```java -jar causal-compare-x.x.x-jar-with-dependencies.jar --config sample_configuration.xml```

> Replace the x.x.x with the version number.  For an example, causal-compare-**0.1.3**-jar-with-dependencies.jar

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

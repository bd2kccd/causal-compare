# causal-compare
A command-line interface (CLI) for running algorithm comparison tool on simulated data.

## Building the software

### Prerequisites - You must have the following installed:
* [Java SE Development Kit 8](https://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [Apache Maven 3.x](https://maven.apache.org/download.cgi)

Please follow the [Java installation guide](https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html) and the [Maven installation guide](https://maven.apache.org/install.html).

### Compiling the code:

 1. Download the source code.
 2. Extract the source code.
 3. Go to the parent folder **causal-compare**.
 4. Type the following command: ```mvn clean package```

The jar file, **causal-compare-x.x.x-jar-with-dependencies.jar**, is in the **target** directory.

## Running the program:

### Prerequisites - You must have the following installed:
* [Java SE Development Kit 8](https://www.oracle.com/technetwork/java/javase/downloads/index.html) **or** [Java SE Runtime Environment 8](https://www.java.com/en/download/)

Please follow the [Java installation guide](https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html).

### Execution

Make sure you are using Java 8.  You can check by typing the following: ```java -version```
1. If you are not building for source, you can download the pre-compiled jar [here](https://cloud.ccd.pitt.edu/nexus/content/repositories/releases/edu/pitt/dbmi/causal-compare/).
2. Download the sample configuration file from [src/test/resources/data/sample_configuration.xml](src/test/resources/data/sample_configuration.xml) to the same directory as the jar file.
4. To run the program, open a terminal from the directory in which the jar file is located and type:
```java -jar causal-compare-x.x.x-jar-with-dependencies.jar --config sample_configuration.xml```

** Replace the x.x.x with the version number.  For an example, causal-compare-**0.1.2**-jar-with-dependencies.jar

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
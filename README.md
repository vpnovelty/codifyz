# Coding Task

The goal of the challenge is to implement a system that analyses financial transaction records. Please refer the attached word document in this repository for detailed information

## Getting started

Checkout the Git Repository on `master`. As such the latest production copy will always be on `master`.

### Sample Input

```$xslt
Sing-2:target sing$ java -jar codifyz-1.0-SNAPSHOT-jar-with-dependencies.jar input.csv
Please enter the Account ID to calculate balance: 
Example: ACC334455
ACC334455
please enter the From Date to calculate the balance for the AccountID - ACC334455: 
Example: 20/10/2018 12:00:00
20/10/2018 12:00:00
please enter the To Date to calculate the balance From Date - 2018-10-20T12:00 for the AccountID - ACC334455: 
Example: 20/10/2018 19:00:00
20/10/2018 19:00:00
```

### Sample Output

```$xslt
Here is the requested details:
Relative balance for the period is: -$25.00 
The Number of Transactions included is: 1
```

##### Assumptions:
For the sake of simplicity, it is safe to assume that
•	Input file and records are all in a valid format
•	Transaction are recorded in order



### Prerequisites

There needs to be a `JDK 1.8` or higher version install to build and run this program locally. This program is build in Java using `JDK 11`.
_Note: No 11 specific features have been used_

The program uses `Apache Maven 3.6` to build. Please ensure you have a Maven installation of `3.6 +` on your local environment.

What things you need to install the software and how to install them

### Installing

The program is packaged as an executable `JAR` file.

Code needs to be compiled and the `JAR` file created by running the maven install command at the project root.

```
mvn clean install
```

The program produces a JAR file in the `target` directory and the local `maven repo`.

### Running

The JAR file is executable and the program can be run by executing it. Use the command:

```$xslt
java -jar codifyz-1.0-SNAPSHOT-jar-with-dependencies.jar <PATH OF CSV FILE>
```

### Running the tests

Tests with coverage reports can be obtained by running the below command on the project root

```
mvn clean test jacoco:report
```

Reports are produced using `JoCoCo` and are available under `/target/site`


### Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [JaCoCo](https://www.jacoco.org) - Code Coverage
* [TravisCI](https://travis-ci.org/) - Builds

### Contributing

This project is not open to active contribution as it is part of a coding exercise
<div align="center">

[![Generic badge](https://img.shields.io/badge/STATUS-FINISHED-success.svg)](https://shields.io/)

# Maven

Maven is a project management tool for Java. It solves three main problems: managing dependencies automatically without downloading JARs manually, standardizing project structure so every Maven project follows the same organization, and automating the build lifecycle from compilation to packaging and publishing.

## Technologies
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)

</div>

---

## 1. Project Structure

Every Maven project follows the same mandatory structure. Maven expects files in exactly these paths.

```
my-project/
├── pom.xml
└── src/
    ├── main/
    │   ├── java/
    │   │   └── com/example/
    │   │       └── Main.java
    │   └── resources/
    │       ├── application.properties
    │       └── logback.xml
    └── test/
        ├── java/
        │   └── com/example/
        │       └── MainTest.java
        └── resources/
            └── test.properties
```

`src/main/java` holds all production code. `src/main/resources` holds configuration files and resources that need to be on the classpath at runtime. `src/test/java` holds the tests. `src/test/resources` holds resources used only during tests. `pom.xml` sits at the project root.

After the first build Maven creates the `target` folder:

```
target/
├── classes/                  # compiled .class files from main
├── test-classes/             # compiled .class files from tests
├── my-project-1.0.0.jar      # generated JAR
├── surefire-reports/         # test reports
└── maven-archiver/           # packaging metadata
```

---

## 2. pom.xml

POM stands for Project Object Model. It is the central file that describes the project and everything it needs.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         http://maven.apache.org/xsd/maven-4.0.0.xsd">

    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example</groupId>
    <artifactId>my-project</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>

    <name>My Project</name>
    <description>Project description</description>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.33</version>
        </dependency>

        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>5.10.0</version>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.30</version>
            <scope>provided</scope>
        </dependency>

    </dependencies>

    <build>
        <plugins>

            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.11.0</version>
                <configuration>
                    <source>17</source>
                    <target>17</target>
                </configuration>
            </plugin>

            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.1.2</version>
            </plugin>

            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-jar-plugin</artifactId>
                <version>3.3.0</version>
                <configuration>
                    <archive>
                        <manifest>
                            <mainClass>com.example.Main</mainClass>
                        </manifest>
                    </archive>
                </configuration>
            </plugin>

        </plugins>
    </build>

</project>
```

---

## 3. Project Identity

`groupId` is the organization or group identifier, typically the reversed domain such as `com.google` or `org.apache`. `artifactId` is the project name. `version` is the current version. Together these three form the unique coordinates of the project in the Maven ecosystem, known as GAV (GroupId, ArtifactId, Version).

`packaging` defines the output format. `jar` is the default for Java applications. `war` is for traditional web applications. `pom` is used in parent projects that only organize other projects.

---

## 4. Dependencies and Scopes

Each dependency has a `scope` that defines in which phase of the lifecycle it is available.

`compile` is the default when not declared. The dependency is available at compile time, test time and runtime. `test` is available only during tests and is not included in the final JAR. `provided` is available at compile time but not included in the JAR because the execution environment already provides it, such as an application server. `runtime` is not needed for compilation but is needed for execution.

---

## 5. JAR

JAR stands for Java ARchive. It is a ZIP file with the `.jar` extension containing compiled `.class` files, resources and metadata. It is the standard distribution format for Java applications and libraries.

Inside every JAR there is a `META-INF` folder with the `MANIFEST.MF` file that describes the content:

```
Manifest-Version: 1.0
Main-Class: com.example.Main
Class-Path: libs/mysql-connector.jar
```

`Main-Class` defines which class has the `main` method to execute when the JAR is called with `java -jar`. Without it the JAR is a library, not an executable.

A standard JAR does not include its dependencies. To generate a JAR that bundles everything, called a fat JAR or uber JAR, the `maven-shade-plugin` or `maven-assembly-plugin` is used.

---

## 6. Build Lifecycle

Maven has three independent lifecycles. The main one is `default`, which goes from validation to deploy. `clean` removes generated files. `site` generates documentation.

The `default` lifecycle in full order:

```
validate          validates pom.xml is correct
initialize        initializes properties and creates directories
generate-sources  generates source code (Lombok, mappers, etc)
process-sources   processes generated code
compile           compiles src/main/java to target/classes
process-classes   processes .class files (instrumentation, etc)
generate-test-sources  generates test code
test-compile      compiles src/test/java to target/test-classes
test              runs tests with Surefire
prepare-package   prepares for packaging
package           generates JAR or WAR in target/
verify            runs quality and integration checks
install           copies JAR to ~/.m2/repository
deploy            sends JAR to remote repository
```

When a command is called, all previous phases execute in order. Calling `mvn package` runs validate, compile, test and package. Calling `mvn install` runs everything up to install.

---

## 7. Commands and Flags

Basic commands:

```bash
mvn validate          # validates the project
mvn compile           # compiles
mvn test              # compiles and runs tests
mvn package           # compiles, tests and generates JAR
mvn verify            # compiles, tests, verifies and packages
mvn install           # everything above + installs to local repository
mvn deploy            # everything above + sends to remote repository
mvn clean             # deletes the target/ folder
```

Most used combinations:

```bash
mvn clean compile              # cleans and recompiles
mvn clean test                 # cleans and runs tests
mvn clean package              # cleans and generates JAR
mvn clean install              # cleans, rebuilds and installs locally
mvn clean install -DskipTests  # rebuilds without running tests
```

Important flags:

```bash
-DskipTests              # skips test execution but still compiles them
-Dmaven.test.skip=true   # skips both compilation and execution of tests
-pl module-a             # executes only on a specific module
-am                      # also builds modules the target depends on
-rf :module-a            # resumes build from a specific module
-X                       # debug mode, shows detailed log
-q                       # quiet mode, shows only errors
-e                       # shows full stack trace on error
-T 4                     # uses 4 threads for parallel build
-o                       # offline mode, does not attempt to download
-U                       # forces snapshot update from repository
-P production-profile    # activates a specific profile
```

Running the generated JAR:

```bash
java -jar target/my-project-1.0.0.jar
```

---

## 8. Local Repository

Every downloaded dependency is stored in `~/.m2/repository` organized by the GAV structure:

```
~/.m2/repository/
└── mysql/
    └── mysql-connector-java/
        └── 8.0.33/
            ├── mysql-connector-java-8.0.33.jar
            ├── mysql-connector-java-8.0.33.pom
            └── mysql-connector-java-8.0.33.jar.sha1
```

The `.sha1` file is the integrity verification hash. Maven verifies the downloaded JAR was not corrupted by comparing the hash.

In companies it is common to have a private repository such as Nexus or Artifactory between the project and Maven Central, to control which dependencies are allowed and to host internal artifacts.

---

## 9. Profiles

Profiles allow different configurations for different environments in the same pom.xml:

```xml
<profiles>
    <profile>
        <id>development</id>
        <properties>
            <db.url>jdbc:mysql://localhost:3306/dev</db.url>
        </properties>
    </profile>
    <profile>
        <id>production</id>
        <properties>
            <db.url>jdbc:mysql://prod-server:3306/app</db.url>
        </properties>
    </profile>
</profiles>
```

Activated with `mvn package -P production`.

---

## 10. Multi-Module Projects

Large projects split code into modules. A parent pom.xml orchestrates the children:

```xml
<modules>
    <module>core-module</module>
    <module>web-module</module>
    <module>service-module</module>
</modules>
```

Each module has its own pom.xml and can depend on other modules. Running `mvn install` at the root builds all of them in dependency order.

---

## 11. Versioning

Maven follows the `MAJOR.MINOR.PATCH` convention with a special suffix. `1.0.0` is a stable release. `1.0.0-SNAPSHOT` is a version in development. SNAPSHOT means the artifact may change and Maven always fetches the latest version from the repository when it encounters one. Stable versions are immutable once published.

---
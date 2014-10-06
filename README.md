Model files of VRP-REP
======================

VRPREPModel is a maven project. It simply contains XML schema files, used to describe Vehicle Routing Problem instances files on the web application [VRP-REP](http://www.vrp-rep.org).

How do I use the model in my own project ?
------------------------------------------

If you intend to use the format in a java project, you can use JAXB generated classes to read and write XML files.

### Maven
The best way to go is to add the following to your pom.xml :

```xml
<dependency>
    <groupId>org.vrp-rep</groupId>
    <artifactId>vrprep-model</artifactId>
    <version>0.1.3</version>
</dependency>
```

### Manually include the JAR
Versioned JAR files are directly available from [Maven central](http://search.maven.org/#search%7Cga%7C1%7Cvrp-rep).

Reader and Writer
-----------------
As of 0.1.4, the project also include a basic instance reader and writer, that you may use this way :

```java
import java.io.File;
import org.vrprep.model.instance.Instance;
import org.vrprep.model.util.Instances;
...
Instance instance = Instances.read(inputPath);
File file = Instances.write(instance, outputPath);
```
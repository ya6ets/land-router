## 🚀 How to Build and Run

Follow these steps from your terminal to compile and launch the application:

### 1. Build the Project

Navigate to the root `land-router` folder (where the `pom.xml` lives) and run the Maven package command to resolve dependencies and build the executable JAR file:

```bash
mvn clean package

```

### 2. Run the Application

Once the build completes successfully, launch the embedded Tomcat server using the generated JAR file:

```bash
java -jar target/land-router-0.0.1-SNAPSHOT.jar

```

---

## 🧪 Testing the Application

Once the application has started up successfully (defaulting to port `8080`), you can test the routing endpoints using a web browser or `curl` via your terminal.

### Example Route Request (Czech Republic to Italy)

```bash
curl http://localhost:8080/routing/CZE/ITA

```

### Expected Output

The server will return the calculated country border route mapping your overland journey.

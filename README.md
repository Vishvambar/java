# 🎓 Advanced Java — Eclipse Project Setup & Exam Reference

> **Purpose:** A complete, step-by-step guide for setting up three types of Eclipse projects under exam conditions. No steps are skipped.

---

## Table of Contents

1. [Standard Java Project (JDBC & Swing)](#1-standard-java-project-jdbc--swing)
2. [Dynamic Web Project (Servlets & JSP)](#2-dynamic-web-project-servlets--jsp)
3. [Maven / Spring Boot Project (Hibernate & REST APIs)](#3-maven--spring-boot-project-hibernate--rest-apis)
4. [Theory for Practicals (A1–A10)](#4-theory-for-practicals-a1a10)

---

## 1. Standard Java Project (JDBC & Swing)

**Use for:** Assignments A1 (JDBC Select), A2 (Swing Login), A3 (Swing Interest Calculator).

### 1.1 Create the Project

1. Open Eclipse → **File → New → Java Project**.
2. In the **"New Java Project"** dialog:
   - **Project name:** e.g., `A1_JDBC_Select`
   - **JRE:** Select **Use an execution environment JRE** → pick `JavaSE-17` (or whatever version is installed). If only `JavaSE-1.8` is available, that works too.
   - **Project layout:** Keep default — *Create separate folders for sources and class files* (`src` and `bin`).
3. Click **Finish**. The project appears in the Package Explorer on the left.

### 1.2 Create a Class

1. Right-click the `src` folder → **New → Class**.
2. In the dialog:
   - **Name:** e.g., `A1_JDBC_Select`
   - ✅ Check **`public static void main(String[] args)`** to auto-generate the main method.
   - Leave **Package** blank (for exam simplicity — no package declaration needed).
3. Click **Finish**. The `.java` file opens in the editor.

### 1.3 Add the MySQL Connector JAR to the Build Path

> **Critical for JDBC programs.** Without this, `Class.forName("com.mysql.cj.jdbc.Driver")` throws `ClassNotFoundException`.

1. **Locate the JAR.** Common exam locations:
   - `C:\Program Files\MySQL\Connector J 8.0\mysql-connector-j-8.x.x.jar`
   - Or on the Desktop / a USB drive provided by the examiner.
   - On Linux: `/usr/share/java/mysql-connector-java.jar` or similar.
2. Right-click the **project name** in Package Explorer → **Build Path → Configure Build Path…**
3. In the dialog, select the **Libraries** tab.
4. Click **Classpath** (not Modulepath) to highlight it.
5. Click **Add External JARs…**
6. Navigate to the MySQL Connector `.jar` file, select it, click **Open**.
7. You should now see `mysql-connector-j-8.x.x.jar` listed under Classpath.
8. Click **Apply and Close**.
9. In Package Explorer, a **Referenced Libraries** node now appears containing the JAR.

### 1.4 Verify MySQL is Running

1. Open a terminal / command prompt.
2. Run: `mysql -u root -p` and enter the password.
3. Execute:
   ```sql
   CREATE DATABASE IF NOT EXISTS example_db;
   USE example_db;
   CREATE TABLE IF NOT EXISTS employees (
       id INT PRIMARY KEY,
       name VARCHAR(100),
       salary DOUBLE
   );
   INSERT INTO employees VALUES (101, 'Alice', 75000) ON DUPLICATE KEY UPDATE name=name;
   ```
4. Verify: `SELECT * FROM employees;` — you should see the row.

### 1.5 Run the Program

1. Right-click the `.java` file → **Run As → Java Application**.
2. Console output appears in the bottom panel.

---

## 2. Dynamic Web Project (Servlets & JSP)

**Use for:** Assignments A4 (Area Servlet), A5 (Factorial JSP).

### 2.1 Configure Apache Tomcat in Eclipse (One-Time Setup)

> Do this **before** creating any Dynamic Web Project.

1. **Download / Locate Tomcat.** Common exam location:
   - `C:\apache-tomcat-9.0.xx\` or `C:\apache-tomcat-10.1.xx\`
   - On Linux: `/opt/tomcat/` or `/usr/share/tomcat9/`
   - **Note:** Tomcat 9 uses `javax.servlet.*`; Tomcat 10+ uses `jakarta.servlet.*`. Adjust imports accordingly.
2. In Eclipse: **Window → Preferences → Server → Runtime Environments**.
3. Click **Add…**
4. Under **Apache**, select **Apache Tomcat v9.0** (or the version matching your installation).
5. Click **Next**.
6. **Tomcat installation directory:** Click **Browse…** and point to the Tomcat root folder (e.g., `C:\apache-tomcat-9.0.xx`).
7. **JRE:** Select the installed JRE/JDK.
8. Click **Finish**, then **Apply and Close**.

### 2.2 Create the Dynamic Web Project

1. **File → New → Dynamic Web Project**.
   - If not visible: **File → New → Other… → Web → Dynamic Web Project**.
2. In the dialog:
   - **Project name:** e.g., `A4_AreaServlet`
   - **Target runtime:** Select the Tomcat runtime you just configured.
   - **Dynamic web module version:** `4.0` (for Tomcat 9) or `5.0` (for Tomcat 10).
   - **Configuration:** Keep default.
3. Click **Next** twice until you reach the **Web Module** page.
   - ✅ Check **Generate web.xml deployment descriptor** (important for older-style servlet mapping, though `@WebServlet` annotation makes it optional).
4. Click **Finish**.

### 2.3 Understanding the Project Structure

```
A4_AreaServlet/
├── src/main/java/          ← Your Servlet .java files go HERE
│   └── AreaServlet.java
├── src/main/webapp/        ← Your HTML, JSP, CSS, JS files go HERE
│   ├── index.html
│   ├── WEB-INF/
│   │   └── web.xml         ← Deployment descriptor (auto-generated)
│   └── META-INF/
└── build/classes/          ← Compiled .class files (auto-managed)
```

> **KEY RULE:**
> - **HTML / JSP files** → `src/main/webapp/` (directly, NOT inside WEB-INF)
> - **Servlet Java classes** → `src/main/java/` (with or without a package)
> - Files inside `WEB-INF/` are **not** directly accessible by the browser.

> **Older Eclipse versions** may use this layout instead:
> ```
> WebContent/           ← HTML, JSP files go HERE
> ├── index.html
> ├── WEB-INF/
> │   └── web.xml
> Java Resources/
> └── src/              ← Servlet .java files go HERE
> ```
> Adapt accordingly based on your Eclipse version.

### 2.4 Create the HTML File

1. Right-click `src/main/webapp` (or `WebContent`) → **New → HTML File**.
2. Name it `index.html` → **Finish**.
3. Write the form with `action="AreaServlet"` and `method="post"`.

### 2.5 Create the Servlet

1. Right-click `src/main/java` (or `Java Resources/src`) → **New → Servlet**.
   - If not visible: **New → Other… → Web → Servlet**.
2. **Class name:** `AreaServlet`. Leave package blank for exam.
3. Click **Next** → you can edit URL mappings here. Default is `/AreaServlet`.
4. Click **Next** → check **doPost** (uncheck doGet if not needed).
5. Click **Finish**.
6. Replace the generated code with your logic.

### 2.6 Run on Server

1. Right-click the project → **Run As → Run on Server**.
2. Select the Tomcat server → **Finish**.
3. Eclipse's internal browser opens `http://localhost:8080/A4_AreaServlet/`.
   - If the internal browser doesn't work, copy the URL to Chrome/Firefox.
4. Navigate to `http://localhost:8080/A4_AreaServlet/index.html` to see your form.

### 2.7 Troubleshooting

| Problem | Fix |
|---|---|
| **404 Not Found** | Check that `index.html` is in `webapp/` (not `WEB-INF/`). Check the `@WebServlet` URL matches the form action. |
| **405 Method Not Allowed** | You implemented `doGet` but the form uses `POST` (or vice versa). |
| **ClassNotFoundException for Servlet** | Clean the project: **Project → Clean…** and rebuild. |
| **Port 8080 already in use** | Stop any running server in the **Servers** tab (bottom panel), then restart. |

---

## 3. Maven / Spring Boot Project (Hibernate & REST APIs)

**Use for:** Assignments A7 (Maven App), A8 (Spring Controller), A9 (Hibernate), A10 (REST Controller).

### 3.1 Create a Maven Project

1. **File → New → Maven Project**.
   - If not visible: **File → New → Other… → Maven → Maven Project**.
2. ✅ Check **Create a simple project (skip archetype selection)** for quick setup.
3. Click **Next**.
4. Fill in:
   - **Group Id:** `com.exam`
   - **Artifact Id:** `A7_MavenApp` (or whatever assignment)
   - **Version:** `1.0` (keep default)
   - **Packaging:** `jar` (for standalone) or `war` (for web deployment — use `jar` for Spring Boot).
5. Click **Finish**.

### 3.2 Understanding the Maven Project Structure

```
A7_MavenApp/
├── pom.xml                        ← Dependencies & build config
├── src/
│   ├── main/
│   │   ├── java/                  ← Your Java source files
│   │   │   └── com/exam/App.java
│   │   └── resources/             ← Config files (hibernate.cfg.xml, application.properties)
│   └── test/
│       └── java/                  ← Test classes (optional for exam)
└── target/                        ← Build output (auto-managed)
```

### 3.3 Edit the `pom.xml`

1. Double-click `pom.xml` in Package Explorer to open it.
2. Click the **pom.xml** tab at the bottom of the editor (not the Overview tab) to see raw XML.
3. Add your dependencies inside the `<dependencies>` block. Example:

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.exam</groupId>
    <artifactId>A7_MavenApp</artifactId>
    <version>1.0</version>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
    </properties>

    <dependencies>
        <!-- Add dependencies here -->
    </dependencies>
</project>
```

4. **Save** the file (`Ctrl+S`).

### 3.4 Force a Maven Update (Critical Step)

> After editing `pom.xml`, Eclipse won't auto-download dependencies. You must force it.

1. Right-click the project → **Maven → Update Project…**
2. In the dialog:
   - ✅ Your project should be checked.
   - ✅ Check **Force Update of Snapshots/Releases**.
3. Click **OK**.
4. Wait for the progress bar (bottom-right) to finish. Watch the **Console** and **Problems** tabs for errors.
5. The `Maven Dependencies` library node in Package Explorer should now list your downloaded JARs.

> **If Maven Update fails:**
> - Check internet connectivity.
> - Try **Project → Clean…** then re-run Maven Update.
> - Delete the `.m2/repository` cache folder and retry (last resort).

### 3.5 Create a Spring Boot Project (For A8, A10)

**Option A: Using Spring Initializr (if internet available)**

1. Go to [https://start.spring.io](https://start.spring.io) in a browser.
2. Configure:
   - **Project:** Maven
   - **Language:** Java
   - **Spring Boot:** `3.2.x` (latest stable)
   - **Group:** `com.exam`
   - **Artifact:** `A10_RestApp`
   - **Packaging:** Jar
   - **Java:** 17
3. **Add Dependencies:** `Spring Web`, `Spring Data JPA` (if needed), `Spring Boot DevTools`.
4. Click **Generate** → download and extract the `.zip`.
5. In Eclipse: **File → Import → Maven → Existing Maven Projects**.
6. Browse to the extracted folder → click **Finish**.
7. Run Maven Update (Section 3.4).

**Option B: Manual Spring Boot in Existing Maven Project**

1. Create a Maven project (Section 3.1).
2. Add the Spring Boot parent and dependencies to `pom.xml`:

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>3.2.5</version>
</parent>

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
</dependencies>
```

3. Force Maven Update (Section 3.4).
4. Create the main class with `@SpringBootApplication` and `SpringApplication.run(...)`.

### 3.6 Add `hibernate.cfg.xml` (For A9)

1. Right-click `src/main/resources` → **New → File** → name it `hibernate.cfg.xml`.
2. Paste the Hibernate configuration XML (provided in the assignment files).
3. Make sure the MySQL connector dependency is also in `pom.xml`.

### 3.7 Run a Maven/Spring Boot Project

- **Plain Maven main class:** Right-click the `.java` file with `main()` → **Run As → Java Application**.
- **Spring Boot app:** Right-click the `@SpringBootApplication` class → **Run As → Java Application** (Spring Boot's embedded Tomcat starts automatically).
  - Default URL: `http://localhost:8080`
  - Check the Console for `Started Application in X seconds`.

### 3.8 Common Maven Errors

| Problem | Fix |
|---|---|
| **Red X on project / "Build path specifies execution environment"** | Right-click project → Build Path → Configure → Libraries → change JRE to installed JDK. Then Maven Update. |
| **"Could not resolve dependencies"** | Check internet. Verify `<dependency>` spelling. Force Maven Update. |
| **"Source option 5 is no longer supported"** | Add `<maven.compiler.source>17</maven.compiler.source>` in `<properties>`. |
| **Port 8080 in use** | Stop any existing server, or add `server.port=9090` to `application.properties`. |

---

## Quick Reference: Which Setup for Which Assignment

| # | Assignment | Project Type | Key Setup Step |
|---|---|---|---|
| A1 | JDBC Select | Standard Java | Add MySQL JAR to Build Path |
| A2 | Swing Login | Standard Java | Add MySQL JAR to Build Path |
| A3 | Swing Interest | Standard Java | None extra |
| A4 | Area Servlet | Dynamic Web | Configure Tomcat + Run on Server |
| A5 | Factorial JSP | Dynamic Web | Configure Tomcat + Run on Server |
| A6 | MVC Bookstore | Standard Java | None extra |
| A7 | Maven App | Maven | Add Commons Lang dependency in POM |
| A8 | Spring Controller | Maven + Spring Boot | Spring Web dependency |
| A9 | Hibernate | Maven | Hibernate + MySQL dependencies in POM |
| A10 | REST Controller | Maven + Spring Boot | Spring Web dependency |

---

## 4. Theory for Practicals (A1–A10)

---

### A1 — JDBC Select (`A1_JDBC_Select.java`)

**Topic: Java Database Connectivity (JDBC)**

**What is JDBC?**
JDBC is a standard Java API (in `java.sql` and `javax.sql` packages) that allows Java programs to interact with relational databases. It provides a uniform interface regardless of the underlying database (MySQL, Oracle, PostgreSQL, etc.).

**JDBC Architecture:**

```
Java Application
      ↓
  JDBC API (java.sql.*)
      ↓
  JDBC Driver Manager
      ↓
  JDBC Driver (e.g., mysql-connector-j)
      ↓
  Database (MySQL)
```

**Key Interfaces & Classes:**

| Interface/Class | Purpose |
|---|---|
| `DriverManager` | Manages a list of database drivers. `getConnection()` establishes the connection. |
| `Connection` | Represents an active connection to the database. Used to create statements. |
| `Statement` | Executes static SQL queries. Vulnerable to SQL injection. |
| `PreparedStatement` | Pre-compiled SQL with `?` placeholders. **Preferred** — prevents SQL injection and is faster for repeated queries. |
| `ResultSet` | Holds the result of a `SELECT` query. Cursor-based — use `next()` to iterate rows. |
| `SQLException` | Checked exception thrown for all database errors. |

**JDBC Steps (The 5-Step Process):**

1. **Load the driver** — `Class.forName("com.mysql.cj.jdbc.Driver")` (optional in modern JDBC 4.0+, auto-loaded).
2. **Establish connection** — `DriverManager.getConnection(url, user, pass)`
3. **Create statement** — `conn.prepareStatement(sql)`
4. **Execute query** — `pstmt.executeQuery()` for SELECT, `pstmt.executeUpdate()` for INSERT/UPDATE/DELETE.
5. **Close resources** — Use **try-with-resources** (Java 7+) to auto-close `Connection`, `PreparedStatement`, and `ResultSet`.

**PreparedStatement vs Statement:**

| Feature | Statement | PreparedStatement |
|---|---|---|
| SQL Injection | Vulnerable | Safe (parameterized) |
| Performance | Compiled each time | Pre-compiled, cached |
| Readability | String concatenation | Clean `?` placeholders |

**JDBC URL Format:**
```
jdbc:mysql://hostname:port/database_name
jdbc:mysql://localhost:3306/example_db
```

**Try-With-Resources:**
Introduced in Java 7. Any class implementing `AutoCloseable` is automatically closed when the `try` block exits — even if an exception occurs. Eliminates the need for `finally` blocks to close resources.

---

### A2 — Swing Login (`A2_SwingLogin.java`)

**Topic: Java Swing + JDBC Integration**

**What is Swing?**
Swing is a GUI widget toolkit in `javax.swing` package. It is part of Java Foundation Classes (JFC) and provides platform-independent, lightweight components built entirely in Java (unlike AWT which uses native OS widgets).

**Swing vs AWT:**

| Feature | AWT | Swing |
|---|---|---|
| Package | `java.awt` | `javax.swing` |
| Components | Heavyweight (OS-native) | Lightweight (Java-rendered) |
| Look & Feel | OS-dependent | Pluggable (consistent cross-platform) |
| Components | `Button`, `TextField` | `JButton`, `JTextField` (prefixed with J) |

**Key Swing Components Used:**

| Component | Purpose |
|---|---|
| `JFrame` | Top-level window container |
| `JTextField` | Single-line text input |
| `JPasswordField` | Masked password input (shows dots) |
| `JButton` | Clickable button |
| `JLabel` | Display-only text |
| `JOptionPane` | Pre-built dialog boxes (info, warning, error, input) |

**Event Handling — Delegation Model:**
Swing uses the **Observer/Listener pattern**:
1. **Event Source** — the component that generates the event (e.g., `JButton`).
2. **Event Object** — encapsulates event info (e.g., `ActionEvent`).
3. **Event Listener** — interface that handles the event (e.g., `ActionListener`).

```
User clicks button → JButton creates ActionEvent → calls actionPerformed() on registered ActionListener
```

**ActionListener Interface:**
```java
public interface ActionListener {
    void actionPerformed(ActionEvent e);
}
```
Register with: `button.addActionListener(this)` (when class implements `ActionListener`).

**Layout Managers:**
- `FlowLayout` — left-to-right, row by row (default for `JPanel`)
- `BorderLayout` — 5 regions: NORTH, SOUTH, EAST, WEST, CENTER (default for `JFrame`)
- `GridLayout` — equal-sized grid cells
- `GridBagLayout` — most flexible, cell-based with constraints (`GridBagConstraints`)

**SwingUtilities.invokeLater():**
Swing is **not thread-safe**. All GUI operations must run on the **Event Dispatch Thread (EDT)**. `invokeLater()` schedules the `Runnable` on the EDT.

---

### A3 — Swing Interest Calculator (`A3_SwingInterest.java`)

**Topic: Swing Event Handling + Exception Handling**

**Simple Interest Formula:**
```
SI = (P × R × T) / 100
```
Where: P = Principal, R = Rate of interest (%), T = Time in years.

**NumberFormatException:**
A runtime (unchecked) exception thrown by `Integer.parseInt()`, `Double.parseDouble()`, etc. when the input string is not a valid number.

```java
Double.parseDouble("abc");   // throws NumberFormatException
Double.parseDouble("");      // throws NumberFormatException
Double.parseDouble("12.5");  // returns 12.5 ✓
```

**JOptionPane Dialog Types:**

| Method | Icon | Use Case |
|---|---|---|
| `showMessageDialog(parent, msg, title, INFORMATION_MESSAGE)` | ℹ️ | Success messages |
| `showMessageDialog(parent, msg, title, WARNING_MESSAGE)` | ⚠️ | Validation warnings |
| `showMessageDialog(parent, msg, title, ERROR_MESSAGE)` | ❌ | Error alerts |
| `showInputDialog(parent, msg)` | ❓ | Prompt for input |
| `showConfirmDialog(parent, msg)` | ❓ | Yes/No/Cancel |

**Best Practice:**
Always validate user input inside `actionPerformed()` with a try-catch for `NumberFormatException` before performing calculations. Display errors using `JOptionPane` rather than printing to console.

---

### A4 — Area Servlet (`A4_AreaServlet.java` + `index.html`)

**Topic: Java Servlets**

**What is a Servlet?**
A Servlet is a Java class that runs on a **web server** (like Apache Tomcat) and handles HTTP requests and responses. It extends `HttpServlet` and lives on the **server side**.

**Servlet Lifecycle (managed by the Servlet Container — Tomcat):**

```
1. Loading     → Container loads the Servlet class
2. init()      → Called ONCE when the Servlet is first created (initialization)
3. service()   → Called for EVERY request; dispatches to doGet()/doPost()
4. destroy()   → Called ONCE when the server shuts down (cleanup)
```

| Method | HTTP Method | When Used |
|---|---|---|
| `doGet()` | GET | Fetching data, URL-based requests, bookmarkable |
| `doPost()` | POST | Submitting forms, sending data (body is hidden from URL) |

**GET vs POST:**

| Feature | GET | POST |
|---|---|---|
| Data in | URL query string (`?key=val`) | Request body (hidden) |
| Data limit | ~2KB (URL length limit) | No practical limit |
| Security | Visible in URL, browser history | Not visible in URL |
| Idempotent | Yes (safe to repeat) | No |
| Use case | Search, navigation | Form submission, login |

**@WebServlet Annotation:**
Replaces the older `web.xml` mapping. Maps a URL pattern to the Servlet class:
```java
@WebServlet("/AreaServlet")  // accessible at http://localhost:8080/project/AreaServlet
```

**HttpServletRequest & HttpServletResponse:**
- `request.getParameter("radius")` — reads form field value (always returns `String`).
- `response.setContentType("text/html")` — tells browser to render as HTML.
- `response.getWriter()` — returns a `PrintWriter` to write the HTML response.

**HTML Form → Servlet Flow:**
```
Browser → index.html (form action="AreaServlet" method="post")
       → HTTP POST to /AreaServlet
       → Tomcat invokes doPost() on AreaServlet
       → Servlet writes HTML response
       → Browser displays result
```

---

### A5 — Factorial JSP (`A5_factorial.jsp` + `index.html`)

**Topic: JavaServer Pages (JSP)**

**What is JSP?**
JSP is a server-side technology that allows embedding Java code inside HTML pages. Behind the scenes, the **JSP container converts every JSP into a Servlet** before execution.

**JSP Lifecycle:**
```
1. Translation  → JSP → Servlet .java file (first request only)
2. Compilation   → .java → .class file
3. Loading       → Class loaded into memory
4. Execution     → _jspService() handles each request
5. Destruction   → jspDestroy() on server shutdown
```

**JSP Scripting Elements:**

| Element | Syntax | Purpose | Example |
|---|---|---|---|
| **Scriptlet** | `<% ... %>` | Java code block (logic) | `<% int x = 5; %>` |
| **Expression** | `<%= ... %>` | Outputs a value (auto `out.print`) | `<%= x + 1 %>` |
| **Declaration** | `<%! ... %>` | Declares class-level variables/methods | `<%! int count = 0; %>` |
| **Directive** | `<%@ ... %>` | Page-level configuration | `<%@ page contentType="text/html" %>` |
| **Comment** | `<%-- ... --%>` | Server-side comment (not sent to browser) | `<%-- hidden --%>` |

**JSP Implicit Objects (available without declaration):**

| Object | Type | Purpose |
|---|---|---|
| `request` | `HttpServletRequest` | Access form parameters |
| `response` | `HttpServletResponse` | Set response headers |
| `out` | `JspWriter` | Write output to response |
| `session` | `HttpSession` | Store session-scoped data |
| `application` | `ServletContext` | Application-wide data |

**Servlet vs JSP:**

| Feature | Servlet | JSP |
|---|---|---|
| Code style | Java class with HTML in `out.println()` | HTML file with Java in `<% %>` |
| Best for | Business logic, controllers | Presentation, view layer |
| Compilation | Compiled before deployment | Compiled on first request |
| MVC Role | Controller | View |

---

### A6 — MVC Bookstore (`A6_Book.java`, `A6_BookView.java`, `A6_BookController.java`)

**Topic: Model-View-Controller (MVC) Design Pattern**

**What is MVC?**
MVC is a **software architectural pattern** that separates an application into three interconnected components to achieve separation of concerns:

```
        User Input
            ↓
      ┌─────────────┐
      │  CONTROLLER  │  ← Handles input, updates Model, selects View
      └──────┬───────┘
             │ updates          queries
        ┌────▼────┐        ┌────────────┐
        │  MODEL   │◄──────│    VIEW     │
        │ (Data)   │──────►│ (Display)  │
        └─────────┘        └────────────┘
```

| Component | Responsibility | Example in A6 |
|---|---|---|
| **Model** | Data + business logic. No knowledge of UI. | `Book.java` — POJO with fields, getters, setters |
| **View** | Presentation layer. Displays data to user. | `BookView.java` — console `println` methods |
| **Controller** | Mediator. Receives user actions, manipulates Model, updates View. | `BookController.java` — `addBook()`, `displayAll()` |

**Why MVC?**
1. **Separation of Concerns** — each component has one job.
2. **Reusability** — the same Model can be used with a Swing View, a Web View, or a REST API.
3. **Testability** — Model can be unit-tested independently of the UI.
4. **Maintainability** — changing the View doesn't require changing the Model or Controller.

**MVC in Web Frameworks:**
- **Spring MVC:** Controller = `@Controller` class, View = Thymeleaf/JSP, Model = `@Entity` / POJO.
- **Servlets + JSP:** Controller = Servlet, View = JSP, Model = JavaBean.

---

### A7 — Maven App (`A7_MavenApp.java`)

**Topic: Apache Maven — Build & Dependency Management**

**What is Maven?**
Maven is a **build automation and dependency management tool** for Java projects. It uses a `pom.xml` (Project Object Model) file to define project configuration, dependencies, and build instructions.

**Why Maven?**
- **No manual JAR management** — declare dependencies in `pom.xml`, Maven downloads them from the Central Repository.
- **Standardized project structure** — every Maven project follows the same directory layout.
- **Build lifecycle** — compile, test, package, deploy with a single command.

**Maven Project Structure:**
```
project/
├── pom.xml                    ← Project config + dependencies
├── src/
│   ├── main/
│   │   ├── java/              ← Source code
│   │   └── resources/         ← Config files (hibernate.cfg.xml, etc.)
│   └── test/
│       └── java/              ← Test classes
└── target/                    ← Build output (JAR/WAR)
```

**pom.xml Key Elements:**

```xml
<groupId>com.exam</groupId>       <!-- Organization/package -->
<artifactId>A7_MavenApp</artifactId> <!-- Project name -->
<version>1.0</version>            <!-- Version -->
<packaging>jar</packaging>         <!-- Output type: jar or war -->

<dependencies>
    <dependency>
        <groupId>org.apache.commons</groupId>
        <artifactId>commons-lang3</artifactId>
        <version>3.14.0</version>
    </dependency>
</dependencies>
```

**Maven Build Lifecycle Phases:**

| Phase | Action |
|---|---|
| `validate` | Check project is correct |
| `compile` | Compile source code |
| `test` | Run unit tests |
| `package` | Package into JAR/WAR |
| `install` | Install to local `.m2` repository |
| `deploy` | Upload to remote repository |

**Maven Repository Hierarchy:**
```
1. Local Repository  (~/.m2/repository)  ← checked first
2. Central Repository (repo.maven.apache.org) ← default remote
3. Remote/Private Repositories ← if configured
```

**Key Maven Commands (via Eclipse):**
- **Maven → Update Project** — re-downloads dependencies, syncs `pom.xml` with Eclipse.
- **Force Update** — re-downloads even if cached (fixes corrupted downloads).

---

### A8 — Spring Task Controller (`A8_SpringTaskController.java`)

**Topic: Spring Framework & Spring Boot**

**What is Spring?**
Spring is a comprehensive Java framework built around two core principles:
1. **Inversion of Control (IoC)** — the framework creates and manages objects (beans), not the programmer.
2. **Dependency Injection (DI)** — dependencies are automatically "injected" into classes (via constructor, setter, or field).

**What is Spring Boot?**
Spring Boot is a layer on top of Spring that eliminates boilerplate configuration:
- **Auto-configuration** — detects dependencies and configures them automatically.
- **Embedded server** — includes Tomcat; no need to deploy WAR files.
- **Starter dependencies** — `spring-boot-starter-web` pulls in everything needed for web apps.

**Spring MVC Request Flow:**

```
Browser → HTTP Request
       → DispatcherServlet (Front Controller)
       → @Controller method (matched by @GetMapping/@PostMapping)
       → Returns view name (e.g., "tasks")
       → ViewResolver → finds templates/tasks.html
       → Rendered HTML → Browser
```

**Key Annotations:**

| Annotation | Purpose |
|---|---|
| `@SpringBootApplication` | Marks the main class. Combines `@Configuration` + `@EnableAutoConfiguration` + `@ComponentScan`. |
| `@Controller` | Marks a class as an MVC controller (returns **view names**). |
| `@RestController` | Like `@Controller` + `@ResponseBody` (returns **data/JSON** directly). |
| `@GetMapping("/path")` | Maps HTTP GET requests to a method. |
| `@PostMapping("/path")` | Maps HTTP POST requests to a method. |
| `@RequestParam` | Binds a query/form parameter to a method argument. |
| `@PathVariable` | Binds a URL path segment (e.g., `/tasks/{id}`) to an argument. |
| `@Autowired` | Injects a dependency (implicit in constructor injection since Spring 4.3). |

**Spring Data JPA:**
- `JpaRepository<Entity, IDType>` provides CRUD methods for free: `findAll()`, `findById()`, `save()`, `deleteById()`.
- No implementation needed — Spring generates it at runtime.

**`@Entity` (JPA):**
Maps a Java class to a database table. Fields become columns. `@Id` marks the primary key, `@GeneratedValue` auto-generates IDs.

---

### A9 — Hibernate Main (`A9_HibernateMain.java`)

**Topic: Hibernate ORM (Object-Relational Mapping)**

**What is Hibernate?**
Hibernate is a **Java ORM framework** that maps Java objects to database tables. Instead of writing SQL manually, you work with Java objects and Hibernate generates the SQL.

**ORM Concept:**
```
Java Class   ←→  Database Table
Object       ←→  Row
Field        ←→  Column
```

**Hibernate Architecture:**

```
Java Application
      ↓
  Session API (persist, get, createQuery)
      ↓
  SessionFactory (thread-safe, one per DB, expensive to create)
      ↓
  JDBC (Hibernate generates SQL internally)
      ↓
  Database
```

**Key Hibernate Interfaces:**

| Interface | Purpose |
|---|---|
| `Configuration` | Reads `hibernate.cfg.xml`, builds `SessionFactory` |
| `SessionFactory` | Thread-safe factory for `Session` objects. Create **once** per application. |
| `Session` | Single-threaded unit of work. Used to perform CRUD. **Not thread-safe** — open, use, close. |
| `Transaction` | Wraps database operations in an atomic unit. Call `commit()` or `rollback()`. |

**Hibernate CRUD Operations:**

| Operation | Method | SQL Equivalent |
|---|---|---|
| Create | `session.persist(obj)` or `session.save(obj)` | `INSERT INTO ...` |
| Read | `session.get(Class, id)` | `SELECT ... WHERE id = ?` |
| Update | `session.merge(obj)` | `UPDATE ... SET ...` |
| Delete | `session.remove(obj)` | `DELETE FROM ... WHERE id = ?` |

**JPA Annotations for Entity Mapping:**

| Annotation | Purpose |
|---|---|
| `@Entity` | Marks class as a persistent entity |
| `@Table(name = "students")` | Maps to specific table name |
| `@Id` | Marks the primary key field |
| `@GeneratedValue(strategy = IDENTITY)` | Auto-increment primary key |
| `@Column(name = "col", nullable = false)` | Maps field to specific column with constraints |

**hibernate.cfg.xml Key Properties:**

| Property | Purpose |
|---|---|
| `connection.driver_class` | JDBC driver class |
| `connection.url` | Database URL |
| `connection.username/password` | Credentials |
| `dialect` | SQL dialect for the target DB (e.g., `MySQLDialect`) |
| `hbm2ddl.auto` | Schema management: `update` (auto-create/alter tables), `create` (drop+create), `validate`, `none` |
| `show_sql` | Print generated SQL to console |

**Hibernate vs JDBC:**

| Feature | JDBC | Hibernate |
|---|---|---|
| SQL | Write manually | Auto-generated |
| Mapping | Manual `ResultSet` → Object | Automatic via annotations |
| Caching | None | L1 (Session) + L2 (SessionFactory) |
| Relationships | Manual JOINs | `@OneToMany`, `@ManyToOne`, etc. |
| Boilerplate | High | Low |

---

### A10 — Employee REST Controller (`A10_EmployeeRestController.java`)

**Topic: RESTful Web Services with Spring Boot**

**What is REST?**
REST (Representational State Transfer) is an **architectural style** for designing networked applications. It uses standard HTTP methods to perform CRUD operations on resources identified by URLs.

**REST Principles:**
1. **Client-Server** — separation of concerns between frontend and backend.
2. **Stateless** — each request contains all information needed; server stores no client state.
3. **Uniform Interface** — resources identified by URIs, manipulated through HTTP methods.
4. **Resource-Based** — everything is a "resource" (e.g., `/api/employees`, `/api/employees/1`).

**HTTP Methods → CRUD Mapping:**

| HTTP Method | CRUD | Example URL | Purpose |
|---|---|---|---|
| `GET` | Read | `/api/employees` | Retrieve all employees |
| `GET` | Read | `/api/employees/1` | Retrieve employee with ID 1 |
| `POST` | Create | `/api/employees` | Add a new employee (data in body) |
| `PUT` | Update | `/api/employees/1` | Update entire employee record |
| `DELETE` | Delete | `/api/employees/1` | Remove employee with ID 1 |

**HTTP Status Codes:**

| Code | Meaning | When Used |
|---|---|---|
| `200 OK` | Success | GET, PUT |
| `201 Created` | Resource created | POST |
| `204 No Content` | Success, no body | DELETE |
| `400 Bad Request` | Invalid input | Validation error |
| `404 Not Found` | Resource doesn't exist | Wrong ID |
| `500 Internal Server Error` | Server-side failure | Unhandled exception |

**Spring Boot REST Annotations:**

| Annotation | Purpose |
|---|---|
| `@RestController` | Marks class as REST controller (returns JSON/XML, not views) |
| `@RequestMapping("/api/employees")` | Base URL path for the controller |
| `@GetMapping` / `@GetMapping("/{id}")` | Handles GET requests |
| `@PostMapping` | Handles POST requests |
| `@PutMapping("/{id}")` | Handles PUT requests |
| `@DeleteMapping("/{id}")` | Handles DELETE requests |
| `@RequestBody` | Deserializes JSON request body into a Java object |
| `@PathVariable` | Extracts value from URL (e.g., `{id}`) |

**JSON Serialization:**
Spring Boot uses **Jackson** library (auto-included) to convert Java objects ↔ JSON:
```
Java Object (Employee) → {"id":1,"name":"Ravi","department":"IT","salary":75000}
```
All public getters are serialized automatically. No extra configuration needed.

**@RestController vs @Controller:**

| Feature | @Controller | @RestController |
|---|---|---|
| Returns | View name (HTML page) | Data (JSON/XML) |
| Needs | ViewResolver + template engine | Jackson (auto-included) |
| Use case | Server-rendered web pages | REST APIs for mobile/SPA clients |

**Testing REST APIs:**
- **Browser** — only for GET requests (type URL in address bar).
- **Postman / Insomnia** — full-featured GUI tool for all HTTP methods.
- **curl** (command line):
  ```bash
  curl http://localhost:8080/api/employees                          # GET all
  curl -X POST -H "Content-Type: application/json" \
       -d '{"name":"Sneha","department":"HR","salary":50000}' \
       http://localhost:8080/api/employees                          # POST new
  ```

---

*Good luck on your exam. Read the error messages — they always tell you what's wrong.* 🚀

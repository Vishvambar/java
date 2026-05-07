# 🎓 Advanced Java — Eclipse Project Setup & Exam Reference

> **Purpose:** A complete, step-by-step guide for setting up three types of Eclipse projects under exam conditions. No steps are skipped.

---

## Table of Contents

1. [Standard Java Project (JDBC & Swing)](#1-standard-java-project-jdbc--swing)
2. [Dynamic Web Project (Servlets & JSP)](#2-dynamic-web-project-servlets--jsp)
3. [Maven / Spring Boot Project (Hibernate & REST APIs)](#3-maven--spring-boot-project-hibernate--rest-apis)

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

*Good luck on your exam. Read the error messages — they always tell you what's wrong.* 🚀
# java

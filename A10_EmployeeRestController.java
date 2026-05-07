/*
 * Spring Boot REST — pom.xml setup:
 *
 * <parent>
 *     <groupId>org.springframework.boot</groupId>
 *     <artifactId>spring-boot-starter-parent</artifactId>
 *     <version>3.2.5</version>
 * </parent>
 *
 * <dependencies>
 *     <dependency>
 *         <groupId>org.springframework.boot</groupId>
 *         <artifactId>spring-boot-starter-web</artifactId>
 *     </dependency>
 * </dependencies>
 *
 * Test with:
 *   GET  http://localhost:8080/api/employees
 *   POST http://localhost:8080/api/employees  (JSON body: {"name":"Alice","department":"IT","salary":60000})
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

// ===== Employee Model =====
class Employee {

    private int    id;
    private String name;
    private String department;
    private double salary;

    public Employee() {}

    public Employee(int id, String name, String department, double salary) {
        this.id         = id;
        this.name       = name;
        this.department = department;
        this.salary     = salary;
    }

    public int    getId()         { return id; }
    public String getName()       { return name; }
    public String getDepartment() { return department; }
    public double getSalary()     { return salary; }

    public void setId(int id)                  { this.id = id; }
    public void setName(String name)           { this.name = name; }
    public void setDepartment(String dept)     { this.department = dept; }
    public void setSalary(double salary)       { this.salary = salary; }
}

// ===== REST Controller =====
@RestController
@RequestMapping("/api/employees")
class EmployeeRestController {

    private final List<Employee>  employees = new ArrayList<>();
    private final AtomicInteger   idCounter = new AtomicInteger(1);

    // Pre-populate sample data
    public EmployeeRestController() {
        employees.add(new Employee(idCounter.getAndIncrement(), "Ravi Kumar",  "Engineering", 75000));
        employees.add(new Employee(idCounter.getAndIncrement(), "Priya Singh", "HR",          60000));
    }

    // GET /api/employees — Retrieve all employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employees;
    }

    // GET /api/employees/{id} — Retrieve by ID
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        return employees.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + id));
    }

    // POST /api/employees — Add a new employee (JSON body)
    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        employee.setId(idCounter.getAndIncrement());
        employees.add(employee);
        return employee;
    }
}

// ===== Main Application =====
@SpringBootApplication
public class A10_EmployeeRestController {
    public static void main(String[] args) {
        SpringApplication.run(A10_EmployeeRestController.class, args);
        System.out.println("Employee REST API running at http://localhost:8080/api/employees");
    }
}

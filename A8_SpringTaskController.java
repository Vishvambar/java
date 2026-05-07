/*
 * Spring Boot — pom.xml dependencies required:
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
 *     <dependency>
 *         <groupId>org.springframework.boot</groupId>
 *         <artifactId>spring-boot-starter-data-jpa</artifactId>
 *     </dependency>
 *     <dependency>
 *         <groupId>com.h2database</groupId>
 *         <artifactId>h2</artifactId>
 *         <scope>runtime</scope>
 *     </dependency>
 * </dependencies>
 *
 * application.properties:
 *   spring.datasource.url=jdbc:h2:mem:testdb
 *   spring.h2.console.enabled=true
 *   spring.jpa.hibernate.ddl-auto=update
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.jpa.repository.JpaRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.List;

// ===== Entity =====
@Entity
class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean completed;

    public Task() {}

    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    public Long    getId()        { return id; }
    public String  getName()      { return name; }
    public boolean isCompleted()  { return completed; }

    public void setId(Long id)              { this.id = id; }
    public void setName(String name)        { this.name = name; }
    public void setCompleted(boolean c)     { this.completed = c; }
}

// ===== Repository =====
interface TaskRepository extends JpaRepository<Task, Long> {}

// ===== Controller =====
@Controller
class TaskController {

    private final TaskRepository repo;

    public TaskController(TaskRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/tasks")
    public String listTasks(Model model) {
        List<Task> tasks = repo.findAll();
        model.addAttribute("tasks", tasks);
        return "tasks";  // resolves to templates/tasks.html (Thymeleaf)
    }

    @PostMapping("/tasks")
    public String addTask(@RequestParam String name) {
        repo.save(new Task(name));
        return "redirect:/tasks";
    }
}

// ===== Main Application =====
@SpringBootApplication
public class A8_SpringTaskController {
    public static void main(String[] args) {
        SpringApplication.run(A8_SpringTaskController.class, args);
    }
}

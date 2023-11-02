package sky.pro.course3.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sky.pro.course3.homework.model.Faculty;
import sky.pro.course3.homework.model.Student;
import sky.pro.course3.homework.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping("{id}")
    @Operation(summary = "Получить студента по Id")
    public ResponseEntity<Student> getStudentInfo(@PathVariable Long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }
    @PostMapping
    @Operation(summary = "Создание нового студента")
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }
    @PutMapping
    @Operation(summary = "Редактирование студента")
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student foundStudent = studentService.editStudent(student);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);
    }
    @DeleteMapping("{id}")
    @Operation(summary = "Удаление студента")
    public ResponseEntity<Student> deleteStudent(Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping
    @Operation(summary = "Найти всех студентов")
    public Collection<Student> findAllStudents() {
        return studentService.getAllStudents();
    }
    @GetMapping("/filter")
    @Operation(summary = "Найти студентов в возрасте из промежутка")
    public Collection<Student> findStudentsByAge(@RequestParam int min,
                                                 @RequestParam int max) {
        return studentService.findStudentsByAge(min,max);
    }
    @GetMapping("faculty/{studentId}")
    @Operation(summary = "Получить факультет студента")
    public ResponseEntity<Faculty> getFaculty(@PathVariable Long studentId) {
        Faculty faculty = studentService.findStudent(studentId).getFaculty();
        return ResponseEntity.ok(faculty);
    }
    @GetMapping("/counterAllStudents")
    @Operation(summary = "Получить количество всех студентов в школе")
    public Integer findAllStudentsInSchool() {
        return studentService.findAllStudentsInSchool();
    }
    @GetMapping("/averageAge")
    @Operation(summary = "Получить средний возраст студентов")
    public Integer findAverageAgeStudents() {
        return studentService.findAverageAgeStudents();
    }
    @GetMapping("/lastFiveStudents")
    @Operation(summary = "Получить последних 5 студентов")
    public Collection<Student> findLastFiveStudents() {
        return studentService.findLastFiveStudents();
    }
}
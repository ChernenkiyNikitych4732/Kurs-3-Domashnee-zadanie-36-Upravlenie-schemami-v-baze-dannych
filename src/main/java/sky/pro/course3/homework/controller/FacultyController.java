package sky.pro.course3.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sky.pro.course3.homework.model.Faculty;
import sky.pro.course3.homework.model.Student;
import sky.pro.course3.homework.service.FacultyService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }
    @GetMapping("{id}")
    @Operation(summary = "Получение факультета по Id")
    public ResponseEntity<Faculty> getFacultyInfo(@PathVariable Long id) {
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PostMapping
    @Operation(summary = "Создание нового факультета")
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @PutMapping
    @Operation(summary = "Редактирование факультета")
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty) {
        Faculty foundFaculty = facultyService.editFaculty(faculty);
        if (foundFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundFaculty);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление факультета по Id")
    public ResponseEntity<Faculty> deleteFaculty(Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping
    @Operation(summary = "Получить список всех факультетов")
    public Collection<Faculty> findAllFaculty() {
        return facultyService.getAllFaculty();
    }

    @GetMapping("/filter")
    @Operation(summary = "Найти факультет по имени или цвету")
    public Collection<Faculty> findFacultyByNameOrColor(@RequestParam(required = false) String name,
                                                        @RequestParam(required = false) String color) {
        return facultyService.findFacultyByNameOrColor(name,color);
    }

    @GetMapping("students/{facultyId}")
    @Operation(summary = "Получить список студентов факультета")
    public ResponseEntity<List<Student>> getStudents(@PathVariable Long facultyId) {
        List<Student> students = facultyService.findFaculty(facultyId).getStudentList();
        return ResponseEntity.ok(students);
    }
}
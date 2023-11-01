package sky.pro.course3.homework;

import net.minidev.json.JSONObject;
import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import sky.pro.course3.homework.controller.FacultyController;
import sky.pro.course3.homework.controller.StudentController;
import sky.pro.course3.homework.model.Faculty;
import sky.pro.course3.homework.model.Student;
import sky.pro.course3.homework.repository.FacultyRepository;
import sky.pro.course3.homework.repository.StudentRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HogwartsApplicationTests {
    @LocalServerPort
    private int port;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    FacultyRepository facultyRepository;
    @Autowired
    private StudentController studentController;
    @Autowired
    private FacultyController facultyController;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void contextLoads() {
        Assertions.assertThat(studentController).isNotNull();
        Assertions.assertThat(facultyController).isNotNull();
    }
    @Test
    void findStudent() {
        Assertions.
                assertThat(this.testRestTemplate.getForObject(
                        "http://localhost:" + port + "/student/1", String.class)).isNotNull();
    }
    @Test
    void findFaculty() {
        Assertions.
                assertThat(this.testRestTemplate.getForObject(
                        "http://localhost:" + port + "/faculty/1", String.class)).isNotNull();
    }
    @Test
    void testPostStudent(){
        Student student = new Student(7L,"Son",29);
        Assertions.
                assertThat(this.testRestTemplate.postForObject(
                        "http://localhost:" + port + "/student", student, String.class)).isNotNull();
    }
    @Test
    void testPostFaculty(){
        Faculty faculty = new Faculty(7L,"Newcastle","black-white");
        Assertions.
                assertThat(this.testRestTemplate.postForObject(
                        "http://localhost:" + port + "/faculty", faculty, String.class)).isNotNull();
    }
    @Test
    void testDeleteStudent() throws Exception{
        Student student = studentRepository.findById(7L).orElse(null);
        Long studentId = (student == null) ? null : student.getId();
        this.testRestTemplate.delete("http://localhost:" + port + "/students/7");
        assertNotEquals(7L, studentId);
    }
    @Test
    void testDeleteFaculty() throws Exception{
        Faculty faculty = facultyRepository.findById(1L).orElse(null);
        Long facultyId = (faculty == null) ? null : faculty.getId();
        this.testRestTemplate.delete("http://localhost:" + port + "/faculty/1");
        assertNotEquals(1L, facultyId);
    }
    @Test
    void findByColor() {
        Assertions.
                assertThat(this.testRestTemplate.getForObject(
                        "http://localhost:" + port + "/faculty/?color=red", String.class)).isNotNull();

    }
    @Test
    void getFacultyOfStudent() {
        Assertions.
                assertThat(this.testRestTemplate.getForObject(
                        "http://localhost:" + port + "/faculty/?studentId=1", String.class)).isNotNull();

    }
    @Test
    void findByAge() {
        Assertions.
                assertThat(this.testRestTemplate.getForObject(
                        "http://localhost:" + port + "/students/?age=29", String.class)).isNotNull();

    }
    @Test
    void findByAgeBetween() {
        Assertions.
                assertThat(this.testRestTemplate.getForObject(
                        "http://localhost:" + port + "/students/?minAge=25&&maxAge=30", String.class)).isNotNull();

    }
    @Test
    void findStudentsByFaculty() {
        Assertions.
                assertThat(this.testRestTemplate.getForObject(
                        "http://localhost:" + port + "/students/?id=1", String.class)).isNotNull();

    }
}

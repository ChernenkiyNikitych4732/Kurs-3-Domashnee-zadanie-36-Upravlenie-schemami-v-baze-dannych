package sky.pro.course3.homework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sky.pro.course3.homework.model.Student;
import sky.pro.course3.homework.repository.StudentRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }
    public Student findStudent(long id) {
        return studentRepository.findById(id).get();
    }
    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
    public Collection<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    public Collection<Student> findStudentsByAge(int min,int max) {
        return studentRepository.findByAgeBetween(min, max);
    }
    public Integer findAllStudentsInSchool() {
        return studentRepository.findAllStudentsInSchool();
    }
    public Integer findAverageAgeStudents() {
        return studentRepository.findAverageAgeStudents();
    }
    public Collection<Student> findLastFiveStudents() {
        return studentRepository.findLastFiveStudents();
    }
}
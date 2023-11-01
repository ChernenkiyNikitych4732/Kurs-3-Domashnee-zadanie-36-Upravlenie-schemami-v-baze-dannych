package sky.pro.course3.homework.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sky.pro.course3.homework.model.Faculty;
import sky.pro.course3.homework.repository.FacultyRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    @Autowired
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }
    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }
    public Faculty findFaculty(Long id) {
        return facultyRepository.findById(id).get();
    }
    public Faculty editFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }
    public void deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }
    public Collection<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }
    public Collection<Faculty> findFacultyByNameOrColor(String name,String color) {
        return facultyRepository.findFacultiesByNameIgnoreCaseOrColorIgnoreCase(name, color);
    }
}
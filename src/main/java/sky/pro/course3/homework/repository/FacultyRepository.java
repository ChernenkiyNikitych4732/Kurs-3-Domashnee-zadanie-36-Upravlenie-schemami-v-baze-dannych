package sky.pro.course3.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sky.pro.course3.homework.model.Faculty;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty,Long> {
    Collection<Faculty> findFacultiesByNameIgnoreCaseOrColorIgnoreCase(String name, String color);
}
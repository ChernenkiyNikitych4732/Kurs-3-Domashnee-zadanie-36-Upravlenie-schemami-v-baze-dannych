package sky.pro.course3.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sky.pro.course3.homework.model.Avatar;

import java.util.List;
import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar,Long> {
    Optional<Avatar> findByStudentId(Long studentId);

    @Override
    List<Avatar> findAll();
}
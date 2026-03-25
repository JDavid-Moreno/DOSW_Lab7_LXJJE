package edu.eci.dosw.TechCup.repository;

import edu.eci.dosw.TechCup.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;


public interface FileRepository extends JpaRepository<FileEntity, Long> {
    List<FileEntity> findByLastMod(LocalDateTime lastMod);
}

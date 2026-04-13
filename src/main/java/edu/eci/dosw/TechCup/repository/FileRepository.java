package edu.eci.dosw.TechCup.repository;

import edu.eci.dosw.TechCup.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Long> {
    List<FileEntity> findByLastMod(LocalDateTime lastMod);
}

package bg.softuni.repositories;

import bg.softuni.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectsRepository extends JpaRepository<Project,Long> {

    List<Project> findAllByisFinished(boolean condition);

   Optional<Project> findFirstByName(String name);

   Optional <Project> findByName(String name);
}

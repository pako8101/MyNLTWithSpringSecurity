package bg.softuni.repositories;

import bg.softuni.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompaniesRepository extends JpaRepository<Company,Long> {
    Optional<Company> findByName(String name);
}

package kodlama.io.devs.dataAccess.abstracts;

import kodlama.io.devs.entities.Framework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FrameworkRepository extends JpaRepository<Framework, Integer> {
}

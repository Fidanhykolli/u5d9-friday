package fidanhykolli.u5d9friday.repositories;

import fidanhykolli.u5d9friday.entities.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {
}

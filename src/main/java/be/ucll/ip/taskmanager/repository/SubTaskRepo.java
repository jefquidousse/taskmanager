package be.ucll.ip.taskmanager.repository;

import be.ucll.ip.taskmanager.domain.Subtask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubTaskRepo extends JpaRepository<Subtask, Integer> {
}

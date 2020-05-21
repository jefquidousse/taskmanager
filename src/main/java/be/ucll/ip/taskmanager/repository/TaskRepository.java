package be.ucll.ip.taskmanager.repository;

import be.ucll.ip.taskmanager.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}

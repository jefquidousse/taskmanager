package be.ucll.ip.taskmanager.service;

import be.ucll.ip.taskmanager.dto.SubTaskDTO;
import be.ucll.ip.taskmanager.dto.TaskDTO;

import java.util.List;

public interface TaskService {
    List<TaskDTO> getTasks();

    TaskDTO getTask(int id);

    void addTask(TaskDTO task);

    void addSubtask(SubTaskDTO taskDTO, int id);

    void editTask(TaskDTO taskDTO);
}

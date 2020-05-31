package be.ucll.ip.taskmanager.service;

import be.ucll.ip.taskmanager.domain.Subtask;
import be.ucll.ip.taskmanager.domain.Task;
import be.ucll.ip.taskmanager.dto.SubTaskDTO;
import be.ucll.ip.taskmanager.dto.TaskDTO;
import be.ucll.ip.taskmanager.repository.SubTaskRepo;
import be.ucll.ip.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskrepo;
    private final SubTaskRepo subTaskRepo;

    @Autowired
    public TaskServiceImpl(TaskRepository taskrepo, SubTaskRepo subTaskRepo){
        this.taskrepo = taskrepo;
        this.subTaskRepo = subTaskRepo;
    }

    @Override
    public List<TaskDTO> getTasks(){
        return taskrepo.findAll().stream().map(t -> {
            TaskDTO dto = new TaskDTO();
            dto.setDate(t.getDate());
            dto.setDescription(t.getDescription());
            dto.setName(t.getName());
            dto.setId(t.getId());
            /*dto.setSubtasks(t.getSubtasks());*/
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public TaskDTO getTask(int id) {
        Task task = taskrepo.getOne(id);
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setName(task.getName());
        taskDTO.setDate(task.getDate());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setSubtasks(task.getSubtasks());
        return taskDTO;
    }

    @Override
    public void addTask(TaskDTO task) {
        Task task1 = new Task(task.getName(), task.getDescription(), task.getDate());
        taskrepo.save(task1);
    }

    @Override
    public void addSubtask(SubTaskDTO subtask, int id) {
        Task task = taskrepo.getOne(id);

        Subtask t = new Subtask();
        t.setSubtaskDescription(subtask.getSubtaskDescription());
        t.setSubtaskId(subtask.getSubtaskId());
        t.setSubtaskName(subtask.getSubtaskName());
        t.setTask(task);

        subTaskRepo.save(t);
    }

    @Override
    public void editTask(TaskDTO taskDTO) {
        Task task = taskrepo.getOne(taskDTO.getId());
        task.setName(taskDTO.getName());
        task.setDescription(taskDTO.getDescription());
        task.setDate(taskDTO.getDate());
        taskrepo.save(task);
    }


}

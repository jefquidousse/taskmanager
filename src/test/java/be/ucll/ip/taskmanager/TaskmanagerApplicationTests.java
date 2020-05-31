package be.ucll.ip.taskmanager;

import be.ucll.ip.taskmanager.domain.Subtask;
import be.ucll.ip.taskmanager.domain.Task;
import be.ucll.ip.taskmanager.dto.SubTaskDTO;
import be.ucll.ip.taskmanager.dto.TaskDTO;
import be.ucll.ip.taskmanager.repository.SubTaskRepo;
import be.ucll.ip.taskmanager.repository.TaskRepository;
import be.ucll.ip.taskmanager.service.TaskService;
import be.ucll.ip.taskmanager.service.TaskServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class TaskmanagerApplicationTests {
    @Autowired
    private TaskService taskService;

    @Test
    void contextLoads() {
    }

    @Test
    void taskServicetest(){
        TaskDTO task = new TaskDTO();
        task.setDate(LocalDateTime.of(2020, 6, 20, 11, 22));
        task.setName("test1");
        task.setDescription("erererer");

        taskService.addTask(task);

        List<TaskDTO> tasks = taskService.getTasks();


        assertNotNull(tasks);

        assertFalse(tasks.isEmpty());
        assertEquals(1, tasks.size());
        TaskDTO t = tasks.get(0);
        assertNotNull(t);

    }

    @Test
    @Transactional
    void taskServiceEditTest(){
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setDescription("test");
        taskDTO.setName("test");
        taskDTO.setDate(LocalDateTime.of(2020, 6, 3, 11, 11));
        taskService.addTask(taskDTO);
        TaskDTO t = taskService.getTasks().get(0);
        t.setName("tt");
        t.setDescription("ERRD");

        taskService.editTask(t);


        assertTrue(t.getName().equals("tt"));
    }

    @Test
    @Transactional
    void taskServiceSubtaskTest(){
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setDescription("test");
        taskDTO.setName("test");
        taskDTO.setDate(LocalDateTime.of(2020, 6, 3, 11, 11));
        taskService.addTask(taskDTO);

        SubTaskDTO subTaskDTO = new SubTaskDTO();
        subTaskDTO.setSubtaskDescription("test");
        subTaskDTO.setSubtaskName("test");

        taskService.addSubtask(subTaskDTO, taskService.getTasks().get(0).getId());

        TaskDTO t = taskService.getTasks().get(0);

        assertNotNull(t.getSubtasks());
    }

    @Test
    void taskDTOtest(){
        LocalDateTime date = LocalDateTime.of(2020, 6, 3, 11, 11);
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setName("eee");
        taskDTO.setDate(date);
        taskDTO.setDescription("eee");
        taskService.addTask(taskDTO);

        String name = taskDTO.getName();
        String description = taskDTO.getDescription();
        LocalDateTime t = taskDTO.getDate();
        int id = taskDTO.getId();

        assertFalse(name.isEmpty());
        assertFalse(description.isEmpty());
        assertTrue(t.equals(LocalDateTime.of(2020,6,3,11,11)));
        assertTrue(id == 0);

    }


    @Test
    void tasktest(){
        LocalDateTime date = LocalDateTime.of(2020, 6, 3, 11, 11);
        Task task = new Task();
        task.setName("eee");
        task.setDate(date);
        task.setDescription("eee");


        String name = task.getName();
        String description = task.getDescription();
        LocalDateTime t = task.getDate();

        assertFalse(name.isEmpty());
        assertFalse(description.isEmpty());
        assertTrue(t.equals(LocalDateTime.of(2020,6,3,11,11)));

    }

    @Test
    void subTaskTest(){
        Task task = new Task();
        Subtask sub = new Subtask();
        sub.setSubtaskName("test");
        sub.setSubtaskDescription("test");
        sub.setTask(task);

        assertFalse(sub.getSubtaskName().isEmpty());
        assertFalse(sub.getSubtaskDescription().isEmpty());
        assertNotNull(sub.getTask());
    }

    @Test
    void subTaskDTOTest(){
        SubTaskDTO sub = new SubTaskDTO();
        sub.setSubtaskName("test");
        sub.setSubtaskDescription("test");


        assertFalse(sub.getSubtaskName().isEmpty());
        assertFalse(sub.getSubtaskDescription().isEmpty());
    }







}

package be.ucll.ip.taskmanager.dto;



import be.ucll.ip.taskmanager.domain.Subtask;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class TaskDTO {

    private int taskid;


    @NotEmpty(message = "Please fill the title in")
    private String taskname;

    @NotEmpty(message = "Please fill the description in")
    private String description;

    @NotNull
    @Future
    private LocalDateTime date;

    private List<Subtask> subtasks = new ArrayList<>();



    public TaskDTO(String name, String description, LocalDateTime date, int taskid){
        this.taskname = name;
        this.description = description;
        this.date = date;
        this.taskid = taskid;
    }

    public TaskDTO(){}

    public String getName() {
        return taskname;
    }

    public void setName(String name) {
        this.taskname = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateString() {return date.getMonth().name().toLowerCase() + " " + date.getDayOfMonth() + " " + date.getYear() + " at " + date.getHour();}

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getId() {
        return taskid;
    }

    public void setId(int id) {
        this.taskid = id;
    }

    public List<Subtask> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(List<Subtask> subtasks) {
        this.subtasks = subtasks;
    }


}

package be.ucll.ip.taskmanager.domain;

import be.ucll.ip.taskmanager.dto.TaskDTO;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name = "subtasks")
public class Subtask {
    @NotEmpty
    private String subtaskName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subtaskId;

    private String subtaskDescription;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "taskId", nullable = false)
    private Task task;

    public Subtask(String  subtaskName, String description){
        this.subtaskDescription = description;
        this.subtaskName = subtaskName;
    }

    public Subtask(){};

    public String getSubtaskName() {
        return subtaskName;
    }

    public void setSubtaskName(String subtaskName) {
        this.subtaskName = subtaskName;
    }

    public String getSubtaskDescription() {
        return subtaskDescription;
    }

    public void setSubtaskDescription(String subtaskDescription) {
        this.subtaskDescription = subtaskDescription;
    }

    public int getSubtaskId() {
        return subtaskId;
    }

    public void setSubtaskId(int subtaskId) {
        this.subtaskId = subtaskId;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}

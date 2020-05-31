package be.ucll.ip.taskmanager.dto;

import be.ucll.ip.taskmanager.dto.TaskDTO;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


public class SubTaskDTO {

    @NotEmpty
    private String subtaskName;

    private int subtaskId;

    @NotEmpty
    private String subtaskDescription;



    public SubTaskDTO(String  subtaskName, String description){
        this.subtaskDescription = description;
        this.subtaskName = subtaskName;
    }

    public SubTaskDTO(){};

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

}

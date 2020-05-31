package be.ucll.ip.taskmanager.domain;


import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "TASKS")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int taskid;

    @NotEmpty(message = "Please fill the title in")
    private String taskname;

    @NotNull
    @Future
    private LocalDateTime date;

    @NotEmpty(message = "Please fill the description in")
    private String description;

    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Subtask> subtasks = new ArrayList<>();

    public Task(){}

    public Task(String name, String description, LocalDateTime date){
        this.taskname = name;
        this.description = description;
        this.date = date;
    }


    public List<Subtask> getSubtasks() {
        return subtasks;
    }

    public void addSubtasks(Subtask task) {
        subtasks.add(task);
    }

    public void setTaskid(int taskid) {
        this.taskid = taskid;
    }

    public int getId() {
        return taskid;
    }

    public String getName(){
        return this.taskname;
    }

    public void setName(String name) {
        this.taskname = name;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getDate(){ return date;}

    public String getDateString() {

        return date.getMonth().name().toLowerCase() + " " + date.getDayOfMonth() + " " + date.getYear() + " at " + date.getHour();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

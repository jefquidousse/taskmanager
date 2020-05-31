package be.ucll.ip.taskmanager.controller;


import be.ucll.ip.taskmanager.domain.Subtask;
import be.ucll.ip.taskmanager.domain.Task;
import be.ucll.ip.taskmanager.dto.SubTaskDTO;
import be.ucll.ip.taskmanager.dto.TaskDTO;
import be.ucll.ip.taskmanager.service.TaskService;
import org.apache.tomcat.util.descriptor.LocalResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

@org.springframework.stereotype.Controller
@RequestMapping("/")
public class Controller {
    private final TaskService taskService;
    private String Fouten = null;
    static DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

    @Autowired
    public Controller(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping
    public String index(){
        return "index";
    }

    @GetMapping("tasks")
    public String getTasks(Model model){
        model.addAttribute("tasks", taskService.getTasks());
        if(Fouten != null){
            model.addAttribute("error", Fouten);
        }
        Fouten = null;
        return "tasks";
    }

    @GetMapping("tasks/new")
    public String getForm(Model model){
        model.addAttribute("task", new Task());
        return "form";
    }

    @PostMapping("new")
    public String addTask(Model model, @RequestParam("tname") String name, @RequestParam("tdescription") String description, @RequestParam("tdate") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME)LocalDateTime date){
        TaskDTO task = new TaskDTO();
        ArrayList<String> error = new ArrayList<>();

        try{
            date.format(formatter);
        }catch (NullPointerException n){
            error.add(n.getMessage());
        }

        if (description.trim().isEmpty() || name.trim().isEmpty() || !error.isEmpty()){
            error.add("Please fill in a name, a description and a date that is in the future");
            model.addAttribute("error", error);
            return "form";
        }
        if(date.isBefore(LocalDateTime.now())){
            String t = "";
            model.addAttribute("date", t);
            return "form";
        }

        task.setDescription(description);
        task.setName(name);
        task.setDate(date);
        taskService.addTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("tasks/{id}")
    public String showDescription(Model model, @PathVariable("id") int id){


        for(TaskDTO taskDTO : taskService.getTasks()){
            if (taskDTO.getId() == id){
                model.addAttribute("task", taskService.getTask(id));
                model.addAttribute("subtasks", taskService.getTask(id).getSubtasks());
                System.out.print(taskService.getTask(id).getSubtasks().size());
                return "taskdescription";
            }
        }

        Fouten = "";
        return "redirect:/tasks";
    }

    @GetMapping("tasks/edit/{id}")
    public String edit(Model model, @PathVariable("id") int id){
        for(TaskDTO taskDTO : taskService.getTasks()){
            if (taskDTO.getId() == id){
                model.addAttribute("task", taskService.getTask(id));
                return "edit";
            }
        }

        Fouten = "";
        return "redirect:/tasks";

    }

    @PostMapping("update/{id}")
    public String submit(Model model, @PathVariable("id") int id, @RequestParam("name")String name, @RequestParam("description")String description, @RequestParam("date") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME)LocalDateTime date){
        String error = null;
        if (name.trim().isEmpty() || description.trim().isEmpty() || date.isBefore(LocalDateTime.now())){
            error = "";
            model.addAttribute("error", error);
            model.addAttribute("task", taskService.getTask(id));
            return "edit";
        }
        TaskDTO taskDTO = new TaskDTO(name, description, date, id);
       taskService.editTask(taskDTO);
       return "redirect:/tasks/{id}";
    }

    @GetMapping("tasks/{id}/sub/create")
    public String create(Model model, @PathVariable("id")int id){
        for(TaskDTO taskDTO : taskService.getTasks()){
            if (taskDTO.getId() == id){
                model.addAttribute("task", taskService.getTask(id));
                return "create";
            }
        }

        Fouten = "";
        return "redirect:/tasks";


    }

    @PostMapping("{id}/subtask")
    public String subtask(Model model, @PathVariable("id") int id, @RequestParam("tname")String name, @RequestParam("tdescription")String description){
        String error = null;
        if (name.trim().isEmpty() || description.trim().isEmpty()){
            error = "";
            model.addAttribute("error", error);
            model.addAttribute("task", taskService.getTask(id));
            return "create";
        }
        SubTaskDTO subtask = new SubTaskDTO(name, description);

        taskService.addSubtask(subtask, id);
        return "redirect:/tasks/{id}";
    }

}





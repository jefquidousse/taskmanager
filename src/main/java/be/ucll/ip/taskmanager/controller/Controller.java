package be.ucll.ip.taskmanager.controller;


import be.ucll.ip.taskmanager.domain.Subtask;
import be.ucll.ip.taskmanager.domain.Task;
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
import java.time.LocalDateTime;
import java.util.Locale;

@org.springframework.stereotype.Controller
@RequestMapping("/")
public class Controller {
    private final TaskService taskService;

    @Autowired
    public Controller(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping
    public String index(){
        return "index";
    }

    @GetMapping("task")
    public String getTasks(Model model){
        model.addAttribute("tasks", taskService.getTasks());
        return "tasks";
    }

    @GetMapping("newTask")
    public String getForm(Model model){
        model.addAttribute("task", new Task());
        return "form";
    }

    @PostMapping("new")
    public String addTask(Model model, @RequestParam("tname") String name, @RequestParam("tdescription") String description, @RequestParam("tdate") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME)LocalDateTime date){
        TaskDTO task = new TaskDTO();
        task.setDescription(description);
        task.setName(name);
        task.setDate(date);
        taskService.addTask(task);
        return "redirect:/task";
    }

    @GetMapping("tasks/{id}")
    public String showDescription(Model model, @PathVariable("id") int id){
        TaskDTO taskDTO = taskService.getTask(id);
        model.addAttribute("task", taskDTO);
        return "taskdescription";

    }

    @GetMapping("edit/{id}")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("task", taskService.getTask(id));
        return "edit";
    }

    @PostMapping("update/{id}")
    public String submit(Model model, @PathVariable("id") int id, @RequestParam("name")String name, @RequestParam("description")String description, @RequestParam("date") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME)LocalDateTime date){
        TaskDTO taskDTO = new TaskDTO(name, description, date, id);
       taskService.editTask(taskDTO);
       return "redirect:/task";
    }

    @GetMapping("{id}/sub/create")
    public String create(Model model, @PathVariable("id")int id){
        model.addAttribute("task", taskService.getTask(id));
        return "create";
    }

    @PostMapping("{id}/subtask")
    public String subtask(Model model, @PathVariable("id") int id, @RequestParam("tname")String name, @RequestParam("tdescription")String description){
        Subtask subtask = new Subtask(name, description);
        taskService.addSubtask(subtask, id);
        model.addAttribute("task", taskService.getTask(id));
        return "taskdescription";
    }

}





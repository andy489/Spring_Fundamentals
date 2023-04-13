package com.gira.web;

import com.gira.model.dto.TaskAddDto;
import com.gira.service.TaskService;
import com.gira.session.CurrentUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("tasks")
public class TaskController extends GenericController {

    private final TaskService taskService;
    private final CurrentUser currentUser;

    @Autowired
    public TaskController(
            TaskService taskService,
            CurrentUser currentUser
    ) {
        this.taskService = taskService;
        this.currentUser = currentUser;
    }

    @ModelAttribute(name = "taskAddModel")
    public TaskAddDto initTaskAddModel() {
        return new TaskAddDto();
    }

    @GetMapping("/add")
    public ModelAndView getAddTask() {
        if (!currentUser.isLoggedIn()) {
            return super.redirect("/users/login");
        }

        return super.view("add-task");
    }

    @PostMapping("/add")
    public ModelAndView postAddTask(
            @Valid @ModelAttribute(name = "taskAddModel") TaskAddDto taskAddDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("taskAddModel", taskAddDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.taskAddModel", bindingResult);

            return super.redirect("add");
        }

        this.taskService.addTask(taskAddDto);

        return super.redirect("/home");
    }
}

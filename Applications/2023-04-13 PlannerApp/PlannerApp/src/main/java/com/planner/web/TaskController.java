package com.planner.web;

import com.planner.model.dto.TaskAddDto;
import com.planner.service.TaskService;
import com.planner.session.CurrentUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
        if (!currentUser.isLoggedIn()) {
            return super.redirect("/users/login");
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("taskAddModel", taskAddDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.taskAddModel", bindingResult);

            return super.redirect("add");
        }

        this.taskService.addTask(taskAddDto);

        return super.redirect("/home");
    }

    @Transactional
    @GetMapping("/remove/{id}")
    public String deleteTask(@PathVariable(name = "id") Long taskId) {
        Boolean removed = taskService.removeTask(taskId);

        return "redirect:/home";
    }

    @Transactional
    @GetMapping("/abandon/{id}")
    public String abandonTask(@PathVariable(name = "id") Long taskId) {
        Boolean abandon = taskService.abandonTask(taskId);

        return "redirect:/home";
    }

    @Transactional
    @GetMapping("/assign/{id}")
    public String assignTaskToMe(@PathVariable(name = "id") Long taskId) {
        Boolean assign = taskService.assignToMe(taskId);

        return "redirect:/home";
    }
}

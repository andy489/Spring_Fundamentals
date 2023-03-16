package com.gira.web;

import com.gira.service.TaskService;
import com.gira.session.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends BaseController {
    private final CurrentUser currentUser;
    private final TaskService taskService;

    @Autowired
    public HomeController(
            CurrentUser currentUser,
            TaskService taskService
    ) {
        this.currentUser = currentUser;
        this.taskService = taskService;
    }

    @GetMapping("/home")
    public ModelAndView getHome(ModelAndView mav) {
        if (!currentUser.isLoggedIn()) {
            return super.redirect("/users/login");
        }

        mav.addObject("allTasks", taskService.getViewAllTasks());

        return super.view("home", mav);
    }

    @GetMapping({"/", "/index"})
    public ModelAndView getIndex() {

        return super.view("index");
    }

    @GetMapping("/progress")
    public ModelAndView postProgress(
            @RequestParam(name = "id") Long taskId,
            @RequestParam(name = "currProgress") String currProgress
    ) {
        if (!currentUser.isLoggedIn()) {
            return super.redirect("/users/login");
        }

        taskService.updateProgress(taskId, currProgress);

        return super.redirect("/home");
    }
}

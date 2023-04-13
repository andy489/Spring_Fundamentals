package com.planner.web;

import com.planner.service.TaskService;
import com.planner.session.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends GenericController {

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

        mav.addObject("tasksHomeModel", taskService.getTasksHomeModel());

        return super.view("home", mav);
    }

    @GetMapping({"/", "/index"})
    public ModelAndView getIndex() {

        return super.view("index");
    }
}

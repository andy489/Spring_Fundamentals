package com.planner.service;

import com.planner.mapper.MapStructMapper;
import com.planner.model.dto.TaskAddDto;
import com.planner.model.entity.TaskEntity;
import com.planner.model.view.TasksHomeModel;
import com.planner.repository.TaskRepository;
import com.planner.session.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService {
    private final Logger LOGGER = LoggerFactory.getLogger(TaskService.class);

    private final TaskRepository taskRepository;

    private final MapStructMapper mapper;

    private final PriorityService priorityService;

    private final UserService userService;

    private final CurrentUser currentUser;

    @Autowired
    public TaskService(
            TaskRepository taskRepository,
            MapStructMapper mapper,
            PriorityService priorityService,
            UserService userService,
            CurrentUser currentUser) {

        this.taskRepository = taskRepository;
        this.mapper = mapper;
        this.priorityService = priorityService;
        this.userService = userService;
        this.currentUser=currentUser;
    }

    public void addTask(TaskAddDto taskAddDto) {
        TaskEntity taskEntity = mapper.toTaskEntity(taskAddDto);

        taskEntity.setPriority(priorityService.getByName(taskAddDto.getPriority()));
//        taskEntity.setAssignedTo(userService.getCurrentUserEntity());

        taskRepository.saveAndFlush(taskEntity);
    }

    public TasksHomeModel getTasksHomeModel() {

        Long currentUserId = userService.getCurrentUserEntity().getId();

        TasksHomeModel tasksHomeModel = new TasksHomeModel();

        tasksHomeModel.setMyTasks(taskRepository.findByAssignedToId(currentUserId).stream().map(mapper::toTaskView).toList());
        tasksHomeModel.setUnassignedTasks(taskRepository.findByAssignedToIdIsNull().stream().map(mapper::toTaskView).toList());

        return tasksHomeModel;
    }

    public Boolean removeTask(Long taskId) {
        if (!checkLoggedInAndNonNegativeId(taskId)) {
            return false;
        }

        Optional<TaskEntity> byId = taskRepository.findById(taskId);

        if (byId.isEmpty()) {
            return false;
        }

        TaskEntity taskEntity = byId.get();

        if (!currentUser.getId().equals(taskEntity.getAssignedTo().getId())) {
            return false;
        }

        taskRepository.deleteById(taskId);

        LOGGER.info("User [{}] with id [{}] finished task with id [{}].",
                currentUser.getUsername(), currentUser.getId(), taskEntity.getId());

        return true;
    }


    public Boolean abandonTask(Long taskId) {
        if (!checkLoggedInAndNonNegativeId(taskId)) {
            return false;
        }

        Optional<TaskEntity> byId = taskRepository.findById(taskId);

        if (byId.isEmpty()) {
            return false;
        }

        TaskEntity taskEntity = byId.get();

        if (!currentUser.getId().equals(taskEntity.getAssignedTo().getId())) {
            return false;
        }

        taskEntity.abandon();

        taskRepository.save(taskEntity);

        LOGGER.info("User [{}] with id [{}] abandoned task with id [{}].",
                currentUser.getUsername(), currentUser.getId(), taskEntity.getId());

        return true;
    }

    public Boolean assignToMe(Long taskId) {
        if (!checkLoggedInAndNonNegativeId(taskId)) {
            return false;
        }

        Optional<TaskEntity> byId = taskRepository.findById(taskId);

        if (byId.isEmpty()) {
            return false;
        }

        TaskEntity taskEntity = byId.get();

        if (taskEntity.getAssignedTo() != null) {
            return false;
        }

        taskEntity.assignTo(userService.getCurrentUserEntity());

        taskRepository.save(taskEntity);

        LOGGER.info("User [{}] with id [{}] assigned task with id [{}] to himself.",
                currentUser.getUsername(), currentUser.getId(), taskEntity.getId());

        return true;
    }

    private Boolean checkLoggedInAndNonNegativeId(Long taskId) {
        return currentUser.getLoggedIn() && taskId > 0;
    }
}

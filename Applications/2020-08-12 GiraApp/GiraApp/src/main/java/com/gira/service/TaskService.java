package com.gira.service;

import com.gira.mapper.MapStructMapper;
import com.gira.model.dto.TaskAddDto;
import com.gira.model.entity.TaskEntity;
import com.gira.model.enumerated.ProgressEnum;
import com.gira.model.view.TaskView;
import com.gira.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    private final MapStructMapper mapper;

    private final ClassificationService classificationService;

    private final UserService userService;

    @Autowired
    public TaskService(
            TaskRepository taskRepository,
            MapStructMapper mapper,
            ClassificationService classificationService,
            UserService userService
    ) {
        this.taskRepository = taskRepository;
        this.mapper = mapper;
        this.classificationService = classificationService;
        this.userService = userService;
    }

    public Optional<TaskEntity> getByName(String name) {
        return taskRepository.findByName(name);
    }


    public void addTask(TaskAddDto taskAddDto) {
        TaskEntity taskEntity = mapper.toTaskEntity(taskAddDto);

        taskEntity.setProgress(ProgressEnum.OPEN);
        taskEntity.setClassification(classificationService.getByName(taskAddDto.getClassification()));
        taskEntity.setAssignedTo(userService.getCurrentUserEntity());

        taskRepository.saveAndFlush(taskEntity);
    }

    public List<TaskView> getViewAllTasks() {

        return taskRepository.findAll().stream()
                .map(t -> {
                    TaskView taskView = mapper.toTaskView(t);

                    taskView.setAssignedTo(t.getAssignedTo().getUsername());
                    taskView.setClassification(t.getClassification().getName());

                    return taskView;
                }).toList();
    }

    public void updateProgress(Long taskId, String currProgress) {
        ProgressEnum currProgressEnum = ProgressEnum.valueOf(currProgress);

        ProgressEnum newProgressEnum = switch (currProgressEnum) {
            case OPEN -> ProgressEnum.IN_PROGRESS;
            case IN_PROGRESS -> ProgressEnum.COMPLETED;
            case COMPLETED -> null;
        };

        TaskEntity taskToUpdate = taskRepository.findById(taskId).orElseThrow(NoSuchElementException::new);

        if (newProgressEnum != null) {
            taskRepository.saveAndFlush(taskToUpdate.setProgress(newProgressEnum));
        } else {
            taskRepository.deleteById(taskId);
        }
    }

}

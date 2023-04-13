package com.planner.mapper;

import com.planner.model.dto.TaskAddDto;
import com.planner.model.dto.UserRegisterDto;
import com.planner.model.entity.TaskEntity;
import com.planner.model.entity.UserEntity;
import com.planner.model.view.TaskView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring")
public interface MapStructMapper {

    UserEntity toUserEntity(UserRegisterDto userRegisterDto);

    @Mapping(target = "assignedTo", ignore = true)
    @Mapping(target = "priority", ignore = true)
    TaskEntity toTaskEntity(TaskAddDto taskAddDto);

    @Mapping(target = "assignedTo", ignore = true)
    @Mapping(target = "priority", ignore = true)
    TaskView toView(TaskEntity taskEntity);


    default TaskView toTaskView(TaskEntity taskEntity){
        TaskView taskView = toView(taskEntity); // map as much as you can
        taskView.setPriority(taskEntity.getPriority().getName().getDisplayName());
        return taskView;
    }
}


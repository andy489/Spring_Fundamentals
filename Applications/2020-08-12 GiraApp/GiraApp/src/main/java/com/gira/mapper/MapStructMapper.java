package com.gira.mapper;

import com.gira.model.dto.TaskAddDto;
import com.gira.model.dto.UserRegisterDto;
import com.gira.model.entity.TaskEntity;
import com.gira.model.entity.UserEntity;
import com.gira.model.view.TaskView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

    UserEntity toUserEntity(UserRegisterDto userRegisterDto);

    TaskEntity toTaskEntity(TaskAddDto taskAddDto);

    @Mapping(target = "assignedTo", ignore = true)
    @Mapping(target = "classification", ignore = true)
    TaskView toTaskView(TaskEntity taskEntity);
}


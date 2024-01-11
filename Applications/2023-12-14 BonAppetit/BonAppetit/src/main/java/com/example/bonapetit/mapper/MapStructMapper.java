package com.example.bonapetit.mapper;

import com.example.bonapetit.model.dto.RecipeAddDto;
import com.example.bonapetit.model.dto.UserRegisterDto;
import com.example.bonapetit.model.entity.RecipeEntity;
import com.example.bonapetit.model.entity.UserEntity;
import com.example.bonapetit.model.view.RecipeView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

    UserEntity toUserEntity(UserRegisterDto userRegisterDto);

    @Mapping(target = "category", ignore = true)
    RecipeEntity toEntity(RecipeAddDto recipeAddDto);

    @Mapping(target="category", ignore = true)
    RecipeView toView(RecipeEntity recipeEntity);


    default RecipeView toRecipeFull(RecipeEntity recipeEntity){
        RecipeView recipeView = this.toView(recipeEntity);
        recipeView.setCategory(recipeEntity.getCategory().getName());

        return recipeView;
    }
}

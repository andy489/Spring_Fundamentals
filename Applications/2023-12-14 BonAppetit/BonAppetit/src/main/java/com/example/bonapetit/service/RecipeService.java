package com.example.bonapetit.service;

import com.example.bonapetit.mapper.MapStructMapper;
import com.example.bonapetit.model.dto.RecipeAddDto;
import com.example.bonapetit.model.entity.RecipeEntity;
import com.example.bonapetit.model.entity.UserEntity;
import com.example.bonapetit.model.enumerated.CategoryEnum;
import com.example.bonapetit.model.view.RecipeHomeModel;
import com.example.bonapetit.model.view.RecipeView;
import com.example.bonapetit.repo.RecipeRepository;
import com.example.bonapetit.session.CurrentUser;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@Service
public class RecipeService {

    private final Logger LOGGER = LoggerFactory.getLogger(RecipeService.class);

    private final RecipeRepository recipeRepository;

    private final MapStructMapper mapper;

    private final CategoryService categoryService;

    private final UserService userService;

    private final CurrentUser currentUser;


    @Autowired
    public RecipeService(RecipeRepository recipeRepository,
                         MapStructMapper mapper,
                         CategoryService categoryService,
                         UserService userService,
                         CurrentUser currentUser) {

        this.recipeRepository = recipeRepository;
        this.mapper = mapper;
        this.categoryService = categoryService;
        this.userService = userService;
        this.currentUser = currentUser;
    }

    public void addRecipe(RecipeAddDto recipeAddDto) {
        RecipeEntity recipeEntity = mapper.toEntity(recipeAddDto);

        recipeEntity.setCategory(categoryService.getByName(recipeAddDto.getCategory()));
        recipeEntity.setAddedBy(userService.getCurrentUserEntity());

        recipeRepository.saveAndFlush(recipeEntity);
    }

    @Transactional
    public RecipeHomeModel getRecipesHomeModel() {

        RecipeHomeModel recipeHomeModel = new RecipeHomeModel();

        Map<CategoryEnum, List<RecipeView>> recipes =
                recipeRepository.findAll().stream()
                        .map(mapper::toRecipeFull)
                        .collect(groupingBy(RecipeView::getCategory));

        UserEntity currentUserEntity = userService.getCurrentUserEntity();

        List<RecipeView> favourites = currentUserEntity.getFavouriteRecipes().stream().map(mapper::toRecipeFull)
                .toList();

        recipeHomeModel.setRecipes(recipes);
        recipeHomeModel.setFavourites(favourites);

        return recipeHomeModel;
    }

    @Transactional
    public void like(Long recipeId) {
        UserEntity currentUserEntity = userService.getCurrentUserEntity();
        currentUserEntity.addFavourite(recipeRepository.getReferenceById(recipeId));
    }

    @Transactional
    public void unlike(Long recipeId) {
        UserEntity currentUserEntity = userService.getCurrentUserEntity();
        currentUserEntity.deleteFavourite(recipeRepository.getReferenceById(recipeId));
    }
}

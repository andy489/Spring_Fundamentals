package com.dictionaryapp.mapper;

import com.dictionaryapp.model.dto.UserRegisterDto;
import com.dictionaryapp.model.dto.WordAddDto;
import com.dictionaryapp.model.entity.LanguageEntity;
import com.dictionaryapp.model.entity.UserEntity;
import com.dictionaryapp.model.entity.WordEntity;
import com.dictionaryapp.model.enumerated.LanguageEnum;
import com.dictionaryapp.model.view.WordView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

    UserEntity toUserEntity(UserRegisterDto userRegisterDto);

    @Mapping(target = "language", ignore = true)
    @Mapping(target = "addedBy", ignore = true)
    WordEntity toEntity(WordAddDto wordAddDto);

    @Mapping(target = "addedBy", ignore = true)
    @Mapping(source="language", target="language", qualifiedByName = "languageMap")
    WordView toView(WordEntity wordEntity);

    default WordView toWordFull(WordEntity wordEntity, String addedByUsername){
        WordView wordView = this.toView(wordEntity);
        wordView.setAddedBy(addedByUsername);
        return wordView;
    }

    @Named("languageMap")
    static LanguageEnum customLanguageMap(LanguageEntity languageEntity){
        return languageEntity.getLanguageName();
    }
}

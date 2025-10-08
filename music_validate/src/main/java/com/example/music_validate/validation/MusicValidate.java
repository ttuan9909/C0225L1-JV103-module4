package com.example.music_validate.validation;

import com.example.music_validate.entity.Music;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class MusicValidate implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Music music = (Music) target;
        if (music.getName().isEmpty()){
            errors.rejectValue("name","notEmpty","Không để trống");
        }else if (!music.getName().matches("^[^@;,.=\\-+!#$%^&*(){}\\[\\]:\"'<>/?\\\\|`~]+$")){
            errors.rejectValue("name","name.patter","Không đúng định dạng");

        }
    }
}

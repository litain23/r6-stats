package org.example.springboot.service.validate;

import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.userprofile.UserProfileRepository;
import org.example.springboot.exception.user.UserValidateException;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
@Component
public class UsernameDuplicationValidator implements ConstraintValidator<UsernameUnique, String> {
    private final UserProfileRepository userProfileRepository;

    @Override
    public void initialize(UsernameUnique constraintAnnotation) {

    }


    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if(userProfileRepository.findByUsername(username).isPresent()) {
            throw new UserValidateException("username is duplicated");
        }
        return true;
    }
}

package org.example.springboot.service.validate;

import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.userprofile.UserProfileRepository;
import org.example.springboot.exception.user.UserSignUpValidateException;
import org.example.springboot.web.dto.SignUpRequestDto;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


@RequiredArgsConstructor
@Component
public class SignUpValidator implements ConstraintValidator<SignUpValid, Object> {
    private final UserProfileRepository userProfileRepository;

    @Override
    public void initialize(SignUpValid constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        SignUpRequestDto dto = (SignUpRequestDto)value;
        if(userProfileRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new UserSignUpValidateException("already exists username");
        } else if(!dto.getPassword().equals(dto.getPassword2())) {
            throw new UserSignUpValidateException("two password is different");
        }
        return true;
    }

}

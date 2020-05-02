package org.example.springboot.service.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD})
@Constraint(validatedBy = PasswordValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValid {
    String message() default "two password is different";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

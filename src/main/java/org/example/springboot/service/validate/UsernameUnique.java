package org.example.springboot.service.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD})
@Constraint(validatedBy = UsernameDuplicationValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface UsernameUnique {
    String message() default "username is duplicated";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

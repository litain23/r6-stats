package me.r6_search.service.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE})
@Constraint(validatedBy = SignUpValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface SignUpValid {
    String message() default "sign up request is wrong";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

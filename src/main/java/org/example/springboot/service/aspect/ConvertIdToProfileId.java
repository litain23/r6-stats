package org.example.springboot.service.aspect;


import java.lang.annotation.ElementType;
import java.lang.annotation.Target;


@Target(ElementType.METHOD)
public @interface ConvertIdToProfileId {

}

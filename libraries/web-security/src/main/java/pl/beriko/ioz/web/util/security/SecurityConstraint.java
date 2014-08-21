package pl.beriko.ioz.web.util.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Project: Suggestion List
 * User: Octavian Rusu
 * Date: 06/12/2010
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SecurityConstraint {

    PermissionType[] permissions() default {};

}

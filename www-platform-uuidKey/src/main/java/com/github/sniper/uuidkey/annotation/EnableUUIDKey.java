package com.github.sniper.uuidkey.annotation;


import com.github.sniper.uuidkey.UuidKeyConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.FIELD})
@Import(UuidKeyConfiguration.class)
public @interface EnableUUIDKey {
}

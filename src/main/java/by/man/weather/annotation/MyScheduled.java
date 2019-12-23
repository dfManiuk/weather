package by.man.weather.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(MySchedules.class)
@Documented
public @interface MyScheduled {
	
	String CRON_DISABLED = ScheduledTaskRegistrar.CRON_DISABLED;
	
	String cron() default "";
	
	String zone() default "";
	
	boolean myDelay() default false;

	String fixedDelayString() default "";

	long fixedRate() default -1;

	String fixedRateString() default "";

	long initialDelay() default -1;

	String initialDelayString() default "";

}

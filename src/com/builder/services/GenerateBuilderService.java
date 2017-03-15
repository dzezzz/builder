package com.builder.services;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.stream.Stream;

import com.builder.dto.BuilderData;
import com.builder.dto.BuilderField;
import com.intellij.util.ReflectionUtil;

/**
 * @author marcinjaz
 */
public class GenerateBuilderService {

	public BuilderData generateBuilder(Class<?> clazz) {
		Collection<Field> declaredFields = getAllFields(clazz);
		BuilderData builderData = new BuilderData(clazz.getSimpleName());
		for (Field declaredField : declaredFields) {
			String type = declaredField.getType().getSimpleName();
			BuilderField builderField = new BuilderField(declaredField.getName(), type);
			if (hasNotNullAnnotation(declaredField)) {
				builderData.addMandatoryField(builderField);
			} else {
				builderData.addOptionalField(builderField);
			}
		}
		return builderData;
	}

	private Collection<Field> getAllFields(Class<?> clazz) {
		return ReflectionUtil.collectFields(clazz);
		//TODO MJ this uses utils from intellij, should be safe, right?
		//return clazz.getDeclaredFields();
	}

	private boolean hasNotNullAnnotation(Field declaredField) {
		// this can cause problems if you put any annotation that has notNull in the name
		// sometimes you may not want to fill all not nulls - eg in the unit test
		Annotation[] annotations = declaredField.getDeclaredAnnotations();
		return Stream.of(annotations).map(Object::toString).anyMatch(annotation -> annotation.contains("NotNull"));
	}

}

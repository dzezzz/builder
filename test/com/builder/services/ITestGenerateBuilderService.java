package com.builder.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;

import org.junit.jupiter.api.Test;

import com.builder.dto.BuilderData;

/**
 * @author marcinjaz
 */
class ITestGenerateBuilderService {

	private GenerateBuilderService generateBuilderService = new GenerateBuilderService();

	@Test
	public void shouldLoadClass() throws MalformedURLException, ClassNotFoundException {
		//given
		Class<?> clazz = Class.forName("com.builder.services.dto.TestDto");
		//when
		BuilderData builderData = generateBuilderService.generateBuilder(clazz);
		//then
		assertNotNull(builderData);
	}

	@Test
	public void shouldLoadClassWithCorrectName() throws MalformedURLException, ClassNotFoundException {
		//given
		Class<?> clazz = Class.forName("com.builder.services.dto.TestDto");
		//when
		BuilderData builderData = generateBuilderService.generateBuilder(clazz);
		//then
		assertTrue(builderData.getName().equals("TestDto"));
	}

	@Test
	public void shouldLoadClassWithInheritance() throws MalformedURLException, ClassNotFoundException {
		//given
		Class<?> clazz = Class.forName("com.builder.services.dto.TestDto");
		//when
		BuilderData builderData = generateBuilderService.generateBuilder(clazz);
		//then
		assertTrue(builderData.getOptionalFields().stream().anyMatch(f -> f.getName().equals("parent")));
	}

}
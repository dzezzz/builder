package com.builder.services;

import static org.junit.Assert.assertNotNull;

import java.net.MalformedURLException;

import org.junit.jupiter.api.Test;

/**
 * @author marcinjaz
 */
class ITestReadClassService {

	private ReadClassService readClassService = new ReadClassService();

	@Test
	public void shouldLoadClass() throws MalformedURLException, ClassNotFoundException {
		//given
		String content = "public class TestDto {\n" +
				"\n" +
				"    private int number;\n" +
				"    private String name;\n" +
				"    private boolean ok;\n" +
				"    private float money;\n" +
				"\n" +
				"    public int getNumber() {\n" +
				"        return number;\n" +
				"    }\n" +
				"\n" +
				"    public void setNumber(int number) {\n" +
				"        this.number = number;\n" +
				"    }\n" +
				"\n" +
				"    public String getName() {\n" +
				"        return name;\n" +
				"    }\n" +
				"\n" +
				"    public void setName(String name) {\n" +
				"        this.name = name;\n" +
				"    }\n" +
				"\n" +
				"    public boolean isOk() {\n" +
				"        return ok;\n" +
				"    }\n" +
				"\n" +
				"    public void setOk(boolean ok) {\n" +
				"        this.ok = ok;\n" +
				"    }\n" +
				"\n" +
				"    public float getMoney() {\n" +
				"        return money;\n" +
				"    }\n" +
				"\n" +
				"    public void setMoney(float money) {\n" +
				"        this.money = money;\n" +
				"    }\n" +
				"}";
		String className = "TestDto";
		//when
		Class aClass = readClassService.loadClass(content, className);
		//then
		assertNotNull(aClass);
	}

}
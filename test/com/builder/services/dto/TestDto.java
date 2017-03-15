package com.builder.services.dto;

/**
 * Created by marcinjaz on 15/03/17.
 */
public class TestDto extends AbstractTestDto {

	@NotNull
	private int number;
	private String name;
	private boolean ok;
	private float money;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public float getMoney() {
		return money;
	}

	public void setMoney(float money) {
		this.money = money;
	}

}
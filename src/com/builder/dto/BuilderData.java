package com.builder.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author marcinjaz
 */
public class BuilderData {

	private String name;
	private List<BuilderField> mandatoryFields = new ArrayList<>();
	private List<BuilderField> optionalFields = new ArrayList<>();

	public BuilderData(String simpleName) {
		name = simpleName;
	}

	public String getName() {
		return name;
	}

	public void addMandatoryField(BuilderField field) {
		mandatoryFields.add(field);
	}

	public void addOptionalField(BuilderField field) {
		optionalFields.add(field);
	}

	public List<BuilderField> getMandatoryFields() {
		return Collections.unmodifiableList(mandatoryFields);
	}

	public List<BuilderField> getOptionalFields() {
		return Collections.unmodifiableList(optionalFields);
	}

	@Override
	public String toString() {
		return "BuilderData{" +
				"mandatoryFields=" + mandatoryFields +
				", optionalFields=" + optionalFields +
				'}';
	}

	public String getStringImplementation() {
return "";
	}
}

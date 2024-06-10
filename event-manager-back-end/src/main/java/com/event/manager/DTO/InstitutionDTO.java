package com.event.manager.DTO;

import javax.validation.constraints.NotBlank;

public class InstitutionDTO {

	private Integer id;

    @NotBlank(message = "Parameter Name is mandatory")
    private String name;

    @NotBlank(message = "Parameter Type is mandatory")
    private String type;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}

package com.example;


import io.dropwizard.Configuration;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FirebaseConfiguration extends Configuration
{
	@NotEmpty
	@JsonProperty
	private String hostname;

	public String getHostname()
	{
		return hostname;
	}

	public void setHostname(String hostname)
	{
		this.hostname = hostname;
	}
}

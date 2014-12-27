package com.example;

import javax.inject.Named;

import com.firebase.client.Firebase;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class MyModule extends AbstractModule {
	
	@Override
	protected void configure() {

	}
	
	@Provides
	@Named("template")
	public String provideTemplate(MyAppConfiguration myAppConfiguration) {
		return myAppConfiguration.getTemplate();
	}

	@Provides
	@Named("defaultName")
	public String provideDefaultName(MyAppConfiguration myAppConfiguration) {
		return myAppConfiguration.getDefaultName();
	}

	@Provides
	@Named("firebase")
	public Firebase provideFirebase(MyAppConfiguration MyAppConfiguration) {
		return new Firebase(MyAppConfiguration.getFirebase().getHostname());
	}
}
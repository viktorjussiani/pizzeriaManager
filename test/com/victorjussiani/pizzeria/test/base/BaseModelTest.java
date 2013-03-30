package com.victorjussiani.pizzeria.test.base;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import play.db.jpa.JPA;
import play.test.Helpers;

public class BaseModelTest {

	public static play.test.TestServer app;

	@BeforeClass
	public static void startApp() throws IOException {
		app = Helpers.testServer(3333, Helpers.fakeApplication(Helpers.inMemoryDatabase()));
		Helpers.start(app);
	}

	@AfterClass
	public static void stopApp() {
		Helpers.stop(app);
	}

	protected void setRollBackOnly() {
		JPA.em().getTransaction().setRollbackOnly();
	}
}

package com.dbrigade;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBUtil {
	public static final EntityManagerFactory emf =
			Persistence.createEntityManagerFactory("BrigadePU");

	public DBUtil () {}
}

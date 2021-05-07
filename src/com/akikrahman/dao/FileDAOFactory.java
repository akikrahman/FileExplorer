package com.akikrahman.dao;

public class FileDAOFactory {
	
	public static FileDAO getFileDAOInstance() {
		return new FileDAOImpl();
	}

}

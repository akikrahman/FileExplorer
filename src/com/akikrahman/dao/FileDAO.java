package com.akikrahman.dao;

import com.akikrahman.model.File;

public interface FileDAO extends AutoCloseable {
	
	public boolean saveFile(File file);

}

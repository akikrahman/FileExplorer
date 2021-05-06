package com.akikrahman.dao;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.akikrahman.model.File;

import oracle.jdbc.pool.OracleDataSource;

public class FileDAOImpl implements FileDAO {

	OracleDataSource ods;
	Connection conn;
	private final String COMMA = ",";
	private final String SINGLE_QOUTE = "'";

	public static FileDAOImpl getFileDAOImpl() {
		return new FileDAOImpl();
	}

	public FileDAOImpl() {

		try {

			FileReader reader = new FileReader("application.properties");
			Properties p = new Properties();
			p.load(reader);

			ods = new OracleDataSource();
			ods.setURL(p.getProperty("dburl"));
			ods.setUser(p.getProperty("dbusername"));
			ods.setPassword(p.getProperty("dbpassword"));
			conn = ods.getConnection();

		} catch (SQLException ex) {
			while (ex != null) {
				System.out.println("Error Code:" + ex.getErrorCode());
				System.out.println("Message:   " + ex.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause:" + t);
					t = t.getCause();
				}
				ex = ex.getNextException();
			}
		} catch (IOException ioe) {
			System.out.println("Message:   " + ioe.getMessage());
		}
	}

	public boolean saveFile(File file) {
		boolean result = true;

		String updatedDate = file.getDate().replace('T', ' ').replace('Z', ' ').trim();

		try {
			String statement = "INSERT INTO files (path,drive,name,extension,file_size,modified_date) " + "VALUES ("
					+ SINGLE_QOUTE + file.getPath() + SINGLE_QOUTE + COMMA + SINGLE_QOUTE + file.getDrive()
					+ SINGLE_QOUTE + COMMA + SINGLE_QOUTE + file.getName() + SINGLE_QOUTE + COMMA + SINGLE_QOUTE
					+ file.getExtension() + SINGLE_QOUTE + COMMA + file.getSize() + COMMA + " to_date('" + updatedDate
					+ "', 'yyyy-mm-dd hh24:mi:ss')" + " )";
			PreparedStatement stmt = conn.prepareStatement(statement);
			stmt.executeQuery();

		} catch (SQLException ex) {

			result = false;

			while (ex != null) {
				System.out.println("SQLState:  " + ex.getSQLState());
				System.out.println("Error Code:" + ex.getErrorCode());
				System.out.println("Message:   " + ex.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause:" + t);
					t = t.getCause();
				}
				ex = ex.getNextException();
			}
		}

		return result;
	}
}

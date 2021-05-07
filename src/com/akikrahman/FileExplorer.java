package com.akikrahman;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

import com.akikrahman.dao.FileDAO;
import com.akikrahman.dao.FileDAOFactory;
import com.akikrahman.dao.FileDAOImpl;
import com.akikrahman.util.ExploreFileTree;

public class FileExplorer {
	 public static void main(String[] args) {
	        if (args.length < 1) {
	            System.out.println("Usage: FileExplorer <starting directory>");
	            System.exit(-1);
	        }
	        Path path = Paths.get(args[0]);
	        if (!Files.isDirectory(path)) {
	            System.out.println(args[0] + " must be a directory!");
	            System.exit(-1);
	        }
	        try(FileDAO fdao = FileDAOFactory.getFileDAOInstance()) {
	        		        	
	            Files.walkFileTree(path, new ExploreFileTree(fdao));
	            
	        } catch (IOException e) {
	            System.out.println("Exception: " + e);
	        } catch(Exception e) {
	        	 System.out.println("Error closing the connection to the database: " + e);
	             System.exit(-1);
	        }
	    }
}

package com.akikrahman.util;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystemLoopException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardCopyOption.*;
import static java.nio.file.FileVisitResult.*;
import java.nio.file.attribute.BasicFileAttributes;

import com.akikrahman.dao.FileDAO;
import com.akikrahman.model.File;

public class ExploreFileTree implements FileVisitor<Path> {

	FileDAO fdao;
	
    public ExploreFileTree(FileDAO fdao) {
    	this.fdao = fdao;
    }

    //Print information about the root path
    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attr) {

        return CONTINUE;
    }

    //Print information about each type of file.
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
    	
       /*Useful debug code
        *  System.out.print("visitFile: ");
        
        if (attr.isSymbolicLink()) {
            System.out.format("Symbolic link: %s ", file);
        } else if (attr.isRegularFile()) {
            System.out.format("Regular file: %s ", file);
        } else {
            System.out.format("Other: %s ", file);
        }
        System.out.println("(" + attr.lastModifiedTime() + " modified time "+ attr.size() + " bytes)");
        */
    	
    	String strFile = file.toString();
    	
        String extension = "";
        
        if(strFile.contains(".")) {
        	extension = strFile.substring(strFile.lastIndexOf('.')+1, strFile.length());
        	if(extension.length()>20) extension = ""; //an invalid extension found, more than 20 chars
        }
        
        String name="";
        
        if(strFile.contains("\\") && strFile.contains(".")) {
        	name = strFile.substring(strFile.lastIndexOf('\\')+1,strFile.lastIndexOf('.'));
        }
        else if(strFile.contains("\\")) {
        	//there is no file extension for this file
        	name = strFile.substring(strFile.lastIndexOf('\\')+1,strFile.length());
        }
        	        
        String filePath = strFile.substring(0, strFile.lastIndexOf('\\'));
        String datetime = attr.lastModifiedTime().toString().substring(0,19); //should be max 19 chars
        char drive = strFile.substring(0,2).charAt(0);
        
        if(!name.isEmpty() && !extension.isEmpty() ) {
               File newFile = new File(filePath, name, drive , extension, attr.size(), datetime);
               fdao.saveFile(newFile);
        }
        

        
        return CONTINUE;
    }

    //Print each directory visited.
    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) {

        return CONTINUE;
    }

    //If there is some error accessing the file, let the user know.
    //If you don't override this method and an error occurs, an IOException 
    //is thrown.
    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        System.out.print("vistiFileFailed: ");
        System.err.println(exc);
        return CONTINUE;
    }
}
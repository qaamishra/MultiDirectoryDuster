package com.DustOnlyFiles;

// only deletes file and all dir untouched if(file.list().length==1) when length==1 at all occurrence
//This is main file packed in jar
//To Test this program make sure your directories has more than one file(for e.g two txt files into it)
//You can have multiple directories and multiple files inside it.
//All the files will be deleted except the directories
//This Program Still needs improvisation

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Properties;
 
public class DustAllDirContents
{
   
 
    public static void main(String[] args) throws IOException, FileNotFoundException
    {	
    	
    	//Include properties file reading of delete directory
    	Properties prop = new Properties();
    	prop.load(new FileInputStream("config.properties"));
    	String DirToClean = prop.getProperty("DirToClean");
    	
    	String[] value=DirToClean.split(",");
    	
    	ArrayList<String> stringList = new ArrayList<String>(Arrays.asList(value));
    	Iterator<String> itrlistname=stringList.iterator();
    	
    	while(itrlistname.hasNext()){
    		
    		String SRC_FOLDER = itrlistname.next();
    		File directory = new File(SRC_FOLDER);
        	DustAllDirContents dustAllDirContents=new DustAllDirContents();
        	dustAllDirContents.deleteDirContentsOnly(directory);
    		
    	}
    	
    }
    
    
    public void deleteDirContentsOnly(File directory){

    	//make sure directory exists
    	if(!directory.exists()){
 
           System.out.println("Directory does not exist.");
           System.exit(0);
 
        }else{
 	           try{
	 
	               delete(directory);
	 
	           }catch(IOException e){
	               e.printStackTrace();
	               System.exit(0);
	           }
        }
 
    	System.out.println("Done");
    }
 
    public static void delete(File file)throws IOException{
 
    	if(file.isDirectory()){
 
    		//directory is empty, then delete it
    		if(file.list().length==0){
 
    		   file.delete();
    		   System.out.println("File is deleted : " 
                                                 + file.getAbsolutePath());
 
    		}else{
 
    		   //list all the directory contents
        	   String files[] = file.list();
 
        	   for (String temp : files) {
        	      //construct the file structure
        	      File fileDelete = new File(file, temp);
 
        	      //recursive delete
        	      delete(fileDelete);
        	   }
 
        	   //check the directory again, if empty then delete it
        	   if(file.list().length==1){
           	     file.delete();
        	     System.out.println("File is deleted : " 
                                                  + file.getAbsolutePath());
        	   }
    		}
 
    	}else{
    		//if file, then delete it
    		file.delete();
    		System.out.println("File is deleted : " + file.getAbsolutePath());
    	}
    
    }
    
    
    
}
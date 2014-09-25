package fr.ensicaen.index;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import fr.ensicaen.utils.FileUtils;
import fr.ensicaen.utils.SaltonUtils;

/**
 * main class of the index construction program
 * @author pellegrini and marguerite
 *
 */
public class IndexCommand {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		List<File> files=new ArrayList<File>();
		if(args.length==0){
			//dialog interface
			System.out.println("Please enter one file or folder to index by line");
			System.out.println("To launch the indexation, enter run");
			String path="";
			do{
				path=sc.nextLine();
				File file=new File(path);
				if(file.isFile()){
					files.add(file);
				}else if(file.isDirectory()){
					files.addAll(Arrays.asList(file.listFiles()));
				}			
			}while(!path.equalsIgnoreCase("run"));
		}else{
			//command line interface
			for(String filesPath:args){
				File file=new File(filesPath);
				if(file.isFile()){
					files.add(file);
				}else if(file.isDirectory()){
					files.addAll(Arrays.asList(file.listFiles()));
				}	
			}
		}
		System.out.println("Construction of the index in progress ...");
		IndexFactory indexBuilder=new IndexFactory();
		Map<String,String> fileContents=new HashMap<String,String>();
		try {
			for(File file:files){
				//the corpus is encoded in ISO-8859-1 but a method for UTF-8 is also provided in FileUtils
				fileContents.put(file.getName(), FileUtils.readWholeFileISO(file.getPath()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Index index=indexBuilder.buildIndex(fileContents);
		IndexHandler indexHandler=new IndexHandler();
		indexHandler.setIndex(index);
		try {
			FileUtils.writeFile("./index/index.txt", indexHandler.getStrIndex(), "UTF-8");
			FileUtils.writeFile("./index/salton.txt", SaltonUtils.getSaltonPartialCoef(indexHandler.getIndex().getIndexMap()), "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println("DONE");
	}

}

package fr.ensicaen.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
/**
 * class that saves or opens a text file
 * @author pellegrini and marguerite
 *
 */
public class FileUtils {
	/**
	 * this method is used to read the index from the filesystem
	 * @param path
	 * @param format
	 * @return
	 * @throws IOException
	 */
	public static List<String> readFileLines(String path,String format) throws IOException{
		return readFileLines(new File(path),format);
	}
	
	public static List<String> readFileLines(File file,String format) throws IOException{
		String line;
		BufferedReader reader=null;
		List<String> lines=new ArrayList<String>();
		try{
			reader  = new BufferedReader(
					new InputStreamReader(new FileInputStream(file),format));
			while((line=reader.readLine())!=null){
				lines.add(line);
			}
		}finally{
			if(reader!=null){
				reader.close();
			}
		}
		return lines;
	}

	/**
	 * this method read the whole text at once (used when reading the corpus)
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static String readWholeFileISO(String path) throws IOException{
		return new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.ISO_8859_1);
	}
	
	public static String readWholeFileUTF8(String path) throws IOException{
		return new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
	}

	/**
	 * write a String in a file
	 * @param path
	 * @param content
	 * @param format
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public static void writeFile(String path, String content,String format) throws FileNotFoundException, UnsupportedEncodingException{
		PrintWriter writer = null;
		try{
			writer = new PrintWriter(path, format);
			writer.print(content);
		}finally{
			if(writer!=null){
				writer.close();
			}
		}
		
	}

	/**
	 * write line by line several strings in a file
	 * @param path
	 * @param contents
	 * @param format
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public static void writeFile(String path, List<String> contents,String format) throws FileNotFoundException, UnsupportedEncodingException{
		PrintWriter writer =null;
		try{
			writer = new PrintWriter(path, format);
			for(String content:contents){
				writer.println(content);
			}
		}finally{
			if(writer!=null){
				writer.close();
			}
		}
	}

}

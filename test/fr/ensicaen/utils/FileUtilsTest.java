package fr.ensicaen.utils;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.junit.Test;

import fr.ensicaen.utils.FileUtils;


public class FileUtilsTest {

	@Test
	public void test() {
		String fileContent;
		try {
			fileContent = FileUtils.readWholeFileISO("./corpus/Ecoutez_la_chanson_bien_douce.txt");
			System.out.println(fileContent);
			FileUtils.writeFile("./testFiles/test1.txt", fileContent,StandardCharsets.UTF_8.displayName());
			List<String> lines=FileUtils.readFileLines("./corpus/A_la_promenade.txt", StandardCharsets.ISO_8859_1.displayName());
			System.out.println(lines);
			FileUtils.writeFile("./testFiles/test2.txt", lines, StandardCharsets.UTF_8.displayName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
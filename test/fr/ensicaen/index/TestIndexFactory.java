package fr.ensicaen.index;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import fr.ensicaen.index.IndexFactory;

import org.junit.Test;

public class TestIndexFactory {
	String text=" a b  il Bonjour comment ça ça va ?\n il il fait beau aujourd'hui  huio  \n\t\rHello";

	@Test
	public void test() {
		IndexFactory factory=new IndexFactory();
		Map<String,String> files=new HashMap<String,String>();
		files.put("test1.txt", text);
		files.put("test2.txt", text+" arbre maison maison");
		factory.buildIndex(files);
	}

}

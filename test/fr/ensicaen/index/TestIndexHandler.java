/**
 *
 */
package fr.ensicaen.index;

import static org.junit.Assert.*;

import java.text.ParseException;

import fr.ensicaen.index.IndexHandler;

import org.junit.Test;

/**
 * @author pellegrini
 *
 */
public class TestIndexHandler {

	final String strIndex="README.txt\tcirconvolution\t3.14\nREADME.txt\tdiamant\t2.71\nMakefile\tgcc\t1.1111\nmain.c\tprintf\t1" ;

	@Test
	public void testIndexHandler() {
		IndexHandler ih=new IndexHandler();
		try {
			ih.initializeFromString(strIndex);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(ih.toString());
	}

}

package fr.ensicaen.utils;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * class in charge of the salton coefficient's terms
 * that can be found during the index' construction
 * @author pellegrini and marguerite
 *
 */
public class SaltonUtils {
	public static String getSaltonPartialCoef(Map<String,Map<String,Float>> index){
		String res="";
		//pre-computation during index building of left-term (without the square root) of the salton coefficient's denominator
		//
		for(String doc:index.keySet()){
			float sum=0;
			for(String word:index.get(doc).keySet()){
				float value=index.get(doc).get(word).floatValue();
				sum+=value*value;
			}
			res+=doc+"\t"+sum+"\n";
		}
		return res;
	}

	public static Map<String,Float> getSaltonCoefFromString(String strSaltons) throws ParseException{
		String[] lines=strSaltons.split("\n");
		Map<String,Float> saltonCoefs=new HashMap<String,Float>();
		for(String line:lines){
			String[] lineParts=line.split("\t");
			if(lineParts.length!=2){
				throw new ParseException("Invalid String Format", lineParts.length);
			}
			String doc=lineParts[0];
			String strCoef=lineParts[1];
			saltonCoefs.put(doc,new Float(strCoef) );
		}
		return saltonCoefs;
	}
}

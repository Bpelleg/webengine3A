/**
 * 
 */
package fr.ensicaen.index;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author pellegrini
 *
 */
public class IndexHandler {
	/**
	 * data representation doc->mot->score
	 */
	private Map<String,Map<String,Float>> index;
	int nbDoc=0;
	
	/**
	 * default constructor
	 */
	public IndexHandler(){
		index=new HashMap<String,Map<String,Float>>();
	}
	
	/**
	 * constructor that initialize the index from the given String 
	 * @param strIndex
	 */
	public IndexHandler(String strIndex){
		index=new HashMap<String,Map<String,Float>>();
		try{
			initializeFromString(strIndex);
		}catch(ParseException e){
			//TODO handle this exception
		}
	}
	
	@Override
	public String toString(){
		String output="";
		for (String doc : index.keySet()) {
		    Map<String,Float> docAndScore=index.get(doc);
		    for(String word : docAndScore.keySet()){
		    	Float score=docAndScore.get(word);
		    	output+=doc+"\t"+word+"\t"+score.toString()+"\n";
		    }
		}
		return output;
	}
	
	public void initializeFromString(String strIndex) throws ParseException{
		String[] lines=strIndex.split("\n");
		nbDoc=lines.length;
		for(String line:lines){
			String[] lineParts=line.split("\t");
			if(lineParts.length!=3){
				throw new ParseException("Invalid String Format", lineParts.length);
			}
			String doc=lineParts[0];
			String word=lineParts[1];
			Float score=Float.valueOf(lineParts[2]);
			if(!index.containsKey(doc)){
				index.put(doc, new HashMap<String,Float>());
			}
			index.get(doc).put(word,score);
		}
	}
	
	public Index getIndex(){
		Index i=new Index();
		i.setIndex(index);
		i.setNbDoc(nbDoc);
		return i;
	}
}

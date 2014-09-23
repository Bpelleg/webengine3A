/**
 * 
 */
package fr.ensicaen.index;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author pellegrini
 *
 */
public class IndexFactory {
	/**
	 * 
	 */
	public IndexFactory(){
		
	}

	/**
	 * 
	 * @param filesContents Map : name -> content
	 */
	
	public Index buildIndex(Map<String,String> files){
		Map<String,Map<String,Float>> indexMap=new HashMap<String,Map<String,Float>>();
		Map<String,Integer> wordOccurencesInFiles=new HashMap<String,Integer>();
		int nbFiles=files.keySet().size();
		
		for(String doc:files.keySet()){
			String fileContent=files.get(doc);
			Map<String,Float> docIndex=new HashMap<String,Float>();
			//get all the words in an array
			//the regular expression detects punctuation and white blanks in the text
			String [] words=fileContent.split("[\\p{Punct}\\s]+");
			for(String word:words){
				if(isValid(word)){
					if(!docIndex.containsKey(word)){
						docIndex.put(word, Float.valueOf(0));
					}
					docIndex.put(word, docIndex.get(word)+1);
				}
			}
			
			for(String word:docIndex.keySet()){
				//update wordOccurrencesInFiles (dfi)
				if(!wordOccurencesInFiles.containsKey(word)){
					wordOccurencesInFiles.put(word, Integer.valueOf(0));
				}
				wordOccurencesInFiles.put(word, wordOccurencesInFiles.get(word)+1);
			}
			
			// at this step the scores are the number of words' occurences in the file
		indexMap.put(doc, docIndex);
		}
		//computes the TF-IDFs
		for(String doc:indexMap.keySet()){
			Map<String,Float> docMap=indexMap.get(doc);
			for(String word:indexMap.get(doc).keySet()){
				float tfidf=tfIdf(docMap.get(word), nbFiles, wordOccurencesInFiles.get(word));
				docMap.put(word, Float.valueOf(tfidf));
			}
		}
		Index index=new Index();
		index.setIndex(indexMap);
		index.setNbDoc(nbFiles);
		return index;
	}

	private boolean isValid(String word) {
		// TODO Auto-generated method stub
		if(word.length()<3){
			return false;
		}
		return true;
	}
	
	private float tfIdf(float tfi,int N, int dfi){
		float log=(float) Math.log(((double)N)/((double)dfi));
		return tfi*log;
	}
	

}

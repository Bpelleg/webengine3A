/**
 *
 */
package fr.ensicaen.index;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fr.ensicaen.utils.FileUtils;

/**
 * class in charge of the index' construction
 * @author pellegrini and marguerite
 *
 */
public class IndexFactory {
	private Set<String> antidico;

	public IndexFactory(){
		antidico=new HashSet<String>();
		try {
			String antidicosContent=FileUtils.readWholeFileUTF8("./antidico/antidico.txt");
			antidico.addAll(Arrays.asList( antidicosContent.split("\n")));
		} catch (IOException e) {
			antidico=null;
			e.printStackTrace();
		}
	}

	/**
	 * method that constructs the index
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

	/**
	 * check whether the given word is meaningful or not
	 * @param word
	 * @return
	 */
	private boolean isValid(String word) {
		if(word.length()<3){
			return false;
		}
		if(antidico.contains(word)){
			return false;
		}
		return true;
	}

	/**
	 * computation of the TF-IDF
	 * @param tfi
	 * @param N
	 * @param dfi
	 * @return
	 */
	private float tfIdf(float tfi,int N, int dfi){
		float log=(float) Math.log(((double)N)/((double)dfi));
		return tfi*log;
	}


}

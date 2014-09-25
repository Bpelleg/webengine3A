package fr.ensicaen.index;

import java.util.Map;

/**
 * this class contains the representation of the index
 * @author pellegrini and marguerite
 *
 */
public class Index {
		private Map<String,Map<String,Float>> index;
		private int nbDoc;


		public Index(){
		}


		public Map<String, Map<String, Float>> getIndexMap() {
			return index;
		}


		public int getNbDoc() {
			return nbDoc;
		}


		public void setIndex(Map<String, Map<String, Float>> index) {
			this.index = index;
		}

		public void setNbDoc(int nbDoc){
			this.nbDoc=nbDoc;
		}
		
}

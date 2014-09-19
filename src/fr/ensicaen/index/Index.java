package fr.ensicaen.index;

import java.util.HashMap;
import java.util.Map;


public class Index {
		private Map<String,Map<String,Float>> index;
		private int nbDoc;


		public Index(){
			index = new HashMap<String, Map<String, Float>>();

			index.put("Cauchemar.txt", new HashMap<String, Float>());
			index.get("Cauchemar.txt").put("toujours", 2.11f);
			index.get("Cauchemar.txt").put("jamais", 5.7f);
		}


		public Map<String, Map<String, Float>> getIndex() {
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

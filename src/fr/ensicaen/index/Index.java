package fr.ensicaen.index;

import java.util.Map;


public class Index {
		private Map<String,Map<String,Float>> index;
		private int nbDoc;


		public Index(){
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

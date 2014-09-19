/*
 * ENSICAEN
 * 6 Boulevard Marechal Juin
 * F-14050 Caen Cedex
 *
 * This file is owned by ENSICAEN students.
 * No portion of this code may be reproduced,
 * copied or revised without written permission
 * of the authors.
 */

package fr.ensicaen.search;

import fr.ensicaen.index.Index;

import java.util.HashMap;
import java.util.Map;

/**
 * This file is used to represent the query of the user.
 * @author Jean MARGUERITE <jean.marguerite@ecole.ensicaen.fr>
 * @author Baptiste PELLEGRINI <baptiste.pellegrini@ecole.ensicaen.fr>
 */
public class Query {
    private String mText;
    private Map<String, Boolean> mVector;
    private Index mIndex;

    public Query(String text, Index index) {
        mText = text;
        mIndex = index;
        buildVector();
        browseQuery();
    }

    private void buildVector() {
        mVector = new HashMap<String, Boolean>();

        for (Map.Entry<String, Map<String, Float>> document : mIndex.getIndex()
                .entrySet()) {
            for (Map.Entry<String, Float> word : document.getValue()
                    .entrySet()) {
                mVector.put(word.getKey(), false);
            }
        }
    }

    private void browseQuery() {
        for (String word : mText.split("[\\p{Punct}\\s]+")) {
            if (mVector.containsKey(word)) {
                mVector.replace(word, true);
            }
        }
    }

    public float computeSaltonCoefficient(String document) {
        float denominator, numerator;


        //return numerator / denominator;
    }

    @Override
    public String toString() {
        return mText;
    }
}

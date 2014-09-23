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

import java.util.HashMap;
import java.util.Map;

import fr.ensicaen.index.Index;

/**
 * This file is used to represent the query of the user.
 * @author Jean MARGUERITE <jean.marguerite@ecole.ensicaen.fr>
 * @author Baptiste PELLEGRINI <baptiste.pellegrini@ecole.ensicaen.fr>
 */
public class Query {
    private String mTextQuery;
    private Map<String, Boolean> mVector;
    private Index mIndex;

    public Query(Index index) {
        mIndex = index;
    }

    public void buildVector() {
        mVector = new HashMap<String, Boolean>();

        for (Map.Entry<String, Map<String, Float>> document : mIndex.getIndexMap()
                .entrySet()) {
            for (Map.Entry<String, Float> word : document.getValue()
                    .entrySet()) {
                mVector.put(word.getKey(), false);
            }
        }
    }

    public void browseQuery(String textQuery) {
        mTextQuery = textQuery;

        for (String word : mTextQuery.split("[\\p{Punct}\\s]+")) {
            if (mVector.containsKey(word)) {
                mVector.replace(word, true);
            }
        }
    }

    public float computeSaltonCoefficient(String textQuery, String document) {
        float denominator, numerator;

        buildVector();
        browseQuery(textQuery);

        numerator = computeNumerator(document);
        denominator = computeDenominator(document);

        return numerator / denominator;
    }

    private float computeNumerator(String document) {
        float sum;

        for (Map.Entry<String, Float> word : mIndex.getIndexMap().get(document)
                .entrySet()) {
            System.out.println(word.getValue());
        }

        return 0f;
    }

    private float computeDenominator(String document) {
        float sum;

        return 5f;
    }

    @Override
    public String toString() {
        return mTextQuery;
    }

    public Map<String, Boolean> getVector() {
        return mVector;
    }
}

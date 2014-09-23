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

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import fr.ensicaen.index.Index;

/**
 * This file is used to represent the query of the user.
 * @author Jean MARGUERITE <jean.marguerite@ecole.ensicaen.fr>
 * @author Baptiste PELLEGRINI <baptiste.pellegrini@ecole.ensicaen.fr>
 */
public class Query {
    private Index mIndex;
    private Set<String> mVector = new HashSet<String>();
    private String mTextQuery;

    public Query(Index index) {
        mIndex = index;
    }

    /**
     * Computes Salton coefficient of a request and a document.
     * @param textQuery Text of the query.
     * @param document Document used.
     * @return Salton coefficient.
     */
    public float computeSaltonCoefficient(String textQuery, String document) {
        float denominator, numerator;

        mTextQuery = textQuery;
        buildVector();

        numerator = computeNumerator(document);
        denominator = computeDenominator(document);

        return numerator / denominator;
    }

    /**
     * Build a vector with only words of the request presents in the index.
     */
    private void buildVector() {
        for (Map.Entry<String, Map<String, Float>> document : mIndex.getIndex()
                .entrySet()) {
            for (Map.Entry<String, Float> word : document.getValue().entrySet()) {
                for (String query : mTextQuery.split("[\\p{Punct}\\s]+")) {
                    if (query.equals(word.getKey())) {
                        mVector.add(query);
                    }
                }
            }
        }
    }

    /**
     * Computes numerator of Salton coefficient.
     * @param document Document used.
     * @return Numerator of Salton coefficient.
     */
    private float computeNumerator(String document) {
        float sum = 0f;

        for (String word : mTextQuery.split("[\\p{Punct}\\s]+")) {
            if (mIndex.getIndex().get(document).containsKey(word)) {
                sum += mIndex.getIndex().get(document).get(word);
            }
        }

        return sum;
    }

    /**
     * Computes denominator of Salton coefficient.
     * @param document Document used.
     * @return Denominator of Salton coefficient.
     */
    private float computeDenominator(String document) {
        float sum = 0f;

        sum += mVector.size();

        return (float) Math.sqrt(sum);
    }

    @Override
    public String toString() {
        return mTextQuery;
    }
}

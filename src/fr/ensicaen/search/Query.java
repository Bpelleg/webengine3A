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

import java.io.IOException;
import java.text.ParseException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import fr.ensicaen.index.Index;
import fr.ensicaen.utils.FileUtils;
import fr.ensicaen.utils.SaltonUtils;

/**
 * This file is used to represent the query of the user.
 * @author Jean MARGUERITE <jean.marguerite@ecole.ensicaen.fr>
 * @author Baptiste PELLEGRINI <baptiste.pellegrini@ecole.ensicaen.fr>
 */
public class Query {
    private Index mIndex;
    private Set<String> mVector = new HashSet<>();
    private String mTextQuery;
    private Map<String, Float> mPartial;

    public Query(Index index) {
        try {
            mPartial = SaltonUtils.getSaltonCoefFromString(
                    FileUtils.readWholeFileUTF8("./index/salton.txt"));
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        mIndex = index;
    }

    /**
     * Returns a map of the indexed documents with the corresponding Salton
     * coefficient.
     * @param textQuery Text of the query.
     * @return Map of the indexed documents.
     */
    public Map<String, Float> search(String textQuery) {
        Map<String, Float> relevantDocument = new HashMap<>();

        mTextQuery = textQuery;
        buildVector();

        for (Map.Entry<String, Map<String, Float>> document : mIndex.getIndexMap()
                .entrySet()) {
            relevantDocument.put(document.getKey(), computeSaltonCoefficient(
                    textQuery, document.getKey()));
        }

        return relevantDocument;
    }

    /**
     * Computes Salton coefficient of a request and a document.
     * @param textQuery Text of the query.
     * @param document Document used.
     * @return Salton coefficient.
     */
    public float computeSaltonCoefficient(String textQuery, String document) {
        float denominator, numerator;

        numerator = computeNumerator(document);
        denominator = computeDenominator(document);

        return numerator / denominator;
    }


    /**
     * Build a vector with only words of the request presents in the index.
     * TODO Mettre des valeurs plus réalistes pour le vecteur de la requête.
     */
    private void buildVector() {
        for (Map.Entry<String, Map<String, Float>> document : mIndex.getIndexMap()
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
            if (mIndex.getIndexMap().get(document).containsKey(word)) {
                sum += mIndex.getIndexMap().get(document).get(word);
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

        for(String word:mVector) {
            float r=computeWordPond(word);
            sum += r*r;
        }

        sum *= mPartial.get(document);

        return (float) Math.sqrt(sum);
    }

    private float computeWordPond(String word) {
        // TODO improve this method as an optimization
        return 1;
    }

    @Override
    public String toString() {
        return mTextQuery;
    }
}

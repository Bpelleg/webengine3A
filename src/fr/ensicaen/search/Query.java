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

    public Query(String text) {
        mText = text;
    }

    public void computeSaltonCoefficient() {

    }

    @Override
    public String toString() {
        return mText;
    }
}

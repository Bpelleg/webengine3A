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

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import fr.ensicaen.index.Index;
import fr.ensicaen.index.IndexFactory;

/**
 * This file is used to test the representation of the query of the user.
 * @author Jean MARGUERITE <jean.marguerite@ecole.ensicaen.fr>
 * @author Baptiste PELLEGRINI <baptiste.pellegrini@ecole.ensicaen.fr>
 */
@RunWith(JUnit4.class)
public class TestQuery {
    private Index mIndex;
    private IndexFactory mIndexFactory;
    private Query mQuery;

    @Before
    public void setUp() {
        Map<String, String> files = new HashMap<String, String>();
        mIndexFactory = new IndexFactory();

        files.put("test.txt", "matin midi soir");
        files.put("test2.txt", "le dernier matin");
        mIndex = mIndexFactory.buildIndex(files);

        mQuery = new Query(mIndex);
    }

    @Test
    public void testBuildVector() {
        mQuery.buildVector();
        assertEquals(mQuery.getVector().get("matin"), false);
        assertEquals(mQuery.getVector().get("midi"), false);
        assertEquals(mQuery.getVector().get("soir"), false);
    }

    @Test
    public void testBrowseQuery() {
        mQuery.buildVector();
        mQuery.browseQuery("matin midi");
        assertEquals(mQuery.getVector().get("matin"), true);
        assertEquals(mQuery.getVector().get("midi"), true);
        assertEquals(mQuery.getVector().get("soir"), false);
    }

    @Test
    public void testComputeSaltonCoefficient() {
        mQuery.computeSaltonCoefficient("le dernier matin", "test.txt");
    }
}

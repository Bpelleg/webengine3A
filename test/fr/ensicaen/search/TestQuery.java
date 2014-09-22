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
import fr.ensicaen.index.Index;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * This file is used to test the representation of the query of the user.
 * @author Jean MARGUERITE <jean.marguerite@ecole.ensicaen.fr>
 * @author Baptiste PELLEGRINI <baptiste.pellegrini@ecole.ensicaen.fr>
 */
@RunWith(JUnit4.class)
public class TestQuery {
    private Index mIndex;

    @BeforeClass
    public void setUp() {
        mIndex = new Index();
    }

    @Test
    public void testComputeSaltonCoefficient() {
        Query query = new Query("matin midi soir", mIndex);
        assertEquals(5, 5);
    }
}

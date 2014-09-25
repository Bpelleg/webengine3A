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
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

import fr.ensicaen.index.Index;
import fr.ensicaen.index.IndexHandler;
import fr.ensicaen.utils.FileUtils;

/**
 * Main search interface.
 * @author Jean MARGUERITE <jean.marguerite@ecole.ensicaen.fr>
 * @author Baptiste PELLEGRINI <baptiste.pellegrini@ecole.ensicaen.fr>
 */
public class Search {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException, ParseException {
        String indexContent = FileUtils.readWholeFileUTF8("./index/index.txt");
        IndexHandler indexHandler = new IndexHandler();
        indexHandler.initializeFromString(indexContent);
        Index index = indexHandler.getIndex();
        Query query = new Query(index);

        String request = "";

        for (int i = 0; i < args.length; ++i) {
            request += args[i] + " ";
        }

        Map<String, Float> relevantDocuments = query.search(request);

        @SuppressWarnings("unchecked")
        TreeSet sortedDocuments = new TreeSet(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Float val1 = (Float) ((Map.Entry) o1).getValue();
                Float val2 = (Float) ((Map.Entry) o2).getValue();

                if (val1 > val2) {
                    return -1;
                }

                return 1;
            }
        });

        sortedDocuments.addAll(relevantDocuments.entrySet());

        for (Iterator it = sortedDocuments.iterator(); it.hasNext() ;) {
            Map.Entry myMapEntry = (Map.Entry) it.next();
            System.out.println(myMapEntry.getKey() + " : " + myMapEntry.getValue());
        }
    }
}

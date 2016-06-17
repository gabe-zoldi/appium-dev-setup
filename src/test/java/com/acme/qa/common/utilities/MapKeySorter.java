/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.common.utilities;

import java.util.Comparator;
import java.util.Map;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
public class MapKeySorter implements Comparator<Map<String, String>> {

    String sortMapKey = null;

    public MapKeySorter(String inMapKey) {
        super();
        this.sortMapKey = inMapKey;
    }

    public int compare(Map<String, String> o1, Map<String, String> o2) {
        // these conditionals handle maps that don't have the key value
        // so a header row map will remain at the top of the list
        if ( !o1.containsKey(sortMapKey) && o2.containsKey(sortMapKey) ) {
            return -1;
        }
        else if ( o1.containsKey(sortMapKey) && !o2.containsKey(sortMapKey) ) {
            return 1;
        }
        else if ( !o1.containsKey(sortMapKey) && !o2.containsKey(sortMapKey) ) {
            return 0;
        }
        // both maps have the key, compare using that key
        else {
            return o1.get(sortMapKey).compareTo(o2.get(sortMapKey));
        }
    }

}
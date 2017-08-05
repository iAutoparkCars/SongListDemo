package com.mobile.songlist;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Steven on 8/5/2017.
 */

public class DataPasser<T> {

    private static final DataPasser passer = new DataPasser();

    public static DataPasser getInstance() {return passer;}

    Map<String, WeakReference<ArrayList<T>>> data = new HashMap<String, WeakReference<ArrayList<T>>>();

    void save(String id, ArrayList<T> T) {
        data.put(id, new WeakReference<ArrayList<T>>(T));
    }

    ArrayList<T> retrieve(String id) {
        WeakReference<ArrayList<T>> TWeakReference = data.get(id);
        return TWeakReference.get();
    }
}
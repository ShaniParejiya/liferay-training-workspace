package com.aixtor.training.liferay.portlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountryStateList {

    private static Map<String, List<String>> stateMap = new HashMap<>();

    static {

        // 1st country state
        ArrayList<String> India = new ArrayList<>();

        India.add("Madhya Pradesh");
        India.add("Delhi");
        India.add("Kolkata");
        India.add("Gujarat");
        India.add("Maharasthra");

        stateMap.put("India", India);

        // 2nd country state
        ArrayList<String> Australia = new ArrayList<>();

        Australia.add("Sydney");
        Australia.add("Melbourne");
        Australia.add("Perth");

        stateMap.put("Australia", Australia);

        // 3rd Country State

        ArrayList<String> Usa = new ArrayList<>();
        Usa.add("Texas");
        Usa.add("Alaska");
        Usa.add("California");

        stateMap.put("Usa", Usa);
    }

    public static List<String> getStates(String country) {
        return stateMap.get(country);
    }

    public static List<String> getCountries() {
        return new ArrayList<>(stateMap.keySet());
    }
}
package com.brandonespana.wowbattlepets.utility;

import com.brandonespana.wowbattlepets.pets.BattlePet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright 2015 Brandon Espana
 * <p/>
 * Instructor and TA have the right to build and evaluate the software package
 *
 * @author Brandon Espana Brandon.Espana@asu.edu
 * @version August 23, 2015
 */
public class JsonResponseParser {
    public List<BattlePet> retrieveListOfBattlePets(String jsonResponse) throws JSONException {
        List<BattlePet> battlePets = new ArrayList<>();

        JSONObject responseObject = new JSONObject(jsonResponse);
        JSONObject pets = responseObject.getJSONObject("pets");
        JSONArray petsCollected = pets.getJSONArray("collected");
        int numberOfPets = Integer.parseInt(pets.getString("numCollected"));

        System.out.println("Number of pets collected (calculated): " + petsCollected.length());
        System.out.println("Number of pets collected (from field): " + pets.getString("numCollected"));

        for (int i = 0; i < numberOfPets; i++){
            JSONObject pet = petsCollected.getJSONObject(i);
            JSONObject stats = pet.getJSONObject("stats");

            String petName = pet.getString("name");
            String creatureName = pet.getString("creatureName");
            int level = stats.getInt("level");

            BattlePet battlePet = new BattlePet(petName, creatureName,level);
            battlePets.add(battlePet);
        }


        return battlePets;
    }
}

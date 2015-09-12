package com.brandonespana.wowbattlepets.utility;

import android.content.Context;
import android.content.Intent;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.brandonespana.wowbattlepets.activities.MainActivity;
import com.brandonespana.wowbattlepets.activities.PetResultsActivity;
import com.brandonespana.wowbattlepets.pets.BattlePet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Copyright 2015 Brandon Espana
 * <p/>
 * Instructor and TA have the right to build and evaluate the software package
 *
 * @author Brandon Espana Brandon.Espana@asu.edu
 * @version August 22, 2015
 */
public class HttpCaller {
    private JsonResponseParser parser = new JsonResponseParser();
    public List<BattlePet> retriveListOfPets(String requestURI) {
        List<BattlePet> battlePets = null;

        return battlePets;
    }

    public void contactBattleNet(String requestURI, Context context, Response.Listener responseLister, Response.ErrorListener errorListener){
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest request = new StringRequest(Request.Method.GET, requestURI, responseLister, errorListener);
        queue.add(request);
    }
    public void makeHttpRequest(String requestURI, final Context context){
        System.out.println("ENTERED THE MAKE REQUEST METHOD SHOULD BE PRINTED OUT");
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = requestURI ;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("String length: " + response.length());
                        try {
                            //TODO: move this to the handle json response method, then later move it to a different class? maybe
//                            JSONObject jsonObject = new JSONObject(response);
//                            System.out.println("NAME IS: " + jsonObject.get("name").toString());
//                            System.out.println("HONORABLE KILLS IS: " + jsonObject.get("totalHonorableKills").toString());
                            parser.retrieveListOfBattlePets(response);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        System.out.println("STRING RESPONSE IS THIS: " + response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("ERROR OCURRED WITH TEH RESPONSE");
            }
        });

        queue.add(stringRequest);
    }

    private void sendResponse(String response) {

    }

    private void handleJsonResponse(JSONArray response) {
        System.out.println("JSON RESPONSE JSON RESPONSE");
        System.out.println(response.toString());
    }
}

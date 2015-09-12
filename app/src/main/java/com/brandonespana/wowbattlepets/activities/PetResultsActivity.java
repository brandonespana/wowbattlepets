package com.brandonespana.wowbattlepets.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.brandonespana.wowbattlepets.R;
import com.brandonespana.wowbattlepets.pets.BattlePet;
import com.brandonespana.wowbattlepets.utility.BattleNetURIHelper;
import com.brandonespana.wowbattlepets.utility.HttpCaller;
import com.brandonespana.wowbattlepets.utility.JsonResponseParser;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


public class PetResultsActivity extends ActionBarActivity implements Response.Listener<String>, Response.ErrorListener{

    private List<BattlePet> battlePets;
    private String characterName;
    private String realm;
    private String locale;

    private TextView characterNameView;
    private TextView realmView;
    private TextView resultsCountView;
    private ListView petResultsView;


    private BattleNetURIHelper uriHelper;
    private HttpCaller httpCaller;
    private JsonResponseParser parser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_results);

        uriHelper = new BattleNetURIHelper();
        httpCaller =  new HttpCaller();
        parser = new JsonResponseParser();

        characterNameView = (TextView) findViewById(R.id.resultsCharacterName);
        realmView = (TextView) findViewById(R.id.resultsRealmName);
        resultsCountView = (TextView) findViewById(R.id.resultsCount);
        petResultsView = (ListView) findViewById(R.id.resultsBattlePetList);

        Intent incomingIntent = getIntent();
        characterName = incomingIntent.getStringExtra("characterName");
        realm = incomingIntent.getStringExtra("realm");
        locale = incomingIntent.getStringExtra("locale");
        battlePets = null;

        characterNameView.setText(characterName);
        realmView.setText(realm);

        String requestURI = uriHelper.createCharacterPetRequest(realm, characterName, locale);
        Log.i("MYINFO", requestURI);


        httpCaller.contactBattleNet(requestURI, this, this, this);
//        httpCaller.makeHttpRequest(requestURI, this);
    }

    @Override
    public void onResponse(String response) {
        try {
            battlePets = parser.retrieveListOfBattlePets(response);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        populateBattlePetList(battlePets);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e("MYINFO", error.getMessage());
    }

    private void populateBattlePetList(List<BattlePet> battlePets) {
        ArrayList<String> tempNames = new ArrayList<String>();

        for(BattlePet pet:battlePets){
            tempNames.add(pet.getName());
        }

        if(resultsCountView == null){
            System.out.println("RESULTS COUNT IS NULL");
        }else{
            resultsCountView.setText(tempNames.size() + " pets");

        }
        //TODO: create a new adapter to handle a List of BattlePet objects
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,tempNames);
        petResultsView.setAdapter(adapter);


    }


}

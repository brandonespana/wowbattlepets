package com.brandonespana.wowbattlepets.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.Response;
import com.brandonespana.wowbattlepets.utility.BattleNetURIHelper;
import com.brandonespana.wowbattlepets.R;
import com.brandonespana.wowbattlepets.utility.HttpCaller;

import org.json.JSONException;


public class MainActivity extends AppCompatActivity {
    private static BattleNetURIHelper uriHelper = new BattleNetURIHelper();
    private EditText characterNameField;
    private AutoCompleteTextView realmNameField;
    private Spinner localeSpinner;
    private HttpCaller httpCaller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        characterNameField = (EditText) findViewById(R.id.characterName);
        realmNameField = (AutoCompleteTextView) findViewById(R.id.realmName);
        localeSpinner = (Spinner) findViewById(R.id.localeSpinner);

        String[] realms = getResources().getStringArray(R.array.us_realms);

        ArrayAdapter<String> realmAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,realms);
        realmNameField.setAdapter(realmAdapter);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.localeArray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        localeSpinner.setAdapter(adapter);

        httpCaller = new HttpCaller();

    }

    public void launchPetRetrieval(View view){
        String characterName = characterNameField.getText().toString();
        String realm = realmNameField.getText().toString();
        String locale = String.valueOf(localeSpinner.getSelectedItem());
        //TODO: create a map for locale displayed and actual locale value (en_US, en_GB, etc)

        Intent intent = new Intent(this, PetResultsActivity.class);
        intent.putExtra("characterName", characterName);
        intent.putExtra("realm", realm);
        intent.putExtra("locale", locale);
        startActivity(intent);


    }
}

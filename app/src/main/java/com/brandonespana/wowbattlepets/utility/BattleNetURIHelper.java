package com.brandonespana.wowbattlepets.utility;

/**
 * Copyright 2015 Brandon Espana
 * <p/>
 * Instructor and TA have the right to build and evaluate the software package
 *
 * @author Brandon Espana Brandon.Espana@asu.edu
 * @version August 15, 2015
 */
public class BattleNetURIHelper {
    private static final String BATTLE_NET_API_KEY = "fvmgaph4khnjsf7sej2vy6nhp43nb2av";
    private static final String CHARACTER_BASE_URL = "https://us.api.battle.net/wow/character/%s/%s?fields=pets&locale=%s&apikey=%s";//realm, charactername, locale, apikey


    public static String createCharacterPetRequest(String realm, String characterName, String locale){
        String request = String.format(CHARACTER_BASE_URL, realm, characterName, locale, BATTLE_NET_API_KEY);
        return request;
    }



}

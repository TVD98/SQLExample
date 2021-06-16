/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.HashMap;
import network.Decoder;

/**
 *
 * @author GMT
 */
public class Player extends Decoder{
    private String name;
    private int scores;
    
    public Player(){
        super();
        name = "Player";
        scores = 0;
    }

    @Override
    public void initKeys() {
        keys = new ArrayList<String>() {
            {
                add("name");
                add("scores");
            }
        };
    }

    @Override
    public void setValues(HashMap<String, Object> map) {
        name = (String)map.get(keys.get(0));
        scores = (int)map.get(keys.get(1));
    }
    
}

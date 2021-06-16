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
public class User extends Decoder{
    private String username;
    private String password;

    public User(){
        super();
    }

    @Override
    public void setValues(HashMap<String, Object> map) {
        username = (String)map.get(keys.get(0));
        password = (String)map.get(keys.get(1));
    }

    @Override
    public void initKeys() {
        keys = new ArrayList<String>() {
            {
                add("username");
                add("password");
            }
        };
    }

    @Override
    public String toString() {
        return String.format("Username: %s\tPassword: %s", username, password);
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author GMT
 */
public abstract class Decoder implements Cloneable {

    protected List<String> keys;

    public Decoder() {
        initKeys();
    }

    public List<String> getKeys() {
        return keys;
    }

    public abstract void initKeys();

    public abstract void setValues(HashMap<String, Object> map);

    @Override
    protected Object clone() {
        try {
            return super.clone();
        } catch (Exception e) {
            return null;
        }
    }
}

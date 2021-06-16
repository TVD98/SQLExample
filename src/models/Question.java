/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import network.Decoder;

/**
 *
 * @author GMT
 */
public class Question extends Decoder {

    private String title;
    private List<String> answers;

    public Question() {
        super();
        title = "";
        answers = new ArrayList<String>();
    }

    @Override
    public List<String> getKeys() {
        return keys;
    }

    @Override
    public void initKeys() {
        keys = new ArrayList<String>() {
            {
                add("title");
                add("correct_answer");
                add("other_answer_1");
                add("other_answer_2");
                add("other_answer_3");
            }
        };
    }

    @Override
    public void setValues(HashMap<String, Object> map) {
        title = (String) map.get(keys.get(0));
        answers.clear();
        for (int i = 1; i < 5; i++) {
            answers.add((String) map.get(keys.get(i)));
        }
    }

    @Override
    public String toString() {
        return String.format("Cau hoi: %s\n\tA: %s\n\tB: %s\n\tC: %s\n\tD: %s\n", title,
                answers.get(0), answers.get(1), answers.get(2), answers.get(3));
    }

}

package com.vaultinnovation.androidcodetest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TextManager {

    public static TextManager textManager;
    public List<String> arrayTexts;

    public static TextManager sharedInstance() {
        if (textManager == null)
            textManager = new TextManager();
        return textManager;
    }

    public TextManager(){
        initializeManager();
    }

    private void initializeManager(){
        arrayTexts = Collections.synchronizedList(new ArrayList<String>());
    }

    public void addNewText(String strText) {
        arrayTexts.add(strText);

        synchronized (arrayTexts) {
            Collections.sort(arrayTexts);
        }
    }

    public String getTextbyIndex(int nIndex) {

        if(nIndex >= arrayTexts.size())
            return null;

        String strText = arrayTexts.get(nIndex);
        return strText;
    }
}

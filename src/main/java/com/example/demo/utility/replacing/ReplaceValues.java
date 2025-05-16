package com.example.demo.utility.replacing;

import org.springframework.stereotype.Component;

@Component
public class ReplaceValues implements IReplaceValues {

    @Override
    public String replaceValues(String itemToReplace, String replacement, String text) {
        return text.replace(itemToReplace, replacement);
    }
}

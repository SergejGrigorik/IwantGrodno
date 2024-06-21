package com.kciray;

import org.springframework.beans.annotation.Value;

public class ParametersHolder {
    @Value(name = "")
    private String someText;

    public String getSomeText(){
        return someText;
    };
}

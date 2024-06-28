package com.kciray;

import org.springframework.postprocessor.value.interfaces.Value;

public class ParametersHolder {
    @Value(name = "my.param.db")
    private String someText;

    public String getSomeText() {
        return someText;
    }
}

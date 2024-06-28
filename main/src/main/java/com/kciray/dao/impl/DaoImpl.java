package com.kciray.dao.impl;

import com.kciray.ParametersHolder;
import com.kciray.dao.DaoInterface;
import org.springframework.annotation.Component;
import org.springframework.postprocessor.autowired.annotation.Autowired;


@Component

public class DaoImpl implements DaoInterface {
    @Autowired
    private ParametersHolder parametersHolder;


    @Override
    public void execute() {
        System.out.println(parametersHolder.getSomeText());

    }
}

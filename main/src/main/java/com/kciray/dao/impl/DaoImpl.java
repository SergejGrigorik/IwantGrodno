package com.kciray.dao.impl;

import com.kciray.ParametersHolder;
import com.kciray.dao.DaoInterface;
import org.springframework.beans.annotation.Autowired;
import org.springframework.beans.annotation.Component;


@Component

public class DaoImpl implements DaoInterface {
    @Autowired
    private ParametersHolder parametersHolder;


    @Override
    public void execute() {
        System.out.println(parametersHolder.getSomeText());

    }
}

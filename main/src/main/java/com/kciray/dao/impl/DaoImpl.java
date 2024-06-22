package com.kciray.dao.impl;

import com.kciray.dao.DaoInterface;
import org.springframework.beans.annotation.Component;
import org.springframework.beans.annotation.Value;

@Component

public class DaoImpl implements DaoInterface {
    @Value(name = "my.param.db")
    public String oooo;

    public String getOooo() {
        return oooo;
    }

    public void setOooo(String oooo) {
        this.oooo = oooo;
    }

    @Override
    public void execute() {

    }
}

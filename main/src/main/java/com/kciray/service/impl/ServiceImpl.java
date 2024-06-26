package com.kciray.service.impl;

import com.kciray.dao.DaoInterface;
import com.kciray.dao.impl.DaoImpl;
import com.kciray.service.ServiceInterface;
import org.springframework.beans.annotation.Autowired;
import org.springframework.beans.annotation.Component;

@Component
public class ServiceImpl implements ServiceInterface {


    private DaoInterface daoInterface;
    private String string;


    @Autowired
    private void setDaoInterface(DaoInterface daoInterface) {
        this.daoInterface = daoInterface;
    }


    @Override
    public void execute() {
        daoInterface.execute();
    }

}

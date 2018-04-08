package com.modularization.ccp;

public class MainAImpl implements MainA {

    public String main(RequestContract requestContract) {
        new RootClass();
        return "Modularization V2 " + requestContract.getValue();
    }

}

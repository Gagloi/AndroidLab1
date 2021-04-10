package com.example.lab1.tasks;

import com.example.lab1.OracleService;

import java.util.concurrent.Callable;

public class ComputationCallable implements Callable<String> {

    private String string;
    private OracleService oracleService;

    public ComputationCallable(String string) {
        oracleService = new OracleService();
        this.string = string;
    }

    @Override
    public String call() throws Exception {
        return oracleService.convert(string);
    }
}

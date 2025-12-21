package com.example.geektrust;

import com.example.geektrust.app.AppRunner;

public class Main {
    public static void main(String[] args) throws Exception{

        if(args.length < 1){
            System.err.println("Unable to run application need input file");
            return;
        }
        new AppRunner().run(args[0]);
    }
}

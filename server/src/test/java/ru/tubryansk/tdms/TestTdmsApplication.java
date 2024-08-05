package ru.tubryansk.tdms;


import org.springframework.boot.SpringApplication;


public class TestTdmsApplication {

    public static void main(String[] args) {
        SpringApplication.from(TdmsApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}

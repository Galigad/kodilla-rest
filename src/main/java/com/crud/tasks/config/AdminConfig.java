package com.crud.tasks.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AdminConfig {
    @Value("${admin.mail}")
    private String adminMail;

    @Value("${admin.name}")
    private String adminName;

    @Value("${info.app.name}")
    private String appName;

    @Value("${info.app.company.name}")
    private String companyName;

    @Value("${info.app.administrator.adress.stret}")
    private String adminAdress;

    @Value("${info.app.administrator.adress.number}")
    private String adminAdressNumber;

    @Value("${info.app.company.goal}")
    private String companyGoal;

    @Value("${info.app.company.email}")
    private String companyEmail;

    @Value("${info.app.company.phone}")
    private String companyPhone;

}

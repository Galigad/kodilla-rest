package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {

        List<String> funcionality = new ArrayList<>();
        funcionality.add("You can manage your tasks");
        funcionality.add("Provides connection with Trello Account");
        funcionality.add("Application allows sending task to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("task_url", "http://galigad.github.io");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", true);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("app_name", adminConfig.getAppName());
        context.setVariable("company_name", adminConfig.getCompanyName());
        context.setVariable("company_adress", adminConfig.getAdminAdress());
        context.setVariable("company_adress_num", adminConfig.getAdminAdressNumber());
        context.setVariable("company_mail", adminConfig.getCompanyEmail());
        context.setVariable("company_goal", adminConfig.getCompanyGoal());
        context.setVariable("company_phone", adminConfig.getCompanyPhone());
        return templateEngine.process("mail/created-trello-card-mail", context);
    }
}

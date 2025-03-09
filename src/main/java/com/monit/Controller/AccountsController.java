package com.monit.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountsController {
    @GetMapping("/myAccount")
    public String getAccountDetails() {
        return "Here is your account details";
    }

}

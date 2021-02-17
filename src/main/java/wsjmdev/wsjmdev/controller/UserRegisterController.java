package wsjmdev.wsjmdev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import wsjmdev.wsjmdev.domain.Account;
import wsjmdev.wsjmdev.repository.AccountRepository;

@Controller
public class UserRegisterController {

    @Autowired
    AccountRepository accountRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/register")
    @ResponseBody
    public String registerUser(@RequestBody Account newAccount) {

        String username = newAccount.getUsername();
        String password = Hashing.hashingPassword(newAccount.getPassword());
        String email = newAccount.getEmail();

        if(username.equals("") || password.equals("") || email.equals(""))
            return "failed";

        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        account.setEmail(email);

        if(accountRepository.findByUsername(username) != null)
            return "failed";

        accountRepository.save(account);

        return "success";
    }
}
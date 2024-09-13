package ssh.example.controller;

import org.springframework.web.bind.annotation.*;
import ssh.example.entity.Account;
import ssh.example.model.TransferRequest;
import ssh.example.service.AccountService;

@RestController
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/transfer")
    public void transferMoney(@RequestBody TransferRequest transferRequest) {
       accountService.transferMoney(transferRequest.getSenderAccountId(), transferRequest.getReceiverAccountId(), transferRequest.getAmount());
    }

    @GetMapping("/accounts")
    public Iterable<Account> getAllAccounts(@RequestParam(required = false) String name) {
        if(name == null) {
            return accountService.getAllAccounts();
        }
        return accountService.findAccountsByName(name);
    }
}

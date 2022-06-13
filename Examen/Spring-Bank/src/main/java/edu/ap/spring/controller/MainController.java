package edu.ap.spring.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.ap.spring.jpa.Wallet;
import edu.ap.spring.jpa.BankRepository;

@Controller
public class MainController {

	@Autowired
	private BankRepository repository;

	//Autowired
  	private Wallet walletA, walletB;

  	@PostConstruct
  	private void init() {
		walletA.setCoins(50.0);
		walletA.setName("walletA");

		walletB.setCoins(50.0);
		walletB.setName("walletB");
  	}

  	@GetMapping(value="/")
   	public String index() {
	   return "redirect:/balance/walletA";
   	}

  	@GetMapping(value="/balance/{wallet}")
   	public String getBalance(@PathVariable("wallet") String walletName,
                             Model model) {
	  
		Wallet wallet = repository.findByName(walletName);
		Double balance = wallet.getCoins();

		model.addAttribute("wallet", walletName);
		model.addAttribute("balance", balance);
    	return "balance";
  	}

	@GetMapping(value="/transaction")
  	public String transactionForm() {
	  	return "transaction";
	}

  	@PostMapping(value="/transaction")
  	public String transaction(@RequestParam("wallet1") String wallet1Name, 
                              @RequestParam("wallet2") String wallet2Name,
                              @RequestParam("amount") float amount) {
	
		return "";
    }
}

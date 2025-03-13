package com.monit.Controller;

import com.monit.model.Cards;
import com.monit.model.Customer;
import com.monit.repository.CardsRepository;
import com.monit.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CardsController {

    private final CardsRepository cardsRepository;
    private final CustomerRepository customerRepository;

    @GetMapping("/myCards")
    public List<Cards> getCardDetails(@RequestParam String email) {
        Optional<Customer> customer = customerRepository.findByEmail(email);
        if (customer.isPresent()) {
            List<Cards> cards = cardsRepository.findByCustomerId(customer.get().getId());
            if (cards != null) {
                return cards;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

}
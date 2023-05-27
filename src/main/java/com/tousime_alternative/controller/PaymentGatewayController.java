package com.tousime_alternative.controller;

import com.stripe.model.Charge;
import com.tousime_alternative.config.StripeClient;
import com.tousime_alternative.service.impl.ReservationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/payment")
public class PaymentGatewayController {

    private StripeClient stripeClient;
    private final ReservationServiceImpl reservationService;
    @Autowired
    PaymentGatewayController(StripeClient stripeClient, ReservationServiceImpl reservationService) {
        this.stripeClient = stripeClient;
        this.reservationService = reservationService;
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/charge")
    public Charge chargeCard(@RequestHeader(value="token") String token, @RequestHeader(value="amount") Double amount,@RequestHeader(value="reservation") Long reservation) throws Exception {
        System.out.println("here");
        reservationService.setReservationUnpayed(reservation);
        return this.stripeClient.chargeNewCard(token, amount);
    }
}

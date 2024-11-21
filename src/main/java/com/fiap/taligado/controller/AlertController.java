package com.fiap.taligado.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.taligado.service.AlertService;

@RestController
@RequestMapping("/alertas")
public class AlertController {

    private final AlertService alertService;

    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    @GetMapping
    public List<String> getAlertas() {
        return alertService.getAlertas();
    }
}

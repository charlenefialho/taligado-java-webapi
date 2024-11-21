package com.fiap.taligado.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class AlertService {

    private final List<String> alertas = new ArrayList<>();

    public void addAlerta(String alerta) {
        alertas.add(alerta);
    }

    public List<String> getAlertas() {
        return new ArrayList<>(alertas);
    }

    public void clearAlertas() {
        alertas.clear();
    }
}


package com.example.clinicabddmaven.dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HistoriaClinica {
    private List<Diagnostico> diagnostiacos;
    public HistoriaClinica(List<String> diagnostiacos){
        this.diagnostiacos =diagnostiacos
                .stream()
                .map(Diagnostico::new)
                .collect(Collectors.toList());
    }

    public Diagnostico buscarDiagnostico(String nombreDiagnostico) {
        return this.diagnostiacos.stream()
                .filter(diagnostico -> diagnostico.tieneNombre(nombreDiagnostico))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No se encontro el diagnostico"));
    }

    public void agregarEvolucion(String diagnoticoElejido, Doctor doctor, String informe) {
        Diagnostico diagnostico = buscarDiagnostico(diagnoticoElejido);
        diagnostico.agregarEvolucion(doctor, informe);
    }
}

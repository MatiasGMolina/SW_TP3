package com.example.clinicabddmaven.app;

import com.example.clinicabddmaven.dominio.Doctor;
import com.example.clinicabddmaven.dominio.Paciente;
import com.example.clinicabddmaven.repositorio.RepositorioPaciente;

public class SistemaClinica {

    private final RepositorioPaciente repositorioPaciente;

    public SistemaClinica(RepositorioPaciente repositorioPaciente) {
        this.repositorioPaciente = repositorioPaciente;
    }
    public Paciente agregarEvolucion(Doctor doctor, String dniPaciente, String diagnoticoElejido, String informe) {
        Paciente paciente = repositorioPaciente.buscaPaciente(dniPaciente).orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        paciente.agregarEvolucion(diagnoticoElejido, doctor, informe);
        repositorioPaciente.actualizarPaciente(paciente);
        return paciente;
    }
}

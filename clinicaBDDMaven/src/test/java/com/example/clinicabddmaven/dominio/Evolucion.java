package com.example.clinicabddmaven.dominio;

public class Evolucion {
    private String informe;
    private Doctor doctor;

    public Evolucion(String informe, Doctor doctor){
        this.informe=informe;
        this.doctor=doctor;
    }
    public boolean tiene(Doctor doctor,String informe){
        return this.informe.equals(informe)&& this.doctor.equals(doctor);

    }
}

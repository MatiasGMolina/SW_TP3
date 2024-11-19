package com.example.clinicabddmaven.dominio;

import java.util.ArrayList;
import java.util.List;

public class Diagnostico {
    private String nombre;
    private List<Evolucion> evoluciones;

    public Diagnostico(String nombre){
        this.nombre=nombre;
        this.evoluciones = new ArrayList<>();
    }
    public boolean tieneNombre(String nombre){
        return this.nombre.equals(nombre);
    }
     public boolean tieneEvolucion(Doctor doctor, String informe){
        return evoluciones.stream().anyMatch(evolucion -> evolucion.tiene(doctor,informe));
     }

    public void agregarEvolucion(Doctor doctor, String informe) {
        Evolucion evolucion = new Evolucion(informe,doctor);
        evoluciones.add(evolucion);
    }
}

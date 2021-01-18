/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Directorio;

import java.util.LinkedList;

/**
 *
 * @author bllv1
 */
public class Directorio {
    private String nombre;
    private Double tamaño;
    private LinkedList<Directorio> directorios;

    public Directorio(String nombre, Double tamaño) {
        this.nombre = nombre;
        this.tamaño = tamaño;
        this.directorios = new LinkedList<Directorio>();
    }

    public Directorio(String nombre) {
        this.nombre = nombre;
        this.tamaño = 0.0;
        this.directorios = new LinkedList<Directorio>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getTamaño() {
        return tamaño;
    }

    public void setTamaño(Double tamaño) {
        this.tamaño = tamaño;
    }

    public LinkedList<Directorio> getDirectorios() {
        return directorios;
    }

    public void setDirectorios(LinkedList<Directorio> directorios) {
        this.directorios = directorios;
    }
    
    @Override
    public String toString(){
        return "Directory{" + "nombre=" + nombre + ", tamaño=" + tamaño + ", directorios=" + directorios + "}";
    }
    
    public boolean esDirectorio(){
        return !this.getDirectorios().isEmpty();
    }    
    
}

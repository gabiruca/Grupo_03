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
public class Directory {
    private String name;
    private Double tamaño;
    private LinkedList<Directory> directorios;

    public Directory(String name, Double tamaño) {
        this.name = name;
        this.tamaño = tamaño;
        this.directorios = new LinkedList<Directory>();
    }

    public Directory(String name) {
        this.name = name;
        this.tamaño = 0.0;
        this.directorios = new LinkedList<Directory>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTamaño() {
        return tamaño;
    }

    public void setTamaño(Double tamaño) {
        this.tamaño = tamaño;
    }

    public LinkedList<Directory> getDirectorios() {
        return directorios;
    }

    public void setDirectorios(LinkedList<Directory> directorios) {
        this.directorios = directorios;
    }
    
    @Override
    public String toString(){
        return "Directorio{" + "nombre=" + name + ", tamaño=" + tamaño + ", directorios= " + directorios + "}";
    }
    
    public boolean isDirectory(){
        return !this.getDirectorios().isEmpty();
    }
    
    
}

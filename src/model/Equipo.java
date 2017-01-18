
package model;

import java.time.LocalDate;
import java.util.Date;


public class Equipo {
    
    private String name;
    private String city;
    private LocalDate creation;

    public Equipo() {
    }
    
    

    public Equipo(String name, String city, LocalDate creation) {
        this.name = name;
        this.city = city;
        this.creation = creation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getCreation() {
        return creation;
    }

    public void setCreation(LocalDate creation) {
        this.creation = creation;
    }

    @Override
    public String toString() {
        return "Equipo{" + "name=" + name + ", city=" + city + ", creation=" + creation + '}';
    }
    
    
}

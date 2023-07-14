package Model.Classes;

import java.util.Objects;

public abstract class Persona {
    private String name;
    private String dni;
    private String phone;
    private String address;
    private String email;

    public Persona(String name, String dni) {
        this.name = name;
        this.dni = dni;
    }

    public Persona(String name, String dni, String phone) {
        this.name = name;
        this.dni = dni;
        this.phone = phone;
    }

    public Persona(String name, String dni, String phone, String address) {
        this.name = name;
        this.dni = dni;
        this.phone = phone;
        this.address = address;
    }

    public Persona(String name, String dni, String phone, String address, String email) {
        this.name = name;
        this.dni = dni;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Objects.equals(dni, persona.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    @Override
    public String toString() {
        return name + " - " + dni + "\n" +
                "phone number: " + phone + "\n" +
                "address: " + address + "\n" +
                "email: " + email+".";
    }
}

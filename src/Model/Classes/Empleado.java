package Model.Classes;

public class Empleado extends Persona{
    public Empleado(String name, String dni) {
        super(name, dni);
    }

    public Empleado(String name, String dni, String phone) {
        super(name, dni, phone);
    }

    public Empleado(String name, String dni, String phone, String address) {
        super(name, dni, phone, address);
    }

    public Empleado(String name, String dni, String phone, String address, String email) {
        super(name, dni, phone, address, email);
    }
}

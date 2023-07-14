package Model.Classes;

public class Cliente extends Persona{
    public Cliente(String name, String dni) {
        super(name, dni);
    }

    public Cliente(String name, String dni, String phone) {
        super(name, dni, phone);
    }

    public Cliente(String name, String dni, String phone, String address) {
        super(name, dni, phone, address);
    }

    public Cliente(String name, String dni, String phone, String address, String email) {
        super(name, dni, phone, address, email);
    }
}

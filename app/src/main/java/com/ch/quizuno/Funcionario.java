package com.ch.quizuno;

public class Funcionario {

    String fNombre;
    String fApellido;
    String fCorreo;
    int fEdad;
    double fSalario;
    String fCargo;

    public Funcionario(String fNombre, String fApellido, String fCorreo, int fEdad, double fSalario, String fCargo) {
        this.fNombre = fNombre;
        this.fApellido = fApellido;
        this.fCorreo = fCorreo;
        this.fEdad = fEdad;
        this.fSalario = fSalario;
        this.fCargo = fCargo;
    }

    public String getfNombre() {
        return fNombre;
    }

    public void setfNombre(String fNombre) {
        this.fNombre = fNombre;
    }

    public String getfApellido() {
        return fApellido;
    }

    public void setfApellido(String fApellido) {
        this.fApellido = fApellido;
    }

    public String getfCoreo() {
        return fCorreo;
    }

    public void setfCoreo(String fCoreo) {
        this.fCorreo = fCoreo;
    }

    public int getfEdad() {
        return fEdad;
    }

    public void setfEdad(int fEdad) {
        this.fEdad = fEdad;
    }

    public double getfSalario() {
        return fSalario;
    }

    public void setfSalario(double fSalario) {
        this.fSalario = fSalario;
    }

    public String getfCargo() {
        return fCargo;
    }

    public void setfCargo(String fCargo) {
        this.fCargo = fCargo;
    }

    @Override
    public String toString() {
        return "Nombre: " + fNombre + " " + fApellido + '\n' +
                "Correo: " + fCorreo + '\n' +
                "Edad: " + fEdad + " años" + '\n' +
                "Salario: " + "$" + fSalario + '\n' +
                "Cargo: " + fCargo;
    }
}

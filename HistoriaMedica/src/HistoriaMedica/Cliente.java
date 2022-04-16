package HistoriaMedica;

public class Cliente {

    private String dni, fechaNacim, nombre, apellido, atencion;
    
    public Cliente(){  
    }
    
    public Cliente(String dni, String fechaNacim, String nombre, String apellido, String atencion) {
        this.dni = dni;
        this.fechaNacim = fechaNacim;
        this.nombre = nombre;
        this.apellido = apellido;
        this.atencion = atencion;
    }
    //getDNI
    public String obtener_dni() {
        return dni;
    }

    public String getFechaNacim() {
        return fechaNacim;
    }

    public String getApellido() {
        return apellido;
    }

    public String getAtencion() {
        return atencion;
    }
    public String getNombre() {
        return nombre;
    }
}

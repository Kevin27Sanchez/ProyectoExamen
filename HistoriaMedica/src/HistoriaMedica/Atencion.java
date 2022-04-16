package HistoriaMedica;

public class Atencion {
    private String id;
    private String sintomas;
    private String diagnostico;
    private String receta;

    public Atencion(){
    }

    public Atencion(String id, String sintomas, String diagnostico, String receta){
        this.id = id;
        this.sintomas = sintomas;
        this.diagnostico = diagnostico;
        this.receta = receta;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public String getId() {
        return id;
    }
    public String getReceta() {
        return receta;
    }

    public String getSintomas() {
        return sintomas;
    }
}

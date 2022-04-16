package HistoriaMedica;

import java.util.ArrayList;
import java.util.List;

public class Datos {
    public static List<Cliente> dataCLiente(){
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente("40524211", "18/03/1979", "HUBEL", "SOLIS", "20200502"));
        clientes.add(new Cliente("40050022", "01/01/2000", "JOSE", "PEREZ", "20190408"));
        clientes.add(new Cliente("40522411", "18/03/1979", "HUBEL", "SOLIS", "20210606"));
        clientes.add(new Cliente("60050021", "02/02/2015", "LUIS", "SOTO", "20180806"));
        return clientes;
    }

    public static List<Atencion> dataAtenciones(){
        List<Atencion> atenciones = new ArrayList<>();
        atenciones.add(new Atencion("20200502","Dolor de cabeza, Fiebre, Dolor de huesos","Dengue","Hidratación cada hora, Descanso"));
        atenciones.add(new Atencion("20190408","Dolor abdominal","Cólicos","Antalgina cada 5 horas"));
        atenciones.add(new Atencion("20210606","Dolor en la rodilla, Moreton en el lado lateral","Contusión leve por contacto","Frotación Doloflam cada noche antes de acostarse No hacer más ejercicios"));
        atenciones.add(new Atencion("20180806","Fiebre alta, Tos, Ojos hundidos ","Gripe estacional","Hidratación Descanso"));

        return atenciones;
    }
}

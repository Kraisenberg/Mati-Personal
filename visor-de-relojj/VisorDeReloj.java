
/**
 * La clase VisorDeReloj implementa un reloj digital para un etilo de
 * hora Europeo de 24 horas. El reloj muestra horas y minutos. El rango
 * del reloj es 00:00 (medianoche) a 23:59 (un minuto antes de medianoche)
 * The ClockDisplay class implements a digital clock display for a
 * 
 * El visor del reloj recibe "tictacs" (por medio del método ticTac) cada
 * minuto y reacciona incrementando el visor. Esto se hace de la manera 
 * usual en un reloj: las horas se incrementan cuando los minutos dan una 
 * vuelta completa y retornan al valor cero.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class VisorDeReloj
{
    private VisorDeNumeros horas;
    private VisorDeNumeros minutos;
    private String cadVisor;    // simula el visor real
    
    /**
     * Constructor de objetos de VisorDeReloj. Este constructor
     * crea un nuevo reloj puesto en hora con el valor 00:00.
     */
    public VisorDeReloj()
    {
        horas   = new VisorDeNumeros(24);
        minutos = new VisorDeNumeros(60);
        actualizarVisor();
    }

    /**
     * Constructor de objetos de VisorDeReloj. Este constructor
     * crea un nuevo reloj puesto en hora con el valor especificado 
     * por los parametros.
     */
    public VisorDeReloj(int hora, int minuto)
    {
        horas   = new VisorDeNumeros(24);
        minutos = new VisorDeNumeros(60);
        ponerEnHora(hora, minuto);
    }

    /**
     * Este método debe invocarse una vez por cada minuto; hace
     * que el visor visor avance un minuto.
     */
    public void ticTac()
    {
        minutos.incrementar();
        if(minutos.getValor() == 0) {  // alcanzó el límite!
            horas.incrementar();
        }
        actualizarVisor();
    }

    /**
     * Pone en hora el reloj con la hora y los minutos especificados.
     */
    public void ponerEnHora(int hora, int minuto)
    {
        horas.setValor(hora);
        minutos.setValor(minuto);
        actualizarVisor();
    }

    /**
     * Retorna la hora actual del visor en ele formato HH:MM.
     */
    public String getHora()
    {
        return cadVisor;
    }
    
    /**
     * Actualiza la cadena interna que representa al visor.
     */
    private void actualizarVisor()
    {
        int valorHora = horas.getValor();
        String stringHora;
        if(valorHora > 12){
            stringHora = "" + (valorHora-12);
            cadVisor = stringHora + ":" + minutos.getValorDelVisor() + " pm";            
        }
        if(valorHora == 12){
            stringHora = "" + 12;
            cadVisor = stringHora + ":" + minutos.getValorDelVisor() + " pm";
        }
        else if(valorHora == 0){
            stringHora = "" + 12;
            cadVisor = stringHora +":"+ minutos.getValorDelVisor() + " am";
        }
        else if(valorHora < 12 && valorHora > 0){
            cadVisor = horas.getValorDelVisor() + ":" + 
                        minutos.getValorDelVisor() + "am"; 
        }
    }
}

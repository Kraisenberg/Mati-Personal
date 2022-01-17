
/**
 * La clase VisorDeNumeros representa un visor digital de números que puede
 * mostrar valores desde cero hasta un determinado límite. Se puede especificar
 * el límite cuando se crea visor. El rango de valores va desde cero (inclusive)
 * hasta el límite-1. Por ejemplo si se usa el visor para los segundos de un
 * reloj digital, el límite podría ser 60, y como resultado se mostrarán los
 * valores desde 0 hasta 59. Cuando se incrementa el valor, el visor vuelve
 * automáticamente al valor 0 al alcanzar el valor límite.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 * Traducción Carlos A. Bartó
 */
public class VisorDeNumeros
{
    private int limite;
    private int valor;

    /**
     * Constructor de objetos de la clases VisorDeNumeros
     * Establece el límite en el cual el visor vueve a cero.
     */
    public VisorDeNumeros(int limiteMaximo)
    {
        limite = limiteMaximo;
        valor = 0;
    }

    /**
     * Retorna el valor actual.
     */
    public int getValor()
    {
        return valor;
    }

    /**
     * Configura el valor del visor con el nuevo valor especificado.
     * Si el nuevo valor es menor que cero o si se pasa del límite no
     * hace nada.
     */
    public void setValor(int nuevoValor)
    {
        if((nuevoValor >= 0) && (nuevoValor < limite)) {
            valor = nuevoValor;
        }
        else{
            System.out.println("Error debe ingresar un numero entre 00 y " + limite);
            System.out.println("-------------------------------------------------------------------------------");
        }
    }
    
    /**
     * Devuelve el número del visor (es decir, el valor actual, como una cadena 
     * de dos dígitos. Si el valor es menor que 10, se completa con un cero).
     */
    public String getValorDelVisor()
    {
        if(valor < 10) {
            return "0" + valor;
        }      
        else {
            return "" + valor;
        }
    }

    /**
     * Incrementa el valor del visor en uno, lo vuelve a cero si se alcanza
     * el valor límite.
     */
    public void incrementar()
    {
        valor = (valor + 1) % limite;
    }
}

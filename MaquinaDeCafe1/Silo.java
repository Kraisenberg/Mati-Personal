
/**
 *Complete los métodos de la clase Silo que extiende de Recipiente, de acuerdo a la documentación en la superclase
 *    public Silo (Ingrediente ingredienteAlmacenado, int capacidadMaxima);
 *    public Silo (Ingrediente ingredienteAlmacenado);
 *    public Ingrediente getTipoIngredienteAlmacenado();
 *    Considere los casos cuando no hay ingrediente o capacidad es mayor a capacidad máxima o negativa. 
 */
public class Silo extends Recipiente
{
    private Ingrediente ingrediente;
    
    /** Constructor donde recibimos un ingrediente y 
    *   un entero para fijar la capacidad maxima
    */
    public Silo(Ingrediente ingredienteAlmacenado, int capacidadMaxima){        
        comprobarCapacidadMaxima(capacidadMaxima);   
        comprobarIngrediente(ingredienteAlmacenado);
    }
   
    /** 
     *  En este constructor solo recibimos un ingrediente como parametro 
     */
    public Silo(Ingrediente ingredienteAlmacenado){
        comprobarIngrediente(ingredienteAlmacenado);
    }
    
    /***
     * metodo que devuelve el ingrediente como retorno
     */
    public Ingrediente getTipoIngredienteAlmacenado(){
        return ingrediente;
    }
    
    /**
     * Metodo privado que, comprueba si el ingediente ingresado es valido
     */
    private void comprobarIngrediente(Ingrediente ing){
        if(ingrediente != null){
            this.ingrediente = ing;
        }
        else{
            throw new IllegalArgumentException("F man, ingrediente no valido");
        }
    }
    /**
     * este metodo compreba si la capacidad maxima es valida
     */
    private void comprobarCapacidadMaxima(int num){
        if(num >= 0 || num < Recipiente.CAPACIDAD_MAXIMA_DEFAULT){
            this.capacidadMaxima = num;
        }
        else{
            throw new IllegalArgumentException("capacidadMaxima no valida");
        }
    }
}

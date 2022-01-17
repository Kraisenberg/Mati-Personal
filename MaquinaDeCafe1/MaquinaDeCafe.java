import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaquinaDeCafe implements MaquinaDeEstados {
    protected Producto seleccion;
    protected Estado estado = Estado.APAGADO;
    protected List <Recipiente> recipientes;
    protected Map <Producto, Receta> recetas;
    protected int totalProductosServidos;

    
    /**
     * Constructor sin parametros.
     */
    public MaquinaDeCafe(){
        recipientes = new ArrayList<Recipiente>();
        recetas = new HashMap<Producto,Receta>();
        totalProductosServidos = 0;
    }
    
    /**
     * Retorna la cantidad total de productos servidos por esta maquina.
     * 
     * @return cantidad total de productos servidos
     */
    public int getTotalProductosServidos() {
        return totalProductosServidos;
    }
    

    /**
     * Retorna los productos y recetas configurados en esta
     * maquina de cafe
     * 
     * @return mapa con productos y recetas asociadas
     */
    public Map<Producto, Receta> getRecetas() {
        return recetas;
    }

    /**
     * Retorna los recipientes configurados en esta maquina de cafe
     * 
     * @return lista de los recipientes
     */
    public List<Recipiente> getRecipientes() {
        return recipientes;
    }
    
    /**
     * Retorna el producto (receta) seleccionado
     * 
     * @return el producto seleccionada en la maquina
     */
    public Producto getSeleccion() {
        return seleccion;
    }

    /**
     * Valida si es posible despachar el producto especificaod
     * 
     * @param p el producto a despachar
     * @throws ProductoException si el producto especificado es null, o no esta
     * configurado en esta maquina, o existen ingredientes faltantes en la receta.
     * 
     */
    public void validarProducto(Producto p) throws ProductoException{
        if (p==null || !recetas.containsKey(p) )
            throw new ProductoException("No existe receta para la seleccion "+ seleccion);
        
        Receta r = recetas.get(p);
        for (Ingrediente ing: r.getIngredientes()){
            if (!hayIngredienteDisponible(ing, r.getCantidadDeIngrediente(ing)))
                throw new ProductoException("Hay faltantes para la seleccion "+seleccion);
        }
    }

    
        
    /**
     * Ejecuta la receta para el producto especificado, extrayendo
     * de cada recipiente la cantidad necesaria de ingredientes
     * para preparar la receta
     * 
     * @param el producto seleccionado
     * @throws ProductoException si por algun motivo no se puede
     * extraer la cantidad requerida de cada ingrediente o no
     * hay receta de ese producto
     */
    public void prepararProducto(Producto seleccion) throws ProductoException {
       
        Receta r = recetas.get(seleccion);
	if (r == null)
		throw new ProductoException("No hay receta configurada para seleccion " + seleccion);
		
	for (Ingrediente ing: r.getIngredientes()){
		Recipiente recipiente = getRecipiente(ing); 
		if (recipiente==null)
			throw new ProductoException("No existe ingrediente para seleccion " + seleccion);		
		try {
			recipiente.extraer(r.getCantidadDeIngrediente(ing));
		} catch (CapacidadExcedidaException e) {
			throw new ProductoException("Existe faltante para para seleccion " + seleccion);
		}
	} 
    }
            
    /**
     * Verifica si existe suficiente cantidad de un ingrediente
     * especificado
     * 
     * @param ingrediente el ingrediente requerido
     * @param cantidadRequerida la cantidad requerida
     * @return true si existe suficiente ingrediente, false en
     * caso contrario.
     */
    public boolean hayIngredienteDisponible (Ingrediente ingrediente, int cantidadRequerida){
        //TODO implementar el metodo
        throw new IllegalStateException();
    }

    
    /**
     * Retorna el recipiente para el producto especificado
     * 
     * @param ingrediente el ingrediente requerido
     * @return el recipiente que contiene el ingrediente
     * requerido, o null si no existe un recipiente para ese
     * ingrediente.
     */
    public Recipiente getRecipiente (Ingrediente ingrediente){
       
        for (Recipiente r: recipientes){
			if (ingrediente == r.getTipoIngredienteAlmacenado()){
				return r;
			}
		}
		return null;
    }
    
    /**
     * Agrega una receta para un producto de la maquina de
     * cafe. Solo es valido en modo MANTENIMIENTO
     * 
     * @param r la Receta correspondiente al producto a agregar.
     * @param p el Producto a agregar y configurar en la maquina.
     * @throws IllegalStateException si la maquina no esta en modo MANTENIMIENTO
     */
    public void agregarReceta (Receta r, Producto p){
        
        if (estado != Estado.MANTENIMIENTO){
            throw new IllegalStateException("No puede modificar recetas desde estado " + estado);
        }
        else{
            recetas.put(p,r);
        }
    }
    
    /**
     * Borra los productos y recetas cargadas en la maquina. Solo
     * es valido en modo MANTENIMIENTO
     * 
     * @throws IllegalStateException si la maquina no esta en modo MANTENIMIENTO
     */
    public void limpiarRecetas (){
        
        if(estado != Estado.MANTENIMIENTO){
            throw new IllegalStateException("No puede modificar recetas desde estado " + estado);
        }
        else{
            recetas.clear();
        }
    }

    /**
     * Selecciona un producto para despachar. Solo es posible si
     * la maquina esta lista para despachar el producto
     * 
     * @param seleccion el producto a despachar
     * @throws IllegalStateException si la maquina no esta lista
     */
    public void setSeleccion(Producto seleccion) {
        
        if (estado != Estado.LISTO)
			throw new IllegalStateException("No puede seleccionar producto desde estado "+estado);
		this.seleccion = seleccion;
    }

    /* Metodos de la interface */

    @Override
    public Estado getEstado() {
       
        return estado;
    }
    
    @Override
    public void operar() {
        
        if (estado!=Estado.LISTO)
			throw new IllegalStateException("No puede operar desde estado "+estado);
		
		try {
        
			prepararProducto(seleccion);
			totalProductosServidos++;
			
		} catch (ProductoException e){
			throw new IllegalStateException(e.getMessage());
		}
		estado = Estado.OPERANDO;
    }

    @Override
    public void restablecer() {
        
        if (estado == Estado.APAGADO)
			throw new IllegalStateException ("No puede restablecer desde estado "+ estado);
		seleccion = null;
		estado = Estado.LISTO;
    }

    @Override
    public void encender() {
        
        if (estado != Estado.APAGADO)
			throw new IllegalStateException ("No puede encender desde estado "+estado);
		seleccion = null;
		estado = Estado.LISTO;
    }

    @Override
    public void apagar() {
        
        if (estado == Estado.OPERANDO)
			throw new IllegalStateException ("No puede apagar desde estado "+estado);
		estado = Estado.APAGADO;
    }

    @Override
    public void mantener() {
       
        if (estado != Estado.LISTO)
			throw new IllegalStateException ("No puede entrar en manteniemiento desde estado " + estado);
		estado = Estado.MANTENIMIENTO;	
    }
    
}

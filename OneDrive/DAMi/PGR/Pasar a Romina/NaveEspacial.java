package examen2425;

public class NaveEspacial {
	// Atributos
	private String nombre;
	private int creacion;
	private int lanzamiento;
	private int capacidad;
	private int tripulantes;
	
	// Metodos
	// Constructores (Por defecto, Por parametros, Copia)
	public NaveEspacial () { // Por defecto
		this.nombre="";
		this.creacion=0;
		this.lanzamiento=0;
		this.capacidad=0;
		this.tripulantes=0;
	}
	public NaveEspacial (String nom, int cre, int lanz, int cap, int trip) { //Por parametros
		this.nombre=nom;
		this.creacion=cre;
		this.lanzamiento=lanz;
		this.capacidad=cap;
		this.tripulantes=trip;
	}
	
	// Get-Set
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCreacion() {
		return creacion;
	}
	public void setCreacion(int creacion) {
		this.creacion = creacion;
	}
	public int getLanzamiento() {
		return lanzamiento;
	}
	public void setLanzamiento(int lanzamiento) {
		this.lanzamiento = lanzamiento;
	}
	public int getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	public int getTripulantes() {
		return tripulantes;
	}
	public void setTripulantes(int tripulantes) {
		this.tripulantes = tripulantes;
	}
	
	// To String
	@Override
	public String toString() {
		return "Nave Espacial: "+nombre+", Año de Creacion: "+creacion+", Año de Lanzamiento: "+lanzamiento
				+", Capacidad Total: "+capacidad+", Tripulantes Necesarios: "+tripulantes;
	}
	
	// Metodos Especiales
	public int calcularAntiguedad(int anioActual) {
		return anioActual-this.creacion;
	}
	
	public boolean admitePasajeros(){
		if(this.tripulantes>=this.capacidad) {
			return false;
		}
		else {
			return true;
		}
	}
}

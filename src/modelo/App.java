package modelo;

public class App {

	public static void main(String[] args) {
		
		// Acciones
		ContactoDaoImp operacion = new ContactoDaoImp();
		
		Contacto nuevo = new Contacto();
		
		nuevo.setNombre("Adrian");
		nuevo.setApellidos("Ortiz");
		nuevo.setCelular("234234232");
		
		System.out.println( operacion.save(nuevo) );
		System.out.println( nuevo.toString() );
		
		

	}

}

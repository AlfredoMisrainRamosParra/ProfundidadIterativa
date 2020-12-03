import java.util.ArrayList;
import java.util.Scanner;

public class main {
	
	public static int limite = 1;
	public static boolean ordenado = false;
	public static int id = 1;
	public static int ultimoId = 0;
	public static ArrayList<String> cadenasYaHechas = new ArrayList<String>();
	public static ArrayList<pancake> pancakesHechos = new ArrayList<pancake>();
	
	public static void main(String args[]) {
		Scanner leer = new Scanner(System.in);
		String abecedario = "abcdefghijklmnopqrstuvwxyz";
		System.out.println("Ingrese la longitud de la cadena:");
		String cadenaOriginal = abecedario.substring(0, leer.nextInt());
		System.out.println("Ordenado: " + cadenaOriginal);
		//Cambiar revolver(cadenaOriginal) por una cadena de texto para las pruebas de casos especificos como solo cambiando 3 letras o que se solo se voltee desde el final
		String revuelta = revolver(cadenaOriginal);
		System.out.println("Primer Caso: " + revuelta);
		pancake pancake = new pancake(revuelta, id, 0, 0);
		pancakesHechos.add(pancake);
		busqueda(cadenaOriginal, pancake, 1);
		if(ultimoId>1) {
			ArrayList<pancake> caminoFinal = new ArrayList<pancake>();
			caminoFinal = caminoFinal(ultimoId);
			for(int i = caminoFinal.size()-1; i >= 0; i --) {
				System.out.println(caminoFinal.get(i).getTexto() + " | Se movio desde: " + caminoFinal.get(i).getDesplazados());
			}
		}else {
			System.out.println("La cadena no requiere ningun cambio: " + cadenaOriginal);
		}
	}
	
	public static void busqueda(String acomodado, pancake base, int vuelta) {
		for(int i = 2; i <= acomodado.length(); i ++) {
			//Se obtiene el texto del pancake
			String cadena = base.getTexto();
			//Revisa si ya se encontro una solucion
			if(!ordenado) {
				//En caso de que no, revisa si la cadena que le estoy pasando no es igual a la solucion (para ver si al revolver ya quedo arreglado o si se acaba de encontrar la solucion)
				if(!cadena.equals(acomodado)) {
					//Se mueve la cadena
					String movida = mover(cadena, i);
					//Reviso que la cadena no haya sido producida antes para ahorrar tiempo y que la profundidad a la que voy sea menor al limite de busqueda
					if(!cadenasYaHechas.contains(movida) && vuelta < limite) {
						//Aumento el id y creo el nuevo nodo o pancake, lo agrego a la lista de los que ya se hicieron y de las cadenas de texto ya hechas y vuelvo a mandar llamar el metodo
						id++;
						pancake auxiliar = new pancake(movida, id, base.getId(), i);
						cadenasYaHechas.add(auxiliar.getTexto());
						pancakesHechos.add(auxiliar);
						busqueda(acomodado, auxiliar, vuelta+1);
					}
				}else {
					//En caso ya se encontro la solucion corto el ciclo for, guardo el id de la solucion y cambio el booleano de la solucion encontrada
					i = cadena.length()+1;
					ultimoId = id;
					ordenado = true;
				}
			}
		}
		if(!ordenado && vuelta == 1) {
			limite +=1;
			id = 1;
			ultimoId = id;
			pancakesHechos.clear();
			cadenasYaHechas.clear();
			pancakesHechos.add(base);
			cadenasYaHechas.add(base.getTexto());
			busqueda(acomodado, base, 1);
		}
	}
	
	public static ArrayList<pancake>caminoFinal(int idTemp){
		ArrayList<pancake>camino = new ArrayList<pancake>();
		//Se genera un arrayList que solo tiene los pancakes de la solucion final
		while (pancakesHechos.get(idTemp-1).getIdPadre() > 0) {
			camino.add(pancakesHechos.get(idTemp-1));
			idTemp = pancakesHechos.get(idTemp-1).getIdPadre();
		}
		return camino;
	}
	
	public static String revolver(String cadena) {
		//Convierte la cadena de texto a un array de caracteres
		char cadenaRevuelta[] = cadena.toCharArray();
		for(int i = 0; i < cadena.length(); i ++) {
			//Genera un numero al azar para determinar la posicion desde que se revuelve
			int random = (int)(Math.random() * cadena.length());
			//Intercambia un caracter aleatorio con el primero de la cadena, luego con el segundo y así sucesivamente
			char temp = cadenaRevuelta[i];
			cadenaRevuelta[i] = cadenaRevuelta[random];
			cadenaRevuelta[random] = temp;
		}
		//Regresa un String con la cadena revuelta
		return String.valueOf(cadenaRevuelta);
	}
	
	public static String mover(String cadena, int pos) {
		char cadenaMovida[] = new char [pos+1];
		char cadenaOrig[] = cadena.toCharArray();
		int e = 0;
		//En un segundo array le paso los caracteres a mover en orden invertido, si debo mover "dcb" le paso "bcd"
		for(int i = pos-1; i >= 0; i --) {
			cadenaMovida[e] = cadenaOrig[i];
			e++;
		}
		//Cambio los caracteres originales de la cadena por los que ya estan invertidos
		for(int i = 0; i < pos; i ++) {
			cadenaOrig[i] = cadenaMovida[i];
		}
		//Regreso la cadena con los caracteres ya movidos
		return String.valueOf(cadenaOrig);
	}
	
}

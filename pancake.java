
public class pancake {

	private String texto;
	private int id;
	private int idPadre;
	private int desplazados;
	
	public pancake(String texto, int id, int idPadre, int desplazados) {
		this.texto = texto;
		this.id = id;
		this.idPadre = idPadre;
		this.desplazados = desplazados;
	}

	public String getTexto() {
		return texto;
	}

	public int getId() {
		return id;
	}

	public int getIdPadre() {
		return idPadre;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setIdPadre(int idPadre) {
		this.idPadre = idPadre;
	}

	public int getDesplazados() {
		return desplazados;
	}

	public void setDesplazados(int desplazados) {
		this.desplazados = desplazados;
	}
	
	
}

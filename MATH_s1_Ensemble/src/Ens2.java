import java.util.IllegalFormatCodePointException;

public class Ens2 extends EnsembleAbstrait {

	private Elt[] elements; // contient les elements de l'ensemble. Il ne peut pas y avoir de doublon.
	private int cardinal;

	public Ens2() {
		//TODO
		elements = new Elt[MAX];
		cardinal = 0;
	}

	public boolean estVide() {
		//TODO

		return (cardinal ==0) ;
	}
	
	public Elt unElement() {
		//TODO
		if (estVide())throw new MathException();
		for (int i = 0; i < MAX; i++) {
			if (elements[i] != null) return elements[i];
		}
		return null;
	}

	public boolean contient(Elt e) {
		//TODO
		if (e == null) throw new IllegalArgumentException();
		if (estVide()) return false;
		for (int i = 0; i < cardinal; i++) {
			if (e.val() == elements[i].val()){
				return true;
			}
		}
		return false;
	}

	public void ajouter(Elt e) {
		//TODO
		if (e == null) throw new IllegalArgumentException();
		if (this.contient(e)) return;
			elements[cardinal] = e;
			cardinal++;
	}

	public void enlever(Elt e) {
		//TODO
		if (e == null) throw new IllegalArgumentException();
		if (!this.contient(e))return;
		elements[e.val()] =elements[MAX -1];
		elements[MAX -1] = e;
		cardinal--;
	}

	public int cardinal() {
		//TODO
		return cardinal;
	}

	public void complementer() {
		//TODO;
		
	}

	public String toString() {
		//TODO
		return null ;
	}

}

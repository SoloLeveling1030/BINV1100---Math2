public class Ens1 extends EnsembleAbstrait {

	private boolean[] tabB; // e appartient � l'ensemble courant ssi tabE[e.val()] est � true.
	private int cardinal;

	public Ens1() {
		//TODO
		tabB = new boolean[Elt.MAXELT.val()+1];
		cardinal =0;
	}
	
	public boolean estVide() {
		//TODO
		return  (cardinal == 0);

	}
	
	public Elt unElement() {
		//TODO
		if (estVide())throw new MathException();
		for (int i = 1; i < tabB.length -1; i++) {
			if (tabB[i]) {
				Elt e = new Elt(i);
				return e;
			}
		}
		throw new InternalError();
	}

	public boolean contient(Elt e) {
		//TODO
		if (e == null) throw new IllegalArgumentException();
		return tabB[e.val()];
	}

	public void ajouter(Elt e) {
		//TODO
		if (e == null) throw new IllegalArgumentException();
		if (!tabB[e.val()]) {
			tabB[e.val()] = true;
			cardinal++;
		}
	}

	public void enlever(Elt e) {
		//TODO
		if (e == null) throw new IllegalArgumentException();
		if (tabB[e.val()]){
			tabB[e.val()] = false;
			cardinal--;
		}
	}

	public int cardinal() {
		//TODO
		if (estVide()) throw new IllegalArgumentException();

		return 0;
	}

	public void complementer() {
		//TODO
		
	}

	public String toString() {
		// TODO
		return null;
	}
	
}

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

		return cardinal;
	}

	public void complementer() {
		//TODO
		for (int i = 1; i <= MAX; i++) {
			if (tabB[i]){
				tabB[i] = false;
				cardinal--;
			}else {
				tabB[i] = true;
				cardinal++;
			}

		}
		
	}

	public String toString() {
		// TODO
		if (estVide())
			return "{}";


		return null;
	}
	
}

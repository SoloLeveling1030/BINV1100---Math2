public class Ens2 extends EnsembleAbstrait {

	private Elt[] elements; // contient les elements de l'ensemble. Il ne peut pas y avoir de doublon.
	private int cardinal;

	public Ens2() {
		elements = new Elt[MAX];
		cardinal = 0;
	}

	public boolean estVide() {
		return cardinal == 0;
	}

	/**
	 * Retourne un élément de l'ensemble courant.
	 * @throws MathException si l'ensemble courant est vide.
	 * @return un élément de l'ensemble courant.
	 */
	public Elt unElement() {
		if (estVide()) throw new MathException("L'ensemble courant est vide");
		for (Elt element : elements) {
			if (element != null)
				return element;
		}
		return null ;
	}

	/**
	 * renvoie true ssi e appartient é l'ensemble courant
	 * @param e l'élément à vérifier
	 * @throws IllegalArgumentException en cas de paramètre invalide
	 * @return true ssi e appartient à l'ensemble courant
	 */
	public boolean contient(Elt e) {
		if (e == null) throw new IllegalArgumentException();
		return elements[e.val()] != null;
	}
	/**
	 * ajoute e (éventuellement) à l'ensemble courant
	 * @param e l'élément à ajouter
	 * @throws IllegalArgumentException en cas de paramètre invalide
	 */
	public void ajouter(Elt e) {
		if (e == null) throw new IllegalArgumentException();
		if (contient(e)) return;
		elements[e.val()] = e;
		cardinal++;
	}

	/**
	 * retire e (éventuellement) à l'ensemble courant
	 * @param e l'élément à enlever
	 * @throws IllegalArgumentException en cas de paramètre invalide
	 */
	public void enlever(Elt e) {
		if (e == null) throw new IllegalArgumentException();
		if (!contient(e)) return;
		elements[e.val()] = null;
		cardinal--;
	}

	/**
	 * renvoie le cardinal de l'ensemble courant
	 * @return le cardinal de l'ensemble courant
	 */
	public int cardinal() {
		return cardinal;
	}

	public void complementer() {
		boolean[] elements2 = new boolean[MAX + 1];
		for (int i = 0; i < cardinal; i++) {
			elements2[elements[i].val()] = true;
		}
		int indice=0 ;

		for (int i = 1; i < elements2.length; i++) {
			if (!elements2[i]) {
				elements[indice] = new Elt(i);
				indice++;

			}
		}
		cardinal = MAX - cardinal;
	}

	/**
	 * renvoie une chaine de caractère décrivant this en extension
	 * @return une chaine de caractère décrivant this en extension
	 */
	public String toString() {
		StringBuilder text = new StringBuilder("{");
		if (estVide())
			text.append("}");

		for (Elt element : elements) {
			if (element != null)
				text.append(element.val()).append(", ");
		}
		return text.toString() ;
	}

}
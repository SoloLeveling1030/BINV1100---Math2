/** Classe Equivalence
	 Chaque instance de cette classe est une relation d'�quivalence sur un sous-ensemble de l'Univers
 */

import java.util.*;

public class Equivalence extends RelationAbstraite {

	private EnsembleAbstrait sousJac; // ensemble sous-jacent
	private Elt[] tabRep; // tableau des repr�sentants
	private int numVersion; // num�ro de version


	// Construit l�identit� sur e
	// Lance une IllegalArgumentException en cas de param�tre invalide 
	public Equivalence(EnsembleAbstrait e) {
		//TODO
		if (e == null ) throw new IllegalArgumentException();
		this.sousJac = e.clone();
		this.tabRep = new Elt[MAX+1];
		this.numVersion =0;
		for (Elt elt:sousJac) {
			tabRep[elt.val()] = elt;
		}
	}
	
	
	// ajoute (si n�cessaire) l�ar�te x-y au diagramme de classes de
	// l�Equivalence courante ; autrement dit, fusionne les classes de
	// c.getx() et de c.gety(). 
	// Lance une IllegalArgumentException en cas de param�tre invalide 
	public void ajouter(Couple c) {
		//TODO
		if (c == null) throw new IllegalArgumentException();
		Elt x = c.getX();
		Elt  y = c.getY();
		// Vérifier que x et y appartiennent à l'ensemble sous-jacent
		if (!sousJac.contient(x) || !sousJac.contient(y))
			throw new IllegalArgumentException("Les éléments du couple doivent appartenir à l'ensemble sous-jacent.");

		Elt repX = tabRep[x.val()];
		Elt repY = tabRep[y.val()];

		if (repX == null || repY == null) throw new IllegalStateException("Les représentants ne doivent pas être null.");

		if (!repX.equals(repY)){
			numVersion++;
			for (int i = 1; i < tabRep.length; i++) {
				if (tabRep[i] != null && tabRep[i].equals(repY)) {
					tabRep[i] = repX;
				}
			}
		}
	}

	// Construit la cl�ture �quivalente de r, pour autant que celle-ci soit une relation sur un ensemble.
	// lance une IllegalArgumentException sinon
	public Equivalence(Relation r) {
		if (r == null) throw new IllegalArgumentException("La relation ne peut pas être null.");
		EnsembleAbstrait d = r.depart();
		EnsembleAbstrait a = r.arrivee();
		if (!d.equals(a)) throw new IllegalArgumentException("La relation doit être sur un seul ensemble.");
		this.sousJac = d.clone();
		this.tabRep = new Elt[MAX + 1];
		this.numVersion = 0;
		// Initialiser chaque élément de l'ensemble sous-jacent pour qu'il soit son propre représentant
		for (Elt elt : sousJac) {
			tabRep[elt.val()] = elt;
		}
		// Fusionner les classes pour chaque couple de la relation r
		for (Couple c : r) {
			ajouter(c);
		}
	}


	
	// renvoie true si c.getx() et c.gety() sont dans la m�me classe et false sinon
	// Lance une IllegalArgumentException en cas de param�tre invalide 
	public boolean contient(Couple c) {
		//TODO
		if (c == null) throw new IllegalArgumentException();
		Elt x = c.getX();
		Elt y = c.getY();
		if (!sousJac.contient(x) || !sousJac.contient(y)) throw new IllegalArgumentException();
		Elt repX = tabRep[x.val()];
		Elt repY = tabRep[y.val()];
		return repX.equals(repY);
	}

	// renvoie la classe d'�quivalence de x, ou g�n�re une IllegalArgumentException
	// si e est null ou si e n'appartient pas � l'ensemble sous-jacent
	public EnsembleAbstrait classe(Elt e) {
		//TODO
		if (e == null) throw new IllegalArgumentException();
		if (!sousJac.contient(e)) throw new IllegalArgumentException("L'élément doit appartenir à l'ensemble sous-jacent.");
		Elt repE = tabRep[e.val()];
		EnsembleAbstrait classeRepresentant = new Ensemble();
		for (int i = 0; i < tabRep.length; i++) {
			if (tabRep[i] !=null && tabRep[i].equals(repE)){
				classeRepresentant.ajouter(new Elt(i));
			}

		}
		return classeRepresentant;
	}

	// Si c.getx()et c.gety() sont distincts et si la classe commune
	// de c.getx() et c.gety() est {c.getx(),c.gety()}, alors cette classe
	// sera scind�e en deux classes.
	// g�n�re une IllegalArgumentException si le param�tre est invalide,
	// ou si c.getx(), c.gety() sont dans la m�me classe  mais qu'on n'est pas
	// dans le cas o� on peut scind�e cette classe.
	public void enlever(Couple c) {
		//pas correcte

		if (c == null) throw new IllegalArgumentException();
		Elt x = c.getX();
		Elt y = c.getY();
		if (!sousJac.contient(x) || !sousJac.contient(y)) throw new IllegalArgumentException();
		Elt repX = tabRep[x.val()];
		Elt repY = tabRep[y.val()];
		if (!repX.equals(repY)) throw new IllegalArgumentException("Les éléments ne sont pas dans la même classe.");

		// Vérifier si la classe commune est {x, y}
		EnsembleAbstrait classeX = classe(x);
		if (classeX.cardinal() != 2 || !classeX.contient(y)) {
			throw new IllegalArgumentException("La classe commune n'est pas {x, y}.");
		}

		// Scinder la classe en deux
		tabRep[y.val()] = y;
		numVersion++;
	}

	// renvoie le nombre de classes de l'Equivalence courante
	public int nbreClasses() {
		//TODO
		Set<Elt> representants = new HashSet<>();
		for (Elt elt:sousJac) {
			representants.add(tabRep[elt.val()]);

		}
		return representants.size();
	}

	// renvoie le quotient de l�ensemble sous-jacent par l'Equivalence
	// courante
	public EnsembleAbstrait[] quotient() {
		Map<Elt, EnsembleAbstrait> classes = new HashMap<>();
		for (Elt elt : sousJac) {
			Elt representant = tabRep[elt.val()];
			if (!classes.containsKey(representant)) {
				classes.put(representant, new Ensemble());
			}
			classes.get(representant).ajouter(elt);
		}
		return classes.values().toArray(new EnsembleAbstrait[0]);
	}

	public boolean estVide() {
		return sousJac.estVide();
	}

	@Override
	public EnsembleAbstrait depart() {
		return sousJac.clone();
	}

	@Override
	public EnsembleAbstrait arrivee() {
		return sousJac.clone();
	}
	
	/** renvoie un it�rateur sur l'Equivalence courante */
	public Iterator<Couple> iterator() {
		return new EquivalenceIterator();
	}

	private class EquivalenceIterator implements Iterator<Couple> {
		private Iterator<Couple> itC;
		private int version;

		public EquivalenceIterator() {
			version = numVersion;
			Relation r = new Relation(sousJac, sousJac);
			EnsembleInterface reste = sousJac.clone();
			while (!reste.estVide()) {
				Elt e = reste.unElement();
				EnsembleAbstrait classeE = classe(e);
				Iterator<Elt> itClasseE = classeE.iterator();
				while (itClasseE.hasNext()) {
					Elt next = itClasseE.next();
					r.ajouter(e, next);
					r.ajouter(next, e);
					r.ajouter(next, next);
				}
				reste.enlever(classeE);
			}
			r.cloTrans();
			itC = r.iterator();
		}

		@Override
		public boolean hasNext() {
			return itC.hasNext();
		}

		@Override
		public Couple next() {
			if (numVersion != this.version)
				throw new ConcurrentModificationException();
			if (!hasNext())
				throw new NoSuchElementException();
			return itC.next();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

} // Equivalence

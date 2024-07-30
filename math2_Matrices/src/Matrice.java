import java.awt.*;

public class Matrice {
    private final int nbLignes;              // nombre de lignes
    private final int nbColonnes;            // nombre de colonnes
    private final double[][] data;           // matrice (nbLignes,nbColonnes)

    // ce constructeur cree la matrice nulle de genre (a,b)
    public Matrice(int a, int b) throws IllegalArgumentException { 
    	//TODO : Les lignes ci-dessous sont là uniquement pour qu'il n'y ait pas d'erreur. Elles doivent être modifiées
        if (a <1  || b < 1 ) throw new IllegalArgumentException();
        this.nbLignes = a;
        this.nbColonnes = b;
        this.data = new double[a][b];
    }

    //  Ce constructeur permet de construire la matrice correspondant 
    //  au tableau en parametre. 
    public Matrice(double[][] tab)  throws IllegalArgumentException {
    	//TODO : La ligne suivante est là uniquement pour qu'il n'y ait pas d'erreur. Elle doit être modifiée ou supprimée
        if (tab == null || tab.length == 0 || tab[0] == null || tab[0].length == 0)throw new IllegalArgumentException();
        int paramLenght = tab[0].length;
        for (int i = 1; i < tab.length; i++) {
            if (tab[i] == null || tab[i].length != paramLenght)throw new IllegalArgumentException();
        }
        data = new double[tab.length][tab[0].length];
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[0].length; j++) {
                data[i][j] = tab[i][j];
            }
        }
        nbLignes = tab.length;
        nbColonnes = tab[0].length;
    }

    // constructeur par recopie
    public Matrice(Matrice a)  throws IllegalArgumentException {
        //TODO : La ligne suivante est là uniquement pour qu'il n'y ait pas d'erreur. Elle doit être modifiée ou supprimée
        if (a == null) throw new IllegalArgumentException();
        data = new double[a.nbLignes][a.nbColonnes];
        for (int i = 0; i < a.nbLignes; i++) {
            for (int j = 0; j < a.nbColonnes; j++) {
                data [i][j] = a.data[i][j];
            }
        }
        nbLignes = a.nbLignes;
        nbColonnes = a.nbColonnes;

	}

     // cree la matrice identite d'ordre a
     // si a = 2
     // créer une matrice (a,a) -> (2,2)
     // { 1, 1 }
     // { 1, 1 }
    public static Matrice identite(int a)  throws IllegalArgumentException {
    	//TODO
        if (a <= 0) throw new IllegalArgumentException();

      Matrice m= new Matrice(a,a);
        for (int i = 0; i < m.data.length; i++) {
            m.data[i][i] = 1;
        }
    	return m;
    }
    
    //Cette methode renvoie l'element de la ligne numLigne et de la 
    //colonne numColonne de la matrice. Si cet element n'existe pas, la 
    //methode lance une IllegalArgumentException 
	public double getElement(int numLigne, int numColonne)
			throws IllegalArgumentException {
		//TODO
        if (numLigne <=0 || numColonne <=0 || numLigne > nbLignes || numColonne >nbColonnes) throw new IllegalArgumentException();

		return data[numLigne -1][numColonne-1];
	 }
    
    // ajoute b a la matrice courante si c'est possible
    public Matrice somme(Matrice b)  throws IllegalArgumentException {
    	//TODO
        if (b == null || data.length != b.data.length || data[0].length != b.data[0].length) throw new IllegalArgumentException();
        Matrice m = new Matrice(nbLignes,nbColonnes);
        for (int i = 0; i < b.data.length ; i++) {
            for (int j = 0; j < b.data[0].length; j++) {
                m.data[i][j] = data[i][j] + b.data[i][j];

            }
        }
    	return m;
    }

    // calcule le produit scalaire.this de la matrice courante avec scalaire
     // matrice * sclaire
    public Matrice produitParScalaire(double scalaire){
    	//TODO
        Matrice m = new Matrice(nbLignes,nbColonnes);
        for (int i = 0; i < m.data.length ; i++) {
            for (int j = 0; j < m.data[0].length ; j++) {
                m.data[i][j] = data[i][j] * scalaire;
            }
        }
    	return m;
    }

    // calcule le produit this*b de la matrice courante avec b si possible
     // matrice * matrice
    public Matrice produitAGauche(Matrice b)  throws IllegalArgumentException {
    	//TODO
        if (b == null || nbColonnes != b.nbLignes ) throw new IllegalArgumentException();

        Matrice m = new Matrice(nbLignes,b.nbColonnes);
        double temp = 0;
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < b.nbColonnes; j++) {
                for (int k = 0; k < b.nbLignes ; k++) {
                temp += this.data[i][k] * b.data[k][j];
                }
                m.data[i][j] = temp;
                temp = 0;
            }
        }
    	return m;
    }
    
	// calcule le produit b*this de b avec la matrice courante si possible
    public Matrice produitADroite(Matrice b)  throws IllegalArgumentException {
    	//TODO
        if (b == null || b.nbColonnes != nbLignes ) throw new IllegalArgumentException();

        Matrice m = new Matrice(b.nbLignes,nbColonnes);
        double temp = 0;
        for (int i = 0; i < b.nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                for (int k = 0; k < nbLignes ; k++) {
                    temp += b.data[i][k] * this.data[k][j];
                }
                m.data[i][j] = temp;
                temp = 0;
            }
        }
        return m;
    }
	 
   // renvoie true si la matrice courante est carrée
	 public boolean carree(){
		//TODO
		 return nbLignes == nbColonnes;
    }
    
    // Calcule this^n. Lance une Mathexception si this n'est pas carrée
    public Matrice puissance(int n) throws  IllegalArgumentException {
    	//TODO
        if (!carree())throw new MathException();
        if (n < 0) throw new IllegalArgumentException();
        if (n == 0) return identite(nbLignes);
        Matrice m = new Matrice(this);
        for (int i = 2; i <= n ; i++) {
            m = m.produitAGauche(this);
        }
    	return m;
    }
    
	//Calcule this^T : la tranposée de this
	public Matrice transposee() {
		//TODO
        double [][] temp= new double[nbColonnes][nbLignes];
        for (int i = 0; i < nbLignes ; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                temp[j][i] = data[i][j];
            }
        }
		return new Matrice(temp);
	}
	 
    // affiche la matrice en format standard
    public String toString(){
    	//TODO
        StringBuilder aRenvoyer = new StringBuilder();
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                aRenvoyer.append(data[i][j]);
                int nbCar = String.valueOf(data[i][j]).length();
                int nbEspaces = 15 - nbCar;
                for (int k = 0; k < nbEspaces; k++) {
                    aRenvoyer.append(" ");
                }
            }
            aRenvoyer.append("\n");
        }
        return aRenvoyer.toString();
    }

    public Matrice pageRank() {
        // A FAIRE QUAND MARKOV AURA ÉTÉ VU
        return null ;
    }
  }   

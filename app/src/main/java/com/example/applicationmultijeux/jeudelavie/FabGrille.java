package JeuDeLaVie;

public class FabGrille 
{
	
	public static Grille creation (int ligne, int colonne, double p) // p : proportion de cellules activées
	{
        Grille maGrille = new Grille();

        if (ligne < 2 || colonne < 2)   return null;
        if (p < 0 || p > 1      )   return null;

        Cellule[][] tabCellule = new Cellule[ligne+2][colonne+2];

        for ( int i = 1; i <= ligne; i++ ) 
		{

            for ( int j = 1; j <= colonne; j++ ) 
			{

                tabCellule[i][j] = new Cellule(p);
                maGrille.addCellule(tabCellule[i][j]);

            }

        }

        /**
         * Copier les cellules pour le déplacement torique
         */
        for ( int i = 1; i <= colonne; i++ ) 
		{

            tabCellule[0][i]        = tabCellule[ligne][i];
            tabCellule[ligne+1][i]  = tabCellule[1][i];

        }

        for ( int i = 1; i <= ligne; i++ ) 
		{

            tabCellule[i][0]        = tabCellule[i][colonne];
            tabCellule[i][colonne+1]    = tabCellule[i][1];

        }

        tabCellule[0][0]            = tabCellule[ligne][colonne];
        tabCellule[ligne+1][colonne+1]  = tabCellule[1][1];
        tabCellule[ligne+1][0]      = tabCellule[1][colonne];
        tabCellule[0][colonne+1]        = tabCellule[ligne][1];

        for ( int i = 1; i <= ligne; i++ ) 
		{

            for ( int j = 1; j <= colonne; j++ ) 
			{

                for ( int k = -1; k <= 1; k++ ) 
				{

                    tabCellule[i][j].addVoisine(tabCellule[i+k][j-1]);
                    tabCellule[i][j].addVoisine(tabCellule[i+k][j+1]);

                }

                tabCellule[i][j].addVoisine(tabCellule[i+1][j]);
                tabCellule[i][j].addVoisine(tabCellule[i-1][j]);

            }

        }

        return maGrille;

	}

}

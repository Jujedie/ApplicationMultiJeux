package com.example.applicationmultijeux.jeudelavie;

public class FabGrille 
{
	
	public static Grille creation (int taille, double p) // p : proportion de cellules activées
	{
        Grille maGrille = new Grille();

        if (taille < 2)   return null;
        if (p < 0 || p > 1      )   return null;

        Cellule[][] tabCellule = new Cellule[taille+2][taille+2];

        for ( int i = 1; i <= taille; i++ )
		{

            for ( int j = 1; j <= taille; j++ )
			{

                tabCellule[i][j] = new Cellule(p);
                maGrille.addCellule(tabCellule[i][j]);

            }

        }

        /**
         * Copier les cellules pour le déplacement torique
         */
        for ( int i = 1; i <= taille; i++ )
		{

            tabCellule[0][i]        = tabCellule[taille][i];
            tabCellule[taille+1][i]  = tabCellule[1][i];

        }

        for ( int i = 1; i <= taille; i++ )
		{

            tabCellule[i][0]        = tabCellule[i][taille];
            tabCellule[i][taille+1]    = tabCellule[i][1];

        }

        tabCellule[0][0]            = tabCellule[taille][taille];
        tabCellule[taille+1][taille+1]  = tabCellule[1][1];
        tabCellule[taille+1][0]      = tabCellule[1][taille];
        tabCellule[0][taille+1]        = tabCellule[taille][1];

        for ( int i = 1; i <= taille; i++ )
		{

            for ( int j = 1; j <= taille; j++ )
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

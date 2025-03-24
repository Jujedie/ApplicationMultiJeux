package com.example.applicationmultijeux.jeudelavie;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

/**
 * Une grille rassemble plusieurs cellules.
 * @author Kochat Nahel, INFOG1 - IUT du Havre
 * @version 1.0.2, 2024-09-24                J-2
 */

public class Grille 
{
	
	private Set<Cellule> cellules;

	Grille()
	{
		cellules = new HashSet<Cellule>();
	}

	void addCellule(Cellule c)
	{
		this.cellules.add(c);
	}

	public void etatSuivant()
	{
		for(Cellule c : cellules)
			c.etatSuivant();
	}

	public Set<Cellule> getSetCellule()
	{
		return cellules;
	}

	public int getCote()
	{
		return (int)Math.sqrt(cellules.size());
	}


}

package com.example.applicationmultijeux.jeudelavie;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

/**
 * Une cellule possède un état, morte ou vivante.
 * Et un voisinage de Cellule
 * @author Kochat Nahel, INFOG1 - IUT du Havre
 * @version 1.0.2, 2024-09-24                J-2
 */


class Cellule 
{
	
	/*Voisinage */
	private Set<Cellule> voisines;

	// Etat de la Cellule
	private Boolean etat = false;

	/**
	 * 
	 * Initialise les variables
	 * @param etat de la nouvelle Cellule
	 */

	Cellule()
	{
		voisines = new HashSet<Cellule>();
	}

	Cellule(Boolean etat)
	{
		this();
		this.etat = etat;
	}

	Cellule(double p)
	{
		this(Math.random()<p);
	}

	public Boolean getEtat() 
	{
		return etat;
	}
	
	/**
	 * Ajout d'une nouvelle Cellule
	 * @param c nouvelle Cellule
	 */
	public void add(Cellule c)
	{
		this.voisines.add(c);
	}

	void addVoisine(Cellule c)
	{
		voisines.add(c);
	}
	
	private Boolean nouvelEtat;

	boolean etatSuivant()
	{
		int nbVoisines = 0;


		for (Cellule c : voisines)
		{
			if (c.getEtat()) nbVoisines++;
		}

		if (nbVoisines<2 || nbVoisines > 3)
		{
			this.nouvelEtat = false;
			return this.etat;
		}

		if (nbVoisines == 2)
		{
			this.nouvelEtat = this.etat;
			return false;
		}
		else // dernier cas : nbVoisines == 3
		{
			nouvelEtat = true;
			return !etat;
		}
		
		
	}
}
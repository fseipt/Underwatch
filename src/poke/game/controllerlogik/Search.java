package poke.game.controllerlogik;

import poke.game.programmlogik.Pokemonreader;

import java.util.Arrays;

import poke.game.programmlogik.Pokemon;

public class Search {

	private Pokemon[] p;
	private Pokemon[] alph;
	private Pokemon[] search = new Pokemon[0];
	private Pokemon[] sort = new Pokemon[0];
	private int[] check;
	
	public Search(Pokemonreader p) {
		this.p = p.getPokemon();
		this.alph = this.sort("alph");
	}
	/**
	 * Suchen nach Kriterium
	 * @param s das Kriteruem
	 * @return das Sortierte Array
	 */
	public Pokemon[] Search(String s) {
		int l = s.length();
		if(l == 0) {
			return this.p;
		}
		for(int x = 0; x < p.length; x++) {
			String att = this.p[x].getName().substring(0,l);
			if(att.contains(s)) {
				this.add(p[x]);
			}
		}
		return this.search;
	}
	
	public void add(Pokemon p) {
		search = Arrays.copyOf(search, search.length+1);
		search[search.length-1] = p;
	}
	public void addSort(Pokemon p) {
		sort = Arrays.copyOf(sort, sort.length+1);
		sort[sort.length-1] = p;
	}
	
	
	public Pokemon[] sort(String krit) {
		Pokemon[] sort = new Pokemon[0];
		if(krit.equals("alph")) {
			sort = this.sortByAlph();
		}
		if(krit.equals("hp")) {
			sort = this.sortByHP();
		}
		if(krit.equals("att")) {
			sort = this.sortByATT();
		}
		if(krit.equals("def")) {
			sort = this.sortByDEF();
		}
		if(krit.equals("satt")) {
			sort = this.sortBySATT();
		}
		if(krit.equals("sdef")) {
			sort = this.sortBySDEF();
		}
		if(krit.equals("spe")) {
			sort = this.sortBySPE();
		}
		return sort;
	}

	/**
	 * Sortiert das Array alphabetisch, indem es wenn index 1 einen niedrigereren Unicode
	 * an erster Stelle hat als 0 dann werden diese getauscht
	 * @return das sortierte Array
	 */
	public Pokemon[] sortByAlph() {
		this.sort = p;
		boolean sort = false;
		boolean innersort = false;
		int l = 0;
		int ca, cb;
		while(sort == false) {
			sort = true;
			for(int x = 0; x < this.sort.length-1; x++) {
				
				String a = this.sort[x].getName();
				String b = this.sort[x+1].getName();
				int la = a.length(); int lb = b.length();
				if(la > lb) {
					l = lb;
				} else {
					l = la;
				}
				
				for(int y = 0; y < l; y++) {
					innersort = false;
					ca = a.charAt(y);
					cb = b.charAt(y);
					if(ca > cb) {
						sort = this.switchP(x, x+1);
						innersort = true;
						break;
					}
					if(ca < cb) {
						break;
					}
					if(y+1 == l && innersort == false) {
						if(la > lb) {
							sort = switchP(x, x+1);
						}
					}
				}
			}
		}
		return this.sort;
	}
	
	/**
	 * Tauscht zwei Pokemons aus in dem sort Array
	 * @param a der index des ersten Pokemons
	 * @param b der Index des zweiten Pokemons
	 */
	public boolean switchP(int a, int b) {
		Pokemon p = sort[a];
		sort[a] = sort[b];
		sort[b] = p;
		return false;
	}
	
	/**
	 * Sortiert das Array nach HP
	 * @return das Array nach HP sortiert
	 */
	public Pokemon[] sortByHP() {
		Pokemon[] run = this.alph;
		this.sort = new Pokemon[0];
		boolean ok = true;
		for(int y = 0; y < run.length; y++) {
			int high = 0;
			int index = 0;
			for(int x = 0; x < run.length; x++) {
				if(run[x] != null) {
					int v = run[x].getStat().getHP();
					if(v > high) {
						high = v;
						index = x;
					}
				}
			}
			this.addSort(run[index]);
			run[index] = null;
		}
		return this.sort;
	}

	
	/**
	 * Sortiert das Array nach Attack
	 * @return das Array nach Attack sortiert
	 */
	public Pokemon[] sortByATT() {
		Pokemon[] run = this.alph;
		this.sort = new Pokemon[0];
		boolean ok = true;
		for(int y = 0; y < run.length; y++) {
			int high = 0;
			int index = 0;
			for(int x = 0; x < run.length; x++) {
				if(run[x] != null) {
					int v = run[x].getStat().getATT();
					if(v > high) {
						high = v;
						index = x;
					}
				}
			}
			this.addSort(run[index]);
			run[index] = null;
		}
		return this.sort;
	}

	
	/**
	 * Sortiert das Array nach Defense
	 * @return das Array nach Defense sortiert
	 */
	public Pokemon[] sortByDEF() {
		Pokemon[] run = this.alph;
		this.sort = new Pokemon[0];
		boolean ok = true;
		for(int y = 0; y < run.length; y++) {
			int high = 0;
			int index = 0;
			for(int x = 0; x < run.length; x++) {
				if(run[x] != null) {
					int v = run[x].getStat().getDEF();
					if(v > high) {
						high = v;
						index = x;
					}
				}
			}
			this.addSort(run[index]);
			run[index] = null;
		}
		return this.sort;
	}

	
	/**
	 * Sortiert das Array nach spezialattack
	 * @return das Array nach spezialattack sortiert
	 */
	public Pokemon[] sortBySATT() {
		Pokemon[] run = this.alph;
		this.sort = new Pokemon[0];
		boolean ok = true;
		for(int y = 0; y < run.length; y++) {
			int high = 0;
			int index = 0;
			for(int x = 0; x < run.length; x++) {
				if(run[x] != null) {
					int v = run[x].getStat().getSATT();
					if(v > high) {
						high = v;
						index = x;
					}
				}
			}
			this.addSort(run[index]);
			run[index] = null;
		}
		return this.sort;
	}

	
	/**
	 * Sortiert das Array nach Spezialdefense
	 * @return das Array nach Spezialdefense sortiert
	 */
	public Pokemon[] sortBySDEF() {
		Pokemon[] run = this.alph;
		this.sort = new Pokemon[0];
		boolean ok = true;
		for(int y = 0; y < run.length; y++) {
			int high = 0;
			int index = 0;
			for(int x = 0; x < run.length; x++) {
				if(run[x] != null) {
					int v = run[x].getStat().getSDEF();
					if(v > high) {
						high = v;
						index = x;
					}
				}
			}
			this.addSort(run[index]);
			run[index] = null;
		}
		return this.sort;
	}

	
	/**
	 * Sortiert das Array nach Speed
	 * @return das Array nach Speed sortiert
	 */
	public Pokemon[] sortBySPE() {
		Pokemon[] run = this.alph;
		this.sort = new Pokemon[0];
		boolean ok = true;
		for(int y = 0; y < run.length; y++) {
			int high = 0;
			int index = 0;
			for(int x = 0; x < run.length; x++) {
				if(run[x] != null) {
					int v = run[x].getStat().getSPE();
					if(v > high) {
						high = v;
						index = x;
					}
				}
			}
			this.addSort(run[index]);
			run[index] = null;
		}
		return this.sort;
	}
	
	/**
	 * gibt das alphabetisch sotierte Array zurueck
	 * @return das alphabetisch sotierte Array
	 */
	public Pokemon[] getPokemon() {
		return this.alph;
	}
}

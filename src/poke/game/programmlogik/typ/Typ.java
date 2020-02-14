package poke.game.programmlogik.typ;

import java.util.Arrays;

import poke.game.programmlogik.Allgemein;
import poke.game.programmlogik.WrongArgumentException;

public  class Typ {
	
	private String typ;
	private String[] imm;
	private String[] zweiRes;
	private String[] zweiEff;
	
	public Typ() {
		
	}
	
	
	public String[] getZweiRes() {
		return zweiRes;
	}
	public void setZweiRes(String zweiRes) throws WrongArgumentException {
		int[] z = new int[0];
		String[] v;
		for(int x = 0; x < zweiRes.length(); x++) {
			if(zweiRes.charAt(x) == 47) {
				z = Arrays.copyOf(z, z.length+1);
				z[z.length-1] = x;
			}
		}
		v = new String[z.length+1];
		for(int x = 0; x < z.length; x++) {
			if(x == 0) {
				v[x] = zweiRes.substring(x, z[x]); // setzt Namen auf den ersten Index
				this.checkIfTyp(v[x]);
			} 
			if(x > 0 && x < z.length) {
				v[x] = zweiRes.substring((z[x -1]+1), z[x]); // setzt Index 2 - 5
				this.checkIfTyp(v[x]);
			}
			if(x == (z.length)) {
				v[x] = zweiRes.substring((z[x-1]+1)); // setzt Zusatz effekt zum letzten Index
				this.checkIfTyp(v[x]);
			}
		}
		this.zweiRes = v;
	}
	public String[] getZweiEff() {
		return zweiEff;
	}
	public void setZweiEff(String zweiEff) throws WrongArgumentException {
		int[] z = new int[0];
		String[] v;
		for(int x = 0; x < zweiEff.length(); x++) {
			if(zweiEff.charAt(x) == 47) {
				z = Arrays.copyOf(z, z.length+1);
				z[z.length-1] = x;
			}
		}
		v = new String[z.length+1];
		for(int x = 0; x < z.length; x++) {
			if(x == 0) {
				
				v[x] = zweiEff.substring(x, z[x]); // setzt Namen auf den ersten Index
				this.checkIfTyp(v[x]);
			} 
			if(x > 0 && x < z.length) {
				v[x] = zweiEff.substring((z[x -1]+1), z[x]); // setzt Index 2 - 5
				this.checkIfTyp(v[x]);
			}
			if(x == (z.length)) {
				v[x] = zweiEff.substring((z[x-1]+1)); // setzt Zusatz effekt zum letzten Index
				this.checkIfTyp(v[x]);
			}
		}
		this.zweiEff = v;
	}
	public void setTyp(String typ) {
		this.typ = typ;
	}

	public String getTyp() {
		return this.typ;
	}
	
	public String[] getImm() {
		return imm;
	}

	public void setImm(String imm) throws WrongArgumentException {
		int[] z = new int[0];
		String[] v;
		for(int x = 0; x < imm.length(); x++) {
			if(imm.charAt(x) == 47) {
				z = Arrays.copyOf(z, z.length+1);
				z[z.length-1] = x;
			}
		}
		v = new String[z.length+1];
		for(int x = 0; x < z.length; x++) {
			if(x == 0) {
				v[x] = imm.substring(x, z[x]); // setzt Namen auf den ersten Index
				this.checkIfTyp(v[x]);
			} 
			if(x > 0 && x < z.length) {
				v[x] = imm.substring((z[x -1]+1), z[x]); // setzt Index 2 - 5
				this.checkIfTyp(v[x]);
			}
			if(x == (z.length)) {
				v[x] = imm.substring((z[x-1]+1)); // setzt Zusatz effekt zum letzten Index
				this.checkIfTyp(v[x]);
			}
		}
		this.imm = v;
	}
	
	public void checkIfTyp(String t) throws WrongArgumentException {
		boolean test = false;
		for(int x = 0; x < Allgemein.typen.length; x++) {
			if(t.equals(Allgemein.typen[x])) {
				test = true;
			}
		}
		if(test = false) {
			throw new WrongArgumentException();
		}
	}
}

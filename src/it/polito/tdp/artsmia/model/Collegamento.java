package it.polito.tdp.artsmia.model;

public class Collegamento {
private int partenza;
private int arrivo;
int peso;



public Collegamento(int partenza, int arrivo, int peso) {
	super();
	this.partenza = partenza;
	this.arrivo = arrivo;
	this.peso = peso;
}





public int getPartenza() {
	return partenza;
}





public int getArrivo() {
	return arrivo;
}





public int getPeso() {
	return peso;
}


}

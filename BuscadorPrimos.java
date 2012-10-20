/*
	NumerosPrimos.java: programa que calcula los números primos
    Copyright (C) 2012  Enrique Benimeli Bofarull
    www.ebenimeli.org

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.	
*/

import java.text.DecimalFormat;

/** Clase NumerosPrimos
 * @author Enrique Benimeli
 * @version 1.0
 */
public class BuscadorPrimos
{  
	private int numIni = 0;
	private int numFin = 100;
	private int intervalo = 1;
	
	private int [] xi,ni;
	private float [] fi,ti;
	
	private int l;

	/** Programa principal
	 * @param args[0]	Último número que se quiere comprobar		
	 */
	public static void main( String args[] ) {
	
		BuscadorPrimos bnp = new BuscadorPrimos(args);
		bnp.buscaPrimos();
		bnp.imprimeTablaDeFrecuencias();	
    }
    
    public BuscadorPrimos(String [] args) {
		inicializaVariables(args);    
    }
    
    private final void inicializaVariables(String [] args) {
    	setNumIni((new Integer(args[0])).intValue());
		setNumFin((new Integer(args[1])).intValue());
		setIntervalo((new Integer(args[2])).intValue());

    
		setL((getNumFin() - getNumIni())/getIntervalo()+1);
		System.out.println("Tamaño del vector: " + getL());
		int tam = getL();
		xi = new int[tam];
		ni = new int[tam];
		fi = new float[tam];
		ti = new float[tam];
    }
    
    private final void buscaPrimos() {
		// tomamos una medida de tiempo inicial
    	long ini = getTiempo();
		// utilizamos 'nprimos' para contar los números primos que encontramos
		int nprimos = 0;
		int x = getNumIni(); int k = 0;
		// Desde 2 hasta 'max', comprobamos si el número es primo
		String sprimos = "";
		int y = getNumIni();
		while( x <= getNumFin() ) {
			for( x=y; x<=(y+getIntervalo()-1); x++ ) {
				if(esPrimo(x)) {
					// si 'x' es primo, lo mostramos por pantalla
					sprimos = sprimos + x + " - ";
					nprimos++;
				}
			}
			xi[k] = x-1;
			ni[k] = nprimos;
			System.out.println(sprimos);
			// tomamos una medida de tiempo final
			long fin = getTiempo();
			// calculamos el tiempo que ha tardado el programa
			// restando el tiempo inicial del final
			long tiempo = fin - ini;
			float frec = ((float)nprimos/(getNumFin()-getNumIni()+1));
			DecimalFormat df = new DecimalFormat("#,##");
			String sfrec = df.format(frec*100);
			// mostramos un mensaje con la cantidad de números primos encontramos y el tiempo invertido
			System.out.println("Entre " + getNumIni() + " - " + (x-1) + " se han encontrado " + nprimos + " números primos (" + sfrec +" %) en " + tiempo + " segundos.");
			System.out.println();
			fi[k] = frec;
			ti[k] = tiempo;
			k++;
			y = x;	
    	}
    }

	/** Función esPrimo: comprueba si un número es primo
	 * @param n		Número que se quiere comprobar (si es primo o no)
	 * @return		Devuelve verdadero si es número es primo, y falso en caso contrario 
	*/
	private boolean esPrimo(int n) {
		if(n<2) return false;
		int i = 2;
		boolean primo = true;
		// mientras no encontremos un divisor, hacer lo siguiente...
		while ((primo) && (i!=n)){
		// si encontramos un divisor de 'n' distinto de 1 o 'n' ...
		if (n % i == 0)
    		// ... entonces no es primo
    		primo = false; // y termina esta parte del programa
    // ... si no, seguimos buscando divisor
    i++;
    }
	// 'n' es primo porque no hemos encontrado
	// divisor distinto de '1' o 'n'
	return primo;
	}
	
	/** Función tiempo: toma una muestra de la hora actual en segundos
	 * return		Devuelve la hora actual en formato de segundos
	*/
	private long getTiempo() {
		return System.currentTimeMillis()/1000;
	}
	
	
	private void imprimeTablaDeFrecuencias() {
		System.out.println("xi" + "\t\t\t" + "ni" + "\t\t\t" + "fi" + "\t\t\t" + "ti");
		for(int i=0; i<getL(); i++) {
		System.out.println(xi[i] + "\t\t\t" + ni[i] + "\t\t\t" + fi[i] + "\t\t\t" + ti[i]);
		}
	}
	

	
	private void setNumIni(int numIni) {
		this.numIni = numIni;
	}

	private int getNumIni() {
		return this.numIni;
	}
	
	private void setNumFin(int numFin) {
		this.numFin = numFin;
	}
	
	private int getNumFin() {
		return this.numFin;
	}

	private void setIntervalo(int intervalo) {
		this.intervalo = intervalo;
	}
	
		private int getIntervalo() {
		return intervalo;
	}
	
	private void setL(int l) {
		this.l = l;
	}
	
	private int getL() {
		return this.l;
	}
	
}

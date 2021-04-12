package it.polito.tdp.regine.model;

import java.util.ArrayList;
import java.util.List;

public class Regine {

	// N è il numero di righe e colonne della scacchiera
	//   (righe e colonne numerate da 0 a N-1)
	// ad ogni livello posizioniamo una regina in una nuova riga
	
	// soluzione parziale: lista delle colonne in cui mettere le regine (prime righe)
	// 		List<Integer>
	// livello = quante righe sono già piene
	// livello = 0 => nessuna riga piena (devo mettere la regina nella riga 0)
	// livello = 3 => 3 righe piene (0, 1, 2), devo mettere la regina nella riga 3
	// [0]
	//     [0, 2]
	//            [0, 2, 1]
	
	private int N;
	private List<List<Integer>> soluzioni;
	
	public List<List<Integer>> risolvi (int N){
		this.N=N;
		
		List<Integer> parziale = new ArrayList<Integer>();
		this.soluzioni=new ArrayList<>();
		cerca(parziale, 0);
		return this.soluzioni;
	}
	
	// cerca = true : trovato; cerca = false : cerca ancora
	private void cerca(List<Integer> parziale, int livello) {
		if(livello==N) {
			// caso terminale
//			System.out.println(parziale);
			this.soluzioni.add(new ArrayList<>(parziale));
		} else {
			for(int colonna=0; colonna<N; colonna++) {
				// if la mossa nella casella [livello][colonna] è valida
				// se sì, aggiungi a parziale e fai ricorsione
				
				if(posValida(parziale, colonna)) {
					
					// versione senza backtracking (sconsigliabile perché deve creare a ogni iterazione devo creare una lista nuova)
					// NB negli anagrammi andava bene perché si lavorava su una stringa, le stringhe sono immutabili invece le liste no
//					List<Integer> parzialeNuovo = new ArrayList<>(parziale);
//					parzialeNuovo.add(colonna);
//					cerca(parzialeNuovo, livello+1);
					
					// versione con backtracking
					parziale.add(colonna);
					cerca(parziale, livello +1);					
					parziale.remove(parziale.size()-1); //backtracking  (così quando risalgo di livello tolgo l'ultimo aggiunto per poterne provare un altro)
				}
			}
		}
	}


	private boolean posValida(List<Integer> parziale, int colonna) {
		int livello=parziale.size();
		// controllo riga non serve per come abbiamo strutturato la soluzione
		// controlla se viene mangiata in verticale
		if(parziale.contains(colonna)) {
			return false;
		}
		
		// controlla se viene mangiata in diagonale: confronta la posizione(livello, colonna) con (r,c) delle regine esistenti
		for(int r=0; r<livello; r++) {
			int c = parziale.get(r);
			
			if(r+c==livello+colonna || r-c==livello-colonna) {
				return false;
			}
		}
		
		return true;
	}
	
	
	
}

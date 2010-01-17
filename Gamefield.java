
/* A causa di un deficit di Java non posso usare questa interfaccia all'interno del programma,
 * ma la lascio in modo da poter separare la descrizione dei metodi dalla loro implementazione.
 * Tutti i metodi seguenti sono definiti come STATICI nella classe Arcade,
 * quindi, ad esempio, il metodo getWidth() di questa interfaccia va chiamato, nel programma,
 * usando Arcade.getWidth().
 */

public abstract class Gamefield {
	/* Questi metodi permettono di ottenere informazioni sulle dimensioni del campo di gioco.
	 * Il loro nome dovrebbe lasciar intuire abbastanza facilmente la loro funzione
	 */
	public abstract int getWidth ();
	public abstract int getHeight ();
	
	/* Questo metodo permette di ottenere la coordinata x del giocatore.
	 * La coordinata y non è accessibile in quanto non è ritenuta necessaria.
	 */
	public abstract int getPlayerPosition ();
	
	/* Questo metodo permette di sparare.
	 * Lo sparo avrà origine nelle coordinate specificate e avanzerà del numero di speed pixel ogni frame.
	 * Non è possibile controllare lo sparo una volta emesso.
	 * Non è possibile sparare in nessun'altra direzione esclusa quella frontale.
	 */
	public abstract void shoot (int x, int y, int speed);
	
	/* Questo metodo aggiunge un nemico al campo di gioco.
	 * Anche se sconsigliato, può essere usato dai nemici per simulare una "duplicazione":
	 * creano un nuovo nemico identico a loro stessi.
	 */
	public abstract void addEnemy (Enemy enemy);
	
	/* Questo metodo elimina il nemico specificato dal campo di gioco.
	 * Una volta rimosso non verranno più chiamato nessun metodo su di esso,
	 * quindi la rimozione va fatta dopo che l'eventuale animazione di morte si è conclusa.
	 */
	public abstract void removeEnemy (Enemy enemy);
	
	/* Questo metodo assegna al giocatore i punti di uccisione per il nemico specificato.
	 * Tuttavia il nemico non viene rimosso dal campo di gioco,
	 * e quindi ha l'occasione di mostrare un'animazione per simulare la morte.
	 * Per la rimozione è da usare il metodo removeEnemy.
	 */
	public abstract void killEnemy (Enemy enemy);
	
	/* Questo metodo va chiamato quando il nemico supera la fine del campo,
	 * cioè quando ha superato indenne il giocatore.
	 * Questo provoca la perdita di un punto vita del giocatore.
	 * Anche in questo caso il metodo removeEnemy va chiamato a parte.
	 */
	public abstract void survivedEnemy (Enemy enemy);
}

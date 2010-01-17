import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public abstract class Enemy {
	/* Questi due metodi devono restituire il punteggio che verrà attribuito al giocatore
	 * rispettivamente ogni volta che colpirà il nemico e quando lo ucciderà.
	 * Questi metodi possono anche restituire zero, come ad esempio nel caso di nemici che muoiono al primo colpo,
	 * o di nemici che non vogliono assegnare punti quando vengono colpiti ma solo quando muoiono.
	 * Idealmente è possibile anche restituire dei valori negativi, in modo da far perdere punti, ma è sconsigliato.
	 */
	public abstract int getHitPoints ();
	public abstract int getKillPoints ();
	
	/* Questo metodo verrà chiamato una volta ad ogni frame
	 * Ha il compito di muovere il nemico, se lo si ritiene necessario.
	 * La posizione del nemico va memorizzata all'interno dello stesso, tanto che il gamefield non ne sarà a conoscenza.
	 * Per decidere se e come muoversi ci si può basare su vari fattori:
	 * - Si può inseguire il giocatore
	 * - Si può attraversare il campo orizzontalmente
	 * - Ci si può avvicinare al giocatore
	 * - Ci si può muovere a schieramento, insieme ad altri nemici
	 * - Ci si può teletrasportare da una parte all'altra del campo.
	 * e altri ancora...
	 */
	public abstract void move ();
	
	/* Questo metodo verrà chiamato una volta ad ogni frame
	 * In questo metodo si devono compiere le operazioni di disegno per rappresentare il nemico sul campo da gioco.
	 * L'aspetto, le dimensioni e la posizione del nemico sono decise unicamente dal nemico stesso.
	 * Questo implica che, dato che non si è a conoscenza della posizione degli altri nemici, può avvenire una sovrapposizione.
	 * In alcuni casi, come ad esempio la creazione, la morte o il teletrasporto, può essere opportuno non rappresentare
	 * il nemico stesso ma magari un'animazione.
	 * Il parametro alpha indica la trasparenza del nemico, che è tenuto a rispettarla.
	 * Per imostare la trasparenza basta passare il parametro alpha come quarto parametro al costruttore di un nuovo Color.
	 */
	public abstract void paint (Graphics2D g, int alpha);
	
	/* Questo metodo verrà chiamato una volta ad ogni frame
	 * Ha il compito di valutare se è il caso di sparare.
	 * Questa decisione può essere presa basandosi su vari fattori:
	 * - Lo sparo deve avvenire con una certa frequenza, quindi non si può sparare ad ogni frame.
	 * - Si può decidere di sparare solo se si è di fronte al giocatore
	 * - ecc...
	 * Nel caso si decida di sparare si deve chiamare il metodo shoot dell'Arcade,
	 * passandogli come parametri 3 valori interi: la posizione x e y dell'origine dello sparo,
	 * e la velocità (positiva) con cui questo si propaga
	 * Dopo essere stato emesso lo sparo verrà gestito esclusivamente dal gamefield.
	 */
	public abstract void shoot ();
	
	/* Questo metodo verrà chiamato una volta per ogni proiettile ad ogni frame.
	 * Il metodo deve determinare se il proiettlie, che si trova nelle coordinate passate come parametri,
	 * ha colpito il nemico (rappresentato dall'oggetto stesso).
	 * Lo sparo è rappresentato da un oggetto Rectangle2D, che rappresenta l'area percorsa dallo sparo
	 * tra il frame precedente e quello attuale. Il nemico deve considerarsi colpito se interseca questo rettangolo.
	 * Se si viene colpiti occorre passare true come valore di ritorno, in modo da interrompere il moto del proiettile
	 * (che altrimenti proseguirebbe e potrebbe colpire altri nemici) e in modo da assegnare al giocatore i punti per il colpo.
	 * Tuttavia venire colpiti non implica la morte. Questa va gestita autonomamente dall'oggetto.
	 * Per segnalare la propria morte all'Arcade occorre chiamare il suo metodo killEnemy,
	 * passando come parametro un riferimento a se stessi, cioè this.
	 * Successivamente è possibile aggiungere un'animazione per simulare la morte.
	 * Una volta conclusa anche questa, si deve chiamare il metodo removeEnemy dell'Arcade,
	 * che rimuove definitivamente il nemico dal campo di gioco.
	 */
	public abstract boolean hit (Rectangle2D shot);	
}

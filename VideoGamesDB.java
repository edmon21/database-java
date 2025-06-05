import java.io.RandomAccessFile;
import java.io.IOException;

public class VideoGamesDB {

	private RandomAccessFile videoGamesDB;
	private int numVideoGames;

	public VideoGamesDB (String fileName) throws IOException {
		videoGamesDB = new RandomAccessFile (fileName, "rw");
		numVideoGames = (int)videoGamesDB.length() / VideoGameInfo.SIZE;
	}

	public int getNumVideoGames() {
		return numVideoGames;
	}

	public void close() throws IOException {
		videoGamesDB.close();
	}

	public void reset() throws IOException {
		videoGamesDB.setLength (0);
		numVideoGames = 0;
	}

	public VideoGameInfo readVideoGameInfo(int posicio) throws IOException {
		// comprovem que la posicio sigui valida
		if (posicio < 0 || posicio >= numVideoGames) {
			throw new IndexOutOfBoundsException("index fora de limits: " + posicio);
		}

		// array de bytes on es guardara la informacio del videojoc
		byte[] buffer = new byte[VideoGameInfo.SIZE];

		// ens situem a la posicio exacta dins del fitxer
		videoGamesDB.seek(posicio * VideoGameInfo.SIZE);

		// llegim exactament els bytes corresponents al videojoc
		videoGamesDB.readFully(buffer);

		// desempaquetem els bytes per construir l'objecte
		return VideoGameInfo.fromBytes(buffer);
	}

	public int searchVideoGame(String text) throws IOException {
		text = text.toLowerCase(); // passem a minuscules per comparar millor
		int indexParcial = -1;

		for (int i = 0; i < numVideoGames; i++) {
			VideoGameInfo joc = readVideoGameInfo(i);
			String titol = joc.getTitle().toLowerCase();

			if (titol.equals(text)) {
				return i; // coincidencia exacta trobada
			} else if (titol.contains(text) && indexParcial == -1) {
				indexParcial = i; // guardem primera coincidencia parcial
			}
		}

		return indexParcial; // retornem coincidencia parcial o -1 si no n'hi ha
	}

	public void writeVideoGameInfo(int posicio, VideoGameInfo joc) throws IOException {
		if (posicio < 0 || posicio > numVideoGames) {
			throw new IndexOutOfBoundsException("index negatiu o massa gran: " + posicio);
		}

		// convertim l'objecte a array de bytes
		byte[] buffer = joc.toBytes();

		// escrivim els bytes a la posicio corresponent del fitxer
		videoGamesDB.seek(posicio * VideoGameInfo.SIZE);
		videoGamesDB.write(buffer);
	}

	public void appendVideoGameInfo (VideoGameInfo vgi) throws IOException {
		writeVideoGameInfo (numVideoGames, vgi);
		numVideoGames++;
	}

	public boolean deleteVideoGame(String text) throws IOException {
		int posicio = searchVideoGame(text);

		if (posicio == -1) return false; // no trobat

		// si no es l'ultim videojoc, el sobreescrivim amb l'ultim
		if (numVideoGames > 1 && posicio != numVideoGames - 1) {
			VideoGameInfo ultim = readVideoGameInfo(numVideoGames - 1);
			writeVideoGameInfo(posicio, ultim);
		}

		// reduim la mida del fitxer
		numVideoGames--;
		videoGamesDB.setLength(numVideoGames * VideoGameInfo.SIZE);

		return true;
	}

}
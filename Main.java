import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import acm.program.ConsoleProgram;

public class Main extends ConsoleProgram {

	private static final String VIDEOGAMES_FILES   = "videogames.txt";
	private static final String VIDEOGAMES_DB_NAME = "videogamesDB.dat";
	private VideoGamesDB videoGamesDB;

	public void run() {
		setFont ("*-*-24");
		try {
			videoGamesDB = new VideoGamesDB (VIDEOGAMES_DB_NAME);
			loadFromFiles();
		} catch (IOException ex) {
			println ("Error generant base de dades!");
			System.exit (-1);
		}
		for (;;) {
			printMenu();
			int option = getOption();
			switch (option) {
				case 1:
					listTitles();
					break;
				case 2:
					infoFromOneVideoGame();
					break;
				case 3:
					deleteVideoGame();
					break;
				case 4:
					quit();
					break;
			}
			println();
		}
	}

	private void printMenu() {
		println ("Menú d'opcions:");
		println ("1 - Llista els títols dels videojocs.");
		println ("2 - Mostra la informació d'un videojoc.");
		println ("3 - Elimina un videojoc.");
		println ("4 - Sortir.");
	}

	private int getOption() {
		int option;
		do {
			option = readInt ("Escull una opció: ");
		} while (option <= 0 || option > 4);
		return option;
	}

	private void loadFromFiles() throws IOException {
		videoGamesDB.reset();
		BufferedReader input = new BufferedReader (
		                       new FileReader (VIDEOGAMES_FILES));
		String fileName = input.readLine();
		while (fileName != null) {
			try {
				VideoGameInfo vgi = VideoGameInfoReader.readVideoGameFile (fileName);
				videoGamesDB.appendVideoGameInfo (vgi);
			}
			catch (IOException ioe) {
				println (ioe + System.lineSeparator());
			}
			fileName = input.readLine();
		}
		input.close();
	}

	private void listTitles() {
		int numVideoGames = videoGamesDB.getNumVideoGames();
		println();
		try {
			for (int i = 0; i < numVideoGames; i++) {
				VideoGameInfo vgi = videoGamesDB.readVideoGameInfo (i);
				println (vgi.getTitle());
			}
		} catch (IOException ex) {
			println ("Error a la base de dades!");
		}
	}

	private void infoFromOneVideoGame() {
		String str = readLine ("Escriu títol o part del títol del videojoc: ");
		try {
			int n = videoGamesDB.searchVideoGame (str);
			if (n >= 0) {
				VideoGameInfo vgi = videoGamesDB.readVideoGameInfo (n);
				println (vgi);
			} else {
				println ("Videojoc no trobat.");
			}
		} catch (IOException ex) {
			println ("Error a la base de dades!");
		}
	}

	private void deleteVideoGame() {
		String str = readLine ("Escriu títol o part del títol del videojoc: ");
		try {
			boolean success = videoGamesDB.deleteVideoGame (str);
			if (!success) {
				println ("Videojoc no trobat.");
			}
		} catch (IOException ex) {
			println ("Error a la base de dades!");
		}
	}

	private void quit() {
		try {
			videoGamesDB.close();
			System.exit (0);
		} catch (IOException ex) {
			println ("Error tancant base de dades!");
			System.exit (-1);
		}
	}

}

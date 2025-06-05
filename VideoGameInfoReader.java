import java.io.*;

public class VideoGameInfoReader {

	public static VideoGameInfo readVideoGameFile(String nomFitxer) throws IOException {

		BufferedReader lector = new BufferedReader(new FileReader(nomFitxer));

		String titol = "";
		String serie = "";
		String editor = "";
		short any = -1;
		int vendes = -1;

		try {
			// llegim titol (obligatori)
			titol = lector.readLine();
			if (titol == null || titol.isEmpty()) {
				System.err.println("titol buit al fitxer " + nomFitxer);
				throw new IOException("titol buit al fitxer " + nomFitxer);
			}

			// llegim serie
			serie = lector.readLine();
			if (serie == null) serie = "";

			// llegim editor
			editor = lector.readLine();
			if (editor == null || editor.isEmpty()) {
				System.err.println("editor buit al fitxer " + nomFitxer);
				editor = "";
			}

			// llegim any
			String liniaAny = lector.readLine();
			if (liniaAny == null || liniaAny.isEmpty()) {
				System.err.println("any buit al fitxer " + nomFitxer);
			} else {
				try {
					any = Short.parseShort(liniaAny.trim());
				} catch (NumberFormatException e) {
					System.err.println("any incorrecte al fitxer " + nomFitxer);
				}
			}

			// llegim vendes
			String liniaVendes = lector.readLine();
			if (liniaVendes == null || liniaVendes.isEmpty()) {
				System.err.println("vendes buides al fitxer " + nomFitxer);
			} else {
				try {
					vendes = Integer.parseInt(liniaVendes.trim());
				} catch (NumberFormatException e) {
					System.err.println("vendes incorrectes al fitxer " + nomFitxer);
				}
			}

		} finally {
			lector.close();
		}

		return new VideoGameInfo(titol, serie, editor, any, vendes);
	}
}

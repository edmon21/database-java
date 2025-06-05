public class VideoGameInfo {

	private String title;
	private String series;
	private String publisher;
	private  short year;
	private    int sales;

	private static final int     TITLE_LIMIT = 24;
	private static final int    SERIES_LIMIT = 19;
	private static final int PUBLISHER_LIMIT = 18;

	public  static final int SIZE = TITLE_LIMIT * 2 + SERIES_LIMIT * 2 + PUBLISHER_LIMIT * 2 + 2 + 4;


	public VideoGameInfo (String title, String series, String publisher,
						  short year, int sales) {
		this.title     = title;
		this.series    = series;
		this.publisher = publisher;
		this.year      = year;
		this.sales     = sales;
	}

	// Getters
	public String getTitle     () { return title;     }
	public String getSeries    () { return series;    }
	public String getPublisher () { return publisher; }
	public  short getYear      () { return year;      }
	public    int getSales     () { return sales;     }

	public byte[] toBytes() {
		// creem un array de bytes on guardarem tota la informacio
		byte[] registre = new byte[SIZE];
		int posicio = 0;

		// empaquetem el titol
		PackUtils.packString(getTitle(), TITLE_LIMIT, registre, posicio);
		posicio += TITLE_LIMIT * 2;

		// empaquetem la serie
		PackUtils.packString(getSeries(), SERIES_LIMIT, registre, posicio);
		posicio += SERIES_LIMIT * 2;

		// empaquetem l'editor
		PackUtils.packString(getPublisher(), PUBLISHER_LIMIT, registre, posicio);
		posicio += PUBLISHER_LIMIT * 2;

		// empaquetem l'any
		PackUtils.packShort(getYear(), registre, posicio);
		posicio += 2;

		// empaquetem les vendes
		PackUtils.packInt(getSales(), registre, posicio);

		return registre;
	}


	public static VideoGameInfo fromBytes(byte[] registre) {
		int posicio = 0;

		// desempaquetem el titol
		String titol = PackUtils.unpackString(TITLE_LIMIT, registre, posicio);
		posicio += TITLE_LIMIT * 2;

		// desempaquetem la serie
		String serie = PackUtils.unpackString(SERIES_LIMIT, registre, posicio);
		posicio += SERIES_LIMIT * 2;

		// desempaquetem l'editor
		String editor = PackUtils.unpackString(PUBLISHER_LIMIT, registre, posicio);
		posicio += PUBLISHER_LIMIT * 2;

		// desempaquetem l'any
		short any = PackUtils.unpackShort(registre, posicio);
		posicio += 2;

		// desempaquetem les vendes
		int vendes = PackUtils.unpackInt(registre, posicio);

		return new VideoGameInfo(titol, serie, editor, any, vendes);
	}

	public String toString() {
		String ls = System.lineSeparator();
		String result = title;
		if (!series.isEmpty()) {
			result += " (sèrie " + series + ")";
		}
		if (!publisher.isEmpty() || year >= 0) {
			result += ls + "Publicat";
			if (!publisher.isEmpty()) {
				result += " per " + publisher;
			}
			if (year >= 0) {
				result += " en l'any " + year;
			}
			result += ".";
		}
		if (sales >= 0) {
			result += ls + "Ha venut aproximadament " + sales + " còpies.";
		}
		return result;
	}

}
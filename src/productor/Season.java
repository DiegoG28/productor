package productor;

public class Season {
	String season = "primavera";

	public void changeSeason() {
		if (season.equals("primavera")) {
			season = "verano";
		} else if (season.equals("verano")) {
			season = "otoño";
		} else if (season.equals("otoño")) {
			season = "invierno";
		} else if (season.equals("invierno")) {
			season = "primavera";
		}
	}

	public String getSeason() {
		return season;
	}
}

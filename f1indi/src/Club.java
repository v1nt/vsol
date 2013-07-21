import java.util.*;

public class Club implements Comparable<Club> {

	private static final String CELL_HEADER = "[td]";
	private static final String CELL_SPLITTER = "[/td][td]";
	private static final String CELL_FOOTER = "[/td]";

	private boolean pro;
	private final int id;
	private String name;
	private String division;
	private List<Match> matches;
	private Championship champ;
	private int points = 0;

	public Club(int id, String name, String div, List<Match> matches) {
		this.id = id;
		this.name = name;
		division = div;
		if("clc".equals(div)) {
			pro = false;
		} else {
			pro = true;
		}
		this.matches = matches;
		champ = new Championship(matches);
	}
	
	
	public boolean isPro() {
		return pro;
	}

	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDivision() {
		return division;
	}

	public List<Match> getMatches() {
		return matches;
	}

	public Championship getChamp() {
		return champ;
	}
	
	public int getPoints() {
		int points = 0;
		for(int raceIndex = 0; raceIndex < champ.getFinishedRacesNumber(); raceIndex++) {
			points += champ.getRaces().get(raceIndex).getPoints();
		}
		return points;
	}

	public void setPro(boolean pro) {
		this.pro = pro;
	}
	
	public String getUrl() {
		return Constants.URL_CLUB + "&filter=" + (pro ? "3" : "10");
	}



	public void calcChamp() {
		champ.calc();
	}

	public Lap getLastLap() {
		return champ.getLastRace().getLastLap();
	}

	public Race getLastRace() {
		return champ.getLastRace();
	}
	
	public void addPoints(int points) {
		this.points += points;
	}
	
	public String getClubInfoRow() {
		StringBuilder sb = new StringBuilder(CELL_HEADER);
		sb.append("[url=");
		sb.append(Constants.URL_CLUB);
		sb.append(id);
		sb.append("&filter=");
		if(pro) {
			sb.append("3");
		} else {
			sb.append("10");
		}
		sb.append("]");
		sb.append(name);
		sb.append("[/url]");
		sb.append(CELL_FOOTER);

		return sb.toString();		

	}

	public String getLapRow(Lap lap) {
		StringBuilder sb = new StringBuilder(CELL_HEADER);
		sb.append("[url=");
		sb.append(lap.getMatch().getUrl());
		sb.append("]");

		if(lap.getMatch().isAs()) {
			sb.append("*");
		} else {
			sb.append(lap.getMatch().getScoredGoals());
			sb.append(" - ");
			sb.append(lap.getMatch().getAgainstGoals());
		}
		sb.append("[/url]");

		sb.append(CELL_SPLITTER);
		int tmp = lap.getMatch().getPlus();
		if(tmp == 0) {
			sb.append("-");
		} else {
			sb.append(tmp);
		}
		sb.append(CELL_SPLITTER);
		tmp = lap.getMatch().getMinus();
		if(tmp == 0) {
			sb.append("-");
		} else {
			sb.append(tmp);
		}
		sb.append(CELL_SPLITTER);
		sb.append("[img]http://www.virtualsoccer.ru/styles/");
		sb.append(lap.getMatch().getStyle());
		sb.append(".png[/img]");
		sb.append(CELL_SPLITTER);
		sb.append("[img]http://www.virtualsoccer.ru/styles/");
		sb.append(lap.getMatch().getEnemyStyle());
		sb.append(".png[/img]");
		sb.append(CELL_SPLITTER);
		sb.append(lap.getSpeed());
		sb.append(CELL_FOOTER);

		return sb.toString();		
	}


	public String getLastRaceRow() {
		StringBuilder sb = new StringBuilder(getClubInfoRow());
		for(Lap lap : getLastRace().getLaps()) {
			sb.append(CELL_HEADER);
			sb.append((lap.getSpeed() == 0 ? "O" : lap.getSpeed()));
			sb.append(CELL_FOOTER);
		}
		sb.append(CELL_HEADER);
		sb.append(getLastRace().getSpeed());
		sb.append(CELL_SPLITTER);
		sb.append(getLastRace().getPluses());
		sb.append(CELL_SPLITTER);
		sb.append(getLastRace().getMinuses());
		sb.append(CELL_SPLITTER);
		sb.append(getLastRace().getSummary());
		sb.append(CELL_FOOTER);
		
		if(getLastRace().getLaps().size() == 3) {
			sb.append(CELL_HEADER);
			sb.append(getLastRace().getPoints());
			sb.append(CELL_FOOTER);
		}


		return sb.toString();		
	}
	
	public String getLastLapRow() {
		StringBuilder sb = new StringBuilder(getClubInfoRow());
		sb.append(getLapRow(getLastLap()));

		return sb.toString();		
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("club id: ");
		sb.append(id);
		sb.append(" \"");
		sb.append(name);
		sb.append("\" division: ");
		sb.append(division);
		sb.append("\n");
		for(Match game : matches) {
			sb.append("match id=");
			sb.append(game.getId());
			sb.append(" day=");
			sb.append(game.getDay());
			sb.append(" isHome: ");
			sb.append(game.isHome());
			sb.append(" points=");
			sb.append(game.getResult());
			sb.append(" GS=");
			sb.append(game.getScoredGoals());
			sb.append(" GA=");
			sb.append(game.getAgainstGoals());
			sb.append(" +");
			sb.append(game.getPlus());
			sb.append(" -");
			sb.append(game.getMinus());
			sb.append(" style: ");
			sb.append(game.getStyle());
			sb.append(" enemyStyle: ");
			sb.append(game.getEnemyStyle());
			sb.append("\n");
		}
		sb.append(champ);
		return sb.toString();		
		
	}


	public int compareTo(Club club) {
		return (int) Math.signum(
				club.getLastLap().getSpeed() - getLastLap().getSpeed());
	}
	
}

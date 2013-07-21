import java.util.*;

public class Championship {

	public static final int LAPS_PER_RACE = 3;

	private List<Race> races;
	private List<Match> matches;

	public Championship(List<Match> matches) {
		this.matches = matches;
	}


	public List<Race> getRaces() {
		return races;
	}

	public Race getLastRace() {
		return races.get(races.size() - 1);
	}


	public int getFinishedRacesNumber() {
		return (getLastRace().isFinished() ? races.size() : races.size() - 1); 
	}

	public void calc() {
		races = new ArrayList<Race>();

		int racesNumber = matches.size() / LAPS_PER_RACE;
		for(int raceNumber = 1; raceNumber <= racesNumber; raceNumber++) {
			races.add(new Race(matches.subList(
					LAPS_PER_RACE * (raceNumber - 1), 
					LAPS_PER_RACE * raceNumber
			)));
		}

		if(matches.size() % LAPS_PER_RACE != 0) {
			races.add(new Race(matches.subList(
					LAPS_PER_RACE * racesNumber, 
					matches.size()
			)));
		}
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("championship\n");
		for(Race race : races) {
			sb.append(race);
		}
		return sb.toString();		
	}



}

import java.util.*;

public class Race {
	private boolean finished;
	private List<Lap> laps;

	public Race(List<Match> matches) {
		finished = (matches.size() == Championship.LAPS_PER_RACE);
		laps = new ArrayList<Lap>();
		for(Match match : matches) {
			laps.add(new Lap(match));
		}
	}

	public boolean isFinished() {
		return finished;
	}

	public List<Lap> getLaps() {
		return laps;
	}

	public Lap getLastLap() {
		return laps.get(laps.size() - 1);
	}


	public int getSpeed() {
		int speed = 0;
		for(Lap lap : laps) {
			speed += lap.getSpeed();
		}
		return speed;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Lap lap : laps) {
			sb.append(lap);
		}
		sb.append(getSpeed());
		sb.append("\n");
		return sb.toString();		
	}
}

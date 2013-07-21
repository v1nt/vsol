import java.util.*;

public class Race {
	private int points = 0;
	private boolean finished;
	private List<Lap> laps;

	public Race(List<Match> matches) {
		finished = (matches.size() == Championship.LAPS_PER_RACE);
		laps = new ArrayList<Lap>();
		for(Match match : matches) {
			laps.add(new Lap(match));
		}
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	public int getPoints() {
		return points;
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
	
	public int getBestLapSpeed() {
		int bestSpeed = -15;
		for(Lap lap : getLaps()) {
			int lapSpeed = lap.getSpeed(); 
			if(lapSpeed > bestSpeed) {
				bestSpeed = lapSpeed;
			}
		}
		return bestSpeed;
	}
	
	public int getPlusMinusDif() {
		int plusMinusDif = 0;
		for(Lap lap : getLaps()) {
			plusMinusDif += lap.getPlusMinusDif();
		}
		return plusMinusDif;
	}
	
	public int getPluses() {
		int pluses = 0;
		for(Lap lap : getLaps()) {
			pluses += lap.getMatch().getPlus();
		}
		return pluses;
	}
	
	public int getMinuses() {
		int minuses = 0;
		for(Lap lap : getLaps()) {
			minuses += lap.getMatch().getMinus();
		}
		return minuses;
		
	}
	
	public int getResult() {
		int result = 0;
		for(Lap lap : getLaps()) {
			result += lap.getMatch().getResult();
		}
		return result;
	}
	
	public int getGoalsDif() {
		int goalsDif = 0;
		for(Lap lap : getLaps()) {
			goalsDif += lap.getGoalsDif();
		}
		return goalsDif;
	}
	
	public int getScoredGoals() {
		int scoredGoals = 0;
		for(Lap lap : getLaps()) {
			scoredGoals += lap.getMatch().getScoredGoals();
		}
		return scoredGoals;
	}
	
	public int getAgainstGoals() {
		int againstGoals = 0;
		for(Lap lap : getLaps()) {
			againstGoals += lap.getMatch().getAgainstGoals();
		}
		return againstGoals;
	}
	
	public String getSummary() {
		StringBuilder sb = new StringBuilder();
		sb.append(getScoredGoals());
		sb.append(" - ");
		sb.append(getAgainstGoals());
		return sb.toString();
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

import java.util.Comparator;


public class RaceComparator implements Comparator<Team> {
	
	private int race;

	public RaceComparator(int raceIndex) {
		// TODO Auto-generated constructor stub
		race = raceIndex;
	}

	@Override
	public int compare(Team team1, Team team2) {
		// TODO Auto-generated method stub
		if(team2.getRaceSpeed(race) != team1.getRaceSpeed(race)) {
			return team2.getRaceSpeed(race) - team1.getRaceSpeed(race);
		} else if (team2.getBestSpeed(race) != team1.getBestSpeed(race)) {
			return team2.getBestSpeed(race) - team1.getBestSpeed(race);
		} else if (team2.getPlusMinusDif(race) != team1.getPlusMinusDif(race)) {
			return team2.getPlusMinusDif(race) - team1.getPlusMinusDif(race);
		} else if (team2.getPluses(race) != team1.getPluses(race)) {
			return team2.getPluses(race) - team1.getPluses(race);
		} else if (team2.getResult(race) != team1.getResult(race)) {
			return team2.getResult(race) - team1.getResult(race);
		} else if (team2.getGoalsDif(race) != team1.getGoalsDif(race)) {
			return team2.getGoalsDif(race) - team1.getGoalsDif(race);
		} else {
			return team2.getScoredGoals(race) - team1.getScoredGoals(race);			
		}
	}

}

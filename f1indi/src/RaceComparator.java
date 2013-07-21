import java.util.Comparator;


public class RaceComparator implements Comparator<Club> {
	
	private int raceIndex;

	public RaceComparator(int raceIndex) {
		// TODO Auto-generated constructor stub
		this.raceIndex = raceIndex;
	}
	
	@Override
	public int compare(Club club1, Club club2) {
		// TODO Auto-generated method stub
		
		Race race1 = club1.getChamp().getRaces().get(raceIndex);
		Race race2 = club2.getChamp().getRaces().get(raceIndex);
		
		if(race2.getSpeed() != race1.getSpeed()) {
			return race2.getSpeed() - race1.getSpeed();
		} else if (race2.getBestLapSpeed() != race1.getBestLapSpeed()) {
			return race2.getBestLapSpeed() - race1.getBestLapSpeed();
		} else if (race2.getPlusMinusDif() != race1.getPlusMinusDif()) {
			return race2.getPlusMinusDif() - race1.getPlusMinusDif();
		} else if (race2.getPluses() != race1.getPluses()) {
			return race2.getPluses() - race1.getPluses();
		} else if (race2.getResult() != race1.getResult()) {
			return race2.getResult() - race1.getResult();
		} else if (race2.getGoalsDif() != race1.getGoalsDif()) {
			return race2.getGoalsDif() - race1.getGoalsDif();
		} else {
			return race2.getScoredGoals() - race1.getScoredGoals();
		}
	}

}

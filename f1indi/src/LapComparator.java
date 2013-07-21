import java.util.Comparator;


public class LapComparator implements Comparator<Club> {
	
	private int race;
	private int lap;

	public LapComparator(int raceIndex, int lapIndex) {
		// TODO Auto-generated constructor stub
		this.race = raceIndex;
		this.lap = lapIndex;
	}

	@Override
	public int compare(Club club1, Club club2) {
		// TODO Auto-generated method stub
		
		Lap lap1 = club1.getChamp().getRaces().get(race).getLaps().get(lap);
		Lap lap2 = club2.getChamp().getRaces().get(race).getLaps().get(lap);

		if(lap2.getSpeed() != lap1.getSpeed()) {
			return lap2.getSpeed() - lap1.getSpeed();
		} else if (lap2.getPlusMinusDif() != lap1.getPlusMinusDif()) {
			return lap2.getPlusMinusDif() - lap1.getPlusMinusDif();
		} else if (lap2.getMatch().getPlus() != lap1.getMatch().getPlus()) {
			return lap2.getMatch().getPlus() - lap1.getMatch().getPlus();
		} else if (lap2.getMatch().getResult() != lap1.getMatch().getResult()) {
			return lap2.getMatch().getResult() - lap1.getMatch().getResult();
		} else if (lap2.getGoalsDif() != lap1.getGoalsDif()) {
			return lap2.getGoalsDif() - lap1.getGoalsDif();
		} else {
			return lap2.getMatch().getScoredGoals() 
					- lap1.getMatch().getScoredGoals();
		}
	}

}

import java.util.Comparator;


public class RaceComparator implements Comparator<Team> {
	
	private int raceIndex;

	public RaceComparator(int raceIndex) {
		// TODO Auto-generated constructor stub
		this.raceIndex = raceIndex;
	}

	@Override
	public int compare(Team arg0, Team arg1) {
		// TODO Auto-generated method stub
		if(arg1.getRaceSpeed(raceIndex) != arg0.getRaceSpeed(raceIndex)) {
			return arg1.getRaceSpeed(raceIndex) - arg0.getRaceSpeed(raceIndex);
		} else {
			return arg1.getBestSpeed(raceIndex) - arg0.getBestSpeed(raceIndex);
		}
	}

}

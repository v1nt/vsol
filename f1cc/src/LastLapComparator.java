import java.util.Comparator;

public class LastLapComparator implements Comparator<Team> {
	public int compare(Team team1, Team team2) {
		return team2.getLastLapSpeed() - team1.getLastLapSpeed();
	}
}

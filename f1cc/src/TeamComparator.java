import java.util.Comparator;


public class TeamComparator implements Comparator<Team> {

	public TeamComparator() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compare(Team team1, Team team2) {
		// TODO Auto-generated method stub
		return team2.getPoints() - team1.getPoints();
	}

}

import java.util.Comparator;


public class ClubComparator implements Comparator<Club> {

	public ClubComparator() {
		// TODO Auto-generated constructor stub
	}
	
	public int compare(Club club1, Club club2) {
		return club2.getPoints() - club1.getPoints();
	}

}

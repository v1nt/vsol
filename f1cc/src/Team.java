import java.util.List;
import java.util.ArrayList;

public class Team {
	private String name;
	private Club[] clubs = new Club[2];
	private List<Integer> racePoints = new ArrayList<Integer>();

	public Team(String name, Club club1, Club club2) {
		this.name = name;
		clubs[0] = club1;
		clubs[1] = club2;
		club1.setTeam(this);
		club2.setTeam(this);
	}

	public String getName() {
		return name;
	}

	public Club[] getClubs() {
		return clubs;
	}

	public int getPoints() {
		int points = 0;
		for(Integer racePoint : racePoints) {
			points += racePoint;
		}
		return points;
	}
	
	public List<Integer> getRacePoints() {
		return racePoints;
	}
	
	
	public int getBestSpeed(int raceIndex) {
		int bestSpeed = -30;
		int lapsNumber = clubs[0].getChamp().getRaces().get(raceIndex).getLaps().size();
			for(int lapIndex = 0; lapIndex < lapsNumber; lapIndex++) {
				int lapSpeed = 0;
				for(int clubIndex = 0; clubIndex < 2; clubIndex++) {
					lapSpeed += clubs[clubIndex].getChamp().getRaces().get(raceIndex).getLaps().get(lapIndex).getSpeed();
				}
				if(lapSpeed > bestSpeed) {
					bestSpeed = lapSpeed;
				}
			}
		return bestSpeed;
	}
	
	public int getLastLapSpeed() {
		return clubs[0].getLastLap().getSpeed() + clubs[1].getLastLap().getSpeed();
	}
	
	public int getRaceSpeed(int raceIndex) {
		return clubs[0].getChamp().getRaces().get(raceIndex).getSpeed() 
				+ clubs[1].getChamp().getRaces().get(raceIndex).getSpeed(); 
	}
	
	public int getLastRaceSpeed() {
		return clubs[0].getLastRace().getSpeed() 
				+ clubs[1].getLastRace().getSpeed(); 
	}
	
	public int[] getLastRaceSpeeds() {
		int lapsNumber = clubs[0].getLastRace().getLaps().size();
		int[] speeds = new int[lapsNumber];
		for(int currentLap = 0; currentLap < lapsNumber; currentLap++) {
			speeds[currentLap] = 0;
			for(int currentClub = 0; currentClub < 2; currentClub++) {
				speeds[currentLap] += clubs[currentClub].getLastRace().getLaps(
						).get(currentLap).getSpeed();
			}
		}
		return speeds; 
	}

}

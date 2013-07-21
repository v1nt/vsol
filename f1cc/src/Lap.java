public class Lap {
	private Match match;

	public Lap(Match match) {
		this.match = match;
	}

	public Match getMatch() {
		return match;
	}


	public int getSpeed() {
		if(match.isAs()) {
			return -5;
		} else {
			int speed = 0;
			speed += match.getResult();
			speed += 2 * (match.getPlus() - match.getMinus());

			if(match.getScoredGoals() > match.getAgainstGoals()) {
				speed += (match.getScoredGoals() - match.getAgainstGoals() > 5 
						? 5 : match.getScoredGoals() - match.getAgainstGoals());
			}

			if(match.getStyle() != Match.STYLE_NORM) {
				if(match.isWinCollision()) {
					speed += 3;
				} else if (match.isLoseCollision()) {
					speed -= 2;
				} else {
					speed++;
				}
			}

			return speed;
		}
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getSpeed());
		sb.append(" ");
		return sb.toString();		
	}
}




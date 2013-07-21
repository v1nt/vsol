public class Match {

	public static final int STYLE_SPART = 1;
	public static final int STYLE_BB = 2;
	public static final int STYLE_KOMB = 3;
	public static final int STYLE_NORM = 4;
	public static final int STYLE_KAT = 5;
	public static final int STYLE_BRIT = 6;

	private int id;
	private int day;
	private boolean as;
	private int result;
	private int scoredGoals;
	private int againstGoals;
	private boolean isHome;
	private int plus;
	private int minus;
	private int style;
	private int enemyStyle;
	
	public Match(int id, int day) {
		this.id = id;
		this.day = day;
	}

	public Match(int id, int day, int result) {
		this(id, day);
		this.result = result;
	}

	public Match(String id, String day, String as, String location, 
		String result, String goals, String plus, String minus) {
		this.id = Integer.parseInt(id);
		this.day = Integer.parseInt(day);

		if("*".equals(as)) {
			this.as = true;
		} else {
			this.as = false;
		}

		if("Д".equals(location)) {
			isHome = true;
		} else {
			isHome = false;
		}

		this.result = calcResultPoints(result);

		String[] splitedGoals = goals.split(":");
		scoredGoals = Integer.parseInt(splitedGoals[0]);
		againstGoals = Integer.parseInt(splitedGoals[1]);

		this.plus = calsPlusMinus(plus);
		this.minus = calsPlusMinus(minus);
	}
	
	public int getId() {
		return id;
	}
	
	public int getDay() {
		return day;
	}

	public boolean isAs() {
		return as;
	}

	public boolean isHome() {
		return isHome;
	}

	public int getResult() {
		return result;
	}

	public int getScoredGoals() {
		return scoredGoals;
	}
	
	public int getAgainstGoals() {
		return againstGoals;
	}

	public int getPlus() {
		return plus;
	}

	public int getMinus() {
		return minus;
	}

	public int getStyle() {
		return style;
	}

	public int getEnemyStyle() {
		return enemyStyle;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDay(int day) {
		this.day = day;	
	}

	public void setStyle(String style) {
		this.style = Integer.parseInt(style);
	}

	public void setEnemyStyle(String style) {
		enemyStyle = Integer.parseInt(style);
	}


	private int calcResultPoints(String result) {
		if ("В".equals(result)) {
			return 3;
		} else if ("Н".equals(result)) {
			return 1;
		}
		return 0;
	}

	private int calsPlusMinus(String plusMinus) {
		if("-".equals(plusMinus)) {
			return 0;
		} else {
			return Integer.parseInt(plusMinus);
		}
	}


	public boolean isWinCollision() {
		if(style == STYLE_SPART && enemyStyle == STYLE_KAT
				|| style == STYLE_KAT && enemyStyle == STYLE_BRIT
				|| style == STYLE_BRIT && enemyStyle == STYLE_KOMB
				|| style == STYLE_KOMB && enemyStyle == STYLE_BB
				|| style == STYLE_BB && enemyStyle == STYLE_SPART
		) {
			return true;
		}
		return false;
	}

	public boolean isLoseCollision() {
		if(enemyStyle == STYLE_SPART && style == STYLE_KAT
				|| enemyStyle == STYLE_KAT && style == STYLE_BRIT
				|| enemyStyle == STYLE_BRIT && style == STYLE_KOMB
				|| enemyStyle == STYLE_KOMB && style == STYLE_BB
				|| enemyStyle == STYLE_BB && style == STYLE_SPART
		) {
			return true;
		}
		return false;
	}

	public boolean isCollision() {
		return isWinCollision() || isLoseCollision();
	}

	
	
	public String getUrl() {
			StringBuilder sb = new StringBuilder(
					"http://www.virtualsoccer.ru/viewmatch.php?day=");
			sb.append(day);
			sb.append("&match_id=");
			sb.append(id);
			return sb.toString();
	}

}

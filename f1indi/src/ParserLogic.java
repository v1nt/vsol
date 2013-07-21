import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.*;

public class ParserLogic {

	private static String content;

	public static void run(boolean isCountry) throws IOException {
		
		long startTime = System.currentTimeMillis();

		List<Club> clubs = new ArrayList<Club>();
		init(clubs, getSeason());
		
		for(Club club : clubs) {
			set(club);
		}
		setPoints(clubs);

		System.out.println(
			"[size=200][color=#BF0000][b]Личное первенство[/b][/color][/size]");
		showLastLap(clubs);
		showLastRace(clubs);
		
		show(clubs);
		showTimeFrom(startTime);

	}
	
	private static void setRacePoints(List<Club> clubs, int raceIndex) {
		int racesNumber = clubs.get(0).getChamp().getFinishedRacesNumber();
		if(raceIndex >= 0 && raceIndex < racesNumber) {
			Collections.sort(clubs, new RaceComparator(raceIndex));
			int updatedClubsNumber = (clubs.size() > Constants.RANK_POINTS.length ? 
					Constants.RANK_POINTS.length : clubs.size());
			for(int clubIndex = 0; clubIndex < updatedClubsNumber; clubIndex++) {
				clubs.get(clubIndex).getChamp().getRaces().get(raceIndex).setPoints(
						Constants.RANK_POINTS[clubIndex]);
			}
		}
	}
	
	private static void setPoints(List<Club> clubs) {
		int racesNumber = clubs.get(0).getChamp().getFinishedRacesNumber();
		for(int raceIndex = 0; raceIndex < racesNumber; raceIndex++) {
			setRacePoints(clubs, raceIndex);
		}
	}

	private static String getSeason() {
		String season = "27";
		try {
			Scanner sc = new Scanner(new FileReader(Constants.CFG_FILE_NAME));
			season = sc.nextLine();
			sc.close();
		} catch(IOException e) {
			e.printStackTrace();
		} catch(NumberFormatException e) {
			e.printStackTrace();
		}
		return season;
	}

	private static void init(List<Club> clubs, String season) {
		try {
			Scanner sc = new Scanner(new FileReader(Constants.INPUT_FILE_NAME));
			while(sc.hasNext()) {
				clubs.add(getClub(Integer.parseInt(sc.nextLine()), season));
			}
			sc.close();
		} catch(IOException e) {
			e.printStackTrace();
		} catch(NumberFormatException e) {
			e.printStackTrace();
		}
	}
	
	private static Club getClub(int id, String season) throws IOException {

		String name;
		String division;

		content = downloadUrl(Constants.URL_CLUB + id);

		Matcher matcher;
		if(Pattern.matches(".*Дивизион.*", content)) {
			matcher = createMatcher(id, season);
		} else {
			matcher = createMatcherCLC(id, season);
		}

		String[] names = Pattern.compile("[(]").split(matcher.group(1));
		name = names[0].trim();
		
		if(matcher.groupCount() == 1) {
			division = "clc";
		} else {
			division = matcher.group(2);
		}

		
		Matcher gameMatcher = Pattern.compile(Constants.PATTERN_GAMES).matcher(
				content);
		List<Match> matches = new ArrayList<Match>();
		while(gameMatcher.find()) {
			String result, location, as, matchDay, matchId, goals, plus, minus;

			result = gameMatcher.group(1);
			location = gameMatcher.group(2);
			as = gameMatcher.group(3);
			matchDay = gameMatcher.group(4);
			matchId = gameMatcher.group(5);
			goals = gameMatcher.group(6);
			plus = gameMatcher.group(7);
			minus = gameMatcher.group(8);

			matches.add(new Match(matchId, matchDay, as, location, result, 
					goals, plus, minus));
		}
		
		return new Club(id, name, division, matches);
	}

	private static Matcher createMatcher(int id, String season) 
			throws IOException {
		Matcher matcher;
		content = downloadUrl(Constants.URL_CLUB + id + "&season=" + season 
				+ "&filter=" + Constants.FILTER_CHAMP);
		matcher = Pattern.compile(Constants.PATTERN_CLUB_PRO).matcher(content);
		if(!matcher.find()) {
			matcher = Pattern.compile(
					Constants.PATTERN_CLUB_PRO_VERSION_2).matcher(content);
			if(!matcher.find()) {
				matcher = createMatcherCLC(id, season);
			}
		}
		return matcher;
	}

	private static Matcher createMatcherCLC(int id, String season) 
			throws IOException {
		Matcher matcher;
		content = downloadUrl(Constants.URL_CLUB + id + "&season=" + season 
				+ "&filter=" + Constants.FILTER_CLC);
		matcher = Pattern.compile(Constants.PATTERN_CLUB_CLC).matcher(content);
		if(!matcher.find()) {
			matcher = Pattern.compile(
					Constants.PATTERN_CLUB_CLC_VERSION_2).matcher(content);
			matcher.find();
		}
		return matcher;
	}
	
	private static void set(Club club) throws IOException {
		for(Match game : club.getMatches()) {
			setGameParameters(club, game);
		}
		club.calcChamp();
	}
	
	private static void setGameParameters(Club club, Match match) 
			throws IOException {
		content = downloadUrl(match.getUrl());
		Matcher styleMatcher = Pattern.compile("styles/([\\d])").matcher(
				content);
		styleMatcher.find();
		if(match.isHome()) {
			match.setStyle(styleMatcher.group(1));
			styleMatcher.find();
			match.setEnemyStyle(styleMatcher.group(1));
		} else {
			match.setEnemyStyle(styleMatcher.group(1));
			styleMatcher.find();
			match.setStyle(styleMatcher.group(1));
		}

	}

	private static String downloadUrl(String urlString) throws IOException {
		String line;
		URL url = new URL(urlString);
		InputStreamReader isr = new InputStreamReader(url.openStream());
		BufferedReader br = new BufferedReader(isr);
		StringBuilder sb = new StringBuilder(Constants.MAX_PAGE_SIZE);
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		br.close();
		return sb.toString();
	}

	private static void showLastLap(List<Club> clubs) {

		Collections.sort(clubs, new LastLapComparator());

		int raceNumber = clubs.get(0).getChamp().getRaces().size();
		int lastLapNumber = 
				clubs.get(0).getChamp().getLastRace().getLaps().size();

		System.out.print("[spoiler=Этап ");
		System.out.print(raceNumber);
		System.out.print(", круг ");
		System.out.print(lastLapNumber);
		System.out.print("][size=150][color=#BF0000][b]Этап ");
		System.out.print(raceNumber);
		System.out.print(", круг ");
		System.out.print(lastLapNumber);
		System.out.println("[/b][/color][/size]");

		System.out.println("[table]");
		System.out.print("[tr][th]rank[/th][th]club[/th][th]match[/th][th][img]http://www.virtualsoccer.ru/pics/up.gif[/img][/th][th][img]http://www.virtualsoccer.ru/pics/down.gif[/img][/th][th]style[/th][th]enemy[/th][th]speed[/th][/tr]");

		int rank = 1;
		for(Club club : clubs) {
			System.out.print("[tr][th]");
			System.out.print(rank++);
			System.out.print("[/th]");
			System.out.print(club.getLastLapRow());
			System.out.println("[/tr]");
		}

		System.out.print("[tr][th]rank[/th][th]club[/th][th]match[/th][th][img]http://www.virtualsoccer.ru/pics/up.gif[/img][/th][th][img]http://www.virtualsoccer.ru/pics/down.gif[/img][/th][th]style[/th][th]enemy[/th][th]speed[/th][/tr]");
		System.out.println("[/table][/spoiler]");
	}
	
	private static void showLastRace(List<Club> clubs) {

		int raceNumber = clubs.get(0).getChamp().getRaces().size();
		int lastLapNumber = 
				clubs.get(0).getChamp().getLastRace().getLaps().size();

		Collections.sort(clubs, new RaceComparator(raceNumber - 1));


		System.out.print("[spoiler=Этап ");
		System.out.print(raceNumber);
		if(lastLapNumber == 3) {
			System.out.print(", итоги");
		} else {
			System.out.print(", текущее положение после круга ");
			System.out.print(lastLapNumber);
		}
		System.out.print("][size=150][color=#BF0000][b]Этап ");
		System.out.print(raceNumber);
		if(lastLapNumber == 3) {
			System.out.print(", итоги");
		} else {
			System.out.print(", текущее положение после круга ");
			System.out.print(lastLapNumber);
		}
		System.out.println("[/b][/color][/size]");

		System.out.print("[table]");
		System.out.print("[tr][th]rank[/th][th]club[/th]");
		for(int i = 1; i <= lastLapNumber; i++) {
			System.out.print("[th]lap" + i + " spd[/th]");
		}
		System.out.print("[th]sum spd[/th]");
		System.out.print("[th][img]http://www.virtualsoccer.ru/pics/up.gif[/img][/th]");
		System.out.print("[th][img]http://www.virtualsoccer.ru/pics/down.gif[/img][/th]");
		System.out.print("[th]results[/th][th]goals[/th]");
		if(lastLapNumber == 3) {
			System.out.print("[th]race points[/th]");
		}
		System.out.println("[/tr]");

		int rank = 1;
		for(Club club : clubs) {
			System.out.print("[tr][th]");
			System.out.print(rank++);
			System.out.print("[/th]");
			System.out.print(club.getLastRaceRow());
			System.out.println("[/tr]");
		}
		System.out.print("[tr][th]rank[/th][th]club[/th]");
		for(int i = 1; i <= lastLapNumber; i++) {
			System.out.print("[th]lap" + i + " spd[/th]");
		}
		System.out.print("[th]sum spd[/th]");
		System.out.print("[th][img]http://www.virtualsoccer.ru/pics/up.gif[/img][/th]");
		System.out.print("[th][img]http://www.virtualsoccer.ru/pics/down.gif[/img][/th]");
		System.out.print("[th]results[/th][th]goals[/th]");
		if(lastLapNumber == 3) {
			System.out.print("[th]race points[/th]");
		}
		System.out.println("[/tr]");
		System.out.println("[/table][/spoiler]");
	}
	
	private static void show(List<Club> clubs) {

		int lastLapNumber = 
				clubs.get(0).getChamp().getLastRace().getLaps().size();

		if(lastLapNumber == 3) {
			Collections.sort(clubs, new ClubComparator());
			int racesNumber = clubs.get(0).getChamp().getFinishedRacesNumber();

			System.out.print("[spoiler=Турнирная таблица][size=150][color=#BF0000][b]");
			System.out.println("Турнирная таблица[/b][/color][/size]");

			int rank = 1;
			System.out.println("[table]");
			System.out.print("[tr][th]rank[/th][th]club[/th]");
			for(int raceIndex = 1; raceIndex <= racesNumber; raceIndex++) {
				System.out.print("[th]race ");
				System.out.print(raceIndex);
				System.out.print("[/th]");
			}
			System.out.println("[th]points[/th][/tr]");
			for(Club club : clubs) {
				
				if (club.getPoints() == 0) {
					break;
				}
				
				System.out.print("[tr][th]"+(rank++)+"[/th][td][url=" + club.getUrl() 
						+ "]" + club.getName() + "[/url][/td]");
				for(int raceIndex = 1; raceIndex <= racesNumber; raceIndex++) {
					System.out.print("[td]");
					System.out.print(club.getChamp().getRaces().get(raceIndex-1).getPoints());
					System.out.print("[/td]");
				}
				System.out.println("[th]" + club.getPoints() + "[/th][/tr]");
			}
			System.out.print("[tr][th]rank[/th][th]club[/th]");
			for(int raceIndex = 1; raceIndex <= racesNumber; raceIndex++) {
				System.out.print("[th]race ");
				System.out.print(raceIndex);
				System.out.print("[/th]");
			}
			System.out.println("[th]points[/th][/tr]");
			System.out.println("[/table][/spoiler]");
		}
	}

	private static void showTimeFrom(long time) {
		long workTime = System.currentTimeMillis() - time;
		System.out.println((workTime/1000) + "." + workTime%1000 + " seconds");
	}
}

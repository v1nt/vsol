public class Constants {
	
public static int[] RANK_POINTS = {50, 44, 40, 36, 34, 32, 30, 28, 26, 24, 
	18, 16, 14, 12, 10, 7, 5, 4, 3, 2, 1};

public static int ID_COUNTRY = 6;
public static int FILTER_CHAMP = 3;
public static int FILTER_CLC = 10;
public static int MAX_PAGE_SIZE = 100000;

public static String INPUT_FILE_NAME = "src/in.txt";
public static String CFG_FILE_NAME = "src/config.txt";

public static String URL_ROOT = "http://www.virtualsoccer.ru/";
public static String URL_COUNTRY = URL_ROOT + "teams_cntr.php?num="
		+ ID_COUNTRY;
public static String URL_CLUB = URL_ROOT + "roster_m.php?num=";

public static String PATTERN_CLUB_ID = "roster[.]php[?]num=([\\d]+)\">";


public static String PATTERN_CLUB_PRO = "<div style=\"FONT-FAMILY:arial; "
		+ "FONT-SIZE:14px; FONT-WEIGHT:bold; letter-spacing:-1px; " 
		+ "TEXT-ALIGN:left\">([^<]+)"
		+ ".*?v2champ[.]php[?]num=[\\d]+\">[^,]+, ([^<]{2,4})";

public static String PATTERN_CLUB_PRO_VERSION_2 = "team_name\" style=\"display:none\">" +
		"([^(]+).*?v2champ[.]php[?]num=[\\d]+\">[^,]+, ([^<]{2,4})";


public static String PATTERN_CLUB_CLC = "<div style=\"FONT-FAMILY:arial; "
		+ "FONT-SIZE:14px; FONT-WEIGHT:bold; letter-spacing:-1px; " 
		+ "TEXT-ALIGN:left\">([^(]+)";

public static String PATTERN_CLUB_CLC_VERSION_2 = 
		"team_name\" style=\"display:none\">([^(]+)";

/*
public static String PATTERN_GAMES = "target=\"_blank\" " 
		+ "href=\"viewmatch[.]php[?]day=([\\d]{1,5})&amp;match_id=([\\d]{1,8})";
*/
public static String PATTERN_GAMES = "тур</td>.*?" 
		+ "<td class=\"txt\" title=\"[^\"]+\">([ВНП])</td>.*?" 
		+ "<td class=\"txt\" title=\"[^\"]+\">([ГД])</td>.+?" 
		+ "</a></td>.*?<td class=\"txt\">([^<]+)</td>.*?"
		+ "<td class=\"txt\"><a target=\"_blank\" " 
		+ "href=\"viewmatch[.]php[?]day=([\\d]{1,5})&amp;match_id=([\\d]{1,8})"
		+ "\" class=\"mnu\"><b>([^<]+).+?" 
		+ "00AA00\">(.).+?" 
		+ "AA0000\">(.)</td></tr>";



public static String PATTERN_PLAYER = 
		"([2Gu])\" href=\"player[.]php[?]num=([\\d]{1,8}).+?</td>.*?</tr>";
public static String PATTERN_GK = 
		"([2Gu])\" href=\"player[.]php[?]num=([\\d]{1,8}).+?</td>.*?"
		+ "<td class=\"txt2\">.*?</td>.*?"
		+ "<td class=\"txt2\">.*?</td>.*?"
		+ "<td class=\"txt2\">.*?</td>.*?"
		+ "<td class=\"txt2\">.*?</td>.*?"
		+ "<td class=\"txt2\">.*?</td>.*?"
		+ "<td class=\"txt2\">.*?</td>.*?"
		+ "<td class=\"txt2\">([\\d]{1,2})</td>.*?"
		+ "<td class=\"txt2\">.*?</td>.*?"
		+ "<td class=\"txt2\">([\\d]{1,2})";

/*
public static String PATTERN_GK = 
		"([2Gu])\" href=\"player[.]php[?]num=([\\d]{1,8}).+?</td>.*?"
		+ "<td class=\"txt2\">.+?</td>.*?"
		+ "<td class=\"txt2\">([\\d]{2})</td>.*?"
		+ "<td class=\"txt2\">([\\d]{1,3})</td>.*?"
		+ "<td class=\"txt2\">.*?</td>.*?"
		+ "<td class=\"txt2\"><b>([\\d]{1,3})</b></td>.*?"
		+ "<td class=\"txt2\">([\\d]{1,2})</td>.*?"
		+ "<td class=\"txt2\">([\\d]{1,2})</td>.*?"
		+ "<td class=\"txt2\">([\\d]{1,2})</td>";
*/
//		"<td.+?</td>.*" +
//		"<td.+?</td>.*" +
//		"<td.+?</td>.*" +
//		"<td.+?</td>.*" +
//		"<td.+?</td>.*" +
//		"<td.+?([\\d]{1,2})</td>.*" +
//		"<td.+?</td>.*" +
//		"<td.+?([\\d]{1,2})</td>.*" +
//		"<td.+?</td>.*" +
//		"</tr>";

//public static String PATTERN_GK = "([2Gu])\" href=\"player[.]php[?]num=" + 
//		"([\\d]{1,8}).+?((up)|(down)[.]gif)?.+?</td>";

public static String TABLE_HEADER = "[size=150][color=#BF0000][b]Конкурс " +
		"вратарей[/b][/color][/size][table][tr][th]№[/th][th]club[/th]" +
		"[th]division[/th][th]shutout[/th][th]save[/th][th]PK save[/th]"
		+ "[th][img]http://www.virtualsoccer.ru/pics/up.gif[/img][/th]"
		+ "[th]goal[/th][th]PK goal[/th][th]FK goal[/th][th]CK goal[/th]"
		+ "[th]MoM[/th][th]MoT[/th][th]WoM[/th][th]WoT[/th]"
		+ "[th][img]http://www.virtualsoccer.ru/pics/down.gif[/img][/th]"
		+ "[th]points[/th][/tr]";
public static String TABLE_FOOTER = "[/table]";

}


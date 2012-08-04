package net.sacredlabyrinth.phaed.simpleclans;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import net.sacredlabyrinth.phaed.simpleclans.storage.DBCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.getspout.spoutapi.Spout;
import org.getspout.spoutapi.player.SpoutPlayer;

/**
 * @author phaed
 */
public class Helper
{

    /**
     * Dumps stacktrace to log
     */
    public static void dumpStackTrace()
    {
        for (StackTraceElement el : Thread.currentThread().getStackTrace()) {
            SimpleClans.debug(el.toString());
        }
    }

    /**
     * Ensures only one player can be matched from a partial name
     *
     * @param playername
     * @return matched player, null if more than one player matched
     */
    public static Player matchOnePlayer(String playername)
    {
        List<Player> players = Bukkit.matchPlayer(playername);

        if (players.size() == 1) {
            return players.get(0);
        }

        return null;
    }

    /**
     * Convert color hex values with ampersand to special character
     *
     * @param msg
     * @return
     */
    public static String parseColors(String msg)
    {
        return msg.replace("&", "\u00a7");
    }

    /**
     * Get a players full color name if he is online
     *
     * @param playerName
     * @return
     */
    public static String getColorName(String playerName)
    {
        List<Player> players = Bukkit.matchPlayer(playerName);

        if (players.size() == 1) {
            return SimpleClans.getInstance().getPermissionsManager().getPrefix(players.get(0)) + players.get(0).getDisplayName() + SimpleClans.getInstance().getPermissionsManager().getSuffix(players.get(0));
        }

        return playerName;
    }

    public static void setTitle(Entity entity, String title)
    {
        if (entity instanceof LivingEntity) {
            Spout.getServer().setTitle((LivingEntity) entity, title);
        }
    }

    public static void setTitle(LivingEntity entity, SpoutPlayer player, String title)
    {
        player.setTitleFor(player, title);
    }

    /**
     * Check for integer
     *
     * @param o
     * @return
     */
    public static boolean isInteger(Object o)
    {
        return o instanceof java.lang.Integer;
    }

    /**
     * Check for byte
     *
     * @param input
     * @return
     */
    public static boolean isByte(String input)
    {
        try {
            Byte.parseByte(input);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Checks if a entry in a column exists
     *
     * @param core
     * @param tabell
     * @param column
     * @param entry
     * @return
     */
    public static Boolean existsEntry(DBCore core, String tabell, String column, String entry) throws SQLException
    {
        String query = "SELECT " + column + " FROM  `" + tabell + "` WHERE `" + tabell + "`.`" + column + "` =  '" + entry + "';";
        return core.select(query).next() ? true : false;
    }

    /**
     * Check for short
     *
     * @param input
     * @return
     */
    public static boolean isShort(String input)
    {
        try {
            Short.parseShort(input);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Check for integer
     *
     * @param input
     * @return
     */
    public static boolean isInteger(String input)
    {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Check for float
     *
     * @param input
     * @return
     */
    public static boolean isFloat(String input)
    {
        try {
            Float.parseFloat(input);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Check for string
     *
     * @param o
     * @return
     */
    public static boolean isString(Object o)
    {
        return o instanceof java.lang.String;
    }

    /**
     * Check for boolean
     *
     * @param o
     * @return
     */
    public static boolean isBoolean(Object o)
    {
        return o instanceof java.lang.Boolean;
    }

    /**
     * Remove a character from a string
     *
     * @param s
     * @param c
     * @return
     */
    public static String removeChar(String s, char c)
    {
        StringBuilder r = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != c) {
                r.append(s.charAt(i));
            }
        }

        return r.toString();
    }

    /**
     * Remove first character from a string
     *
     * @param s
     * @param c
     * @return
     */
    public static String removeFirstChar(String s, char c)
    {
        StringBuilder r = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != c) {
                r.append(s.charAt(i));
                break;
            }
        }

        return r.toString();
    }

    /**
     * Capitalize first word of sentence
     *
     * @param content
     * @return
     */
    public static String capitalize(String content)
    {
        if (content.length() < 2) {
            return content;
        }

        String first = content.substring(0, 1).toUpperCase();
        return first + content.substring(1);
    }

    /**
     * Return plural word if count is bigger than one
     *
     * @param count
     * @param word
     * @param ending
     * @return
     */
    public static String plural(int count, String word, String ending)
    {
        return count == 1 ? word : word + ending;
    }

    /**
     * Hex value to ChatColor
     *
     * @param hexValue
     * @return
     */
    public static String toColor(String hexValue)
    {
        if (hexValue == null) {
            return "";
        }

        return ChatColor.getByChar(hexValue).toString();
    }

    /**
     * Converts string array to List<String>, remove empty strings
     *
     * @param values
     * @return
     */
    public static List fromArray(String... values)
    {
        List<String> out = new ArrayList<String>(Arrays.asList(values));
        out.remove("");
        return out;
    }

    /**
     * Converts string array to HashSet<String>, remove empty strings
     *
     * @param values
     * @return
     */
    public static HashSet fromArray2(String... values)
    {
        HashSet<String> out = new HashSet<String>(Arrays.asList(values));
        out.remove("");
        return out;
    }

    /**
     * Converts any array to a Collection
     *
     * @param values
     * @return
     */
    public static <T> Collection<T> convertArray(T... values)
    {
        return Arrays.asList(values);
    }

    /**
     * Converts a player array to ArrayList<Player>
     *
     * @param values
     * @return
     */
    public static Set<Player> fromPlayerArray(Player... values)
    {
        return new HashSet<Player>(Arrays.asList(values));
    }

    /**
     * Converts ArrayList<String> to string array
     *
     * @param list
     * @return
     */
    public static String[] toArray(Collection<String> list)
    {
        return list.toArray(new String[list.size()]);
    }

    /**
     * Removes first item from a string array
     *
     * @param args
     * @return
     */
    public static <T>T[] removeFirst(T[] args)
    {
        return Arrays.copyOfRange(args, 1, args.length);
    }

    /**
     * Converts a string array to a space separated string
     *
     * @param args
     * @return
     */
    public static String toMessage(String[] args)
    {
        StringBuilder sb = new StringBuilder();

        for (String arg : args) {
            sb.append(arg).append(' ');
        }

        return sb.toString().trim();
    }

    /**
     * Converts a string array to a string with custom separators
     *
     * @param args
     * @param sep
     * @return
     */
    public static String toMessage(String[] args, String sep)
    {
        StringBuilder sb = new StringBuilder();

        for (String arg : args) {
            sb.append(arg).append(' ').append(", ");
        }


        return stripTrailing(sb.toString(), ", ");
    }

    /**
     * Converts a string array to a string with custom separators
     *
     * @param args
     * @param sep
     * @return
     */
    public static String toMessage(Collection<String> args, String sep)
    {
        StringBuilder sb = new StringBuilder();

        for (String arg : args) {
            sb.append(arg).append(' ').append(sep);
        }

        return stripTrailing(sb.toString(), sep);
    }

    /**
     * Removes color codes from strings
     *
     * @param msg
     * @return
     */
    public static String stripColors(String msg)
    {
        String out = msg.replaceAll("[&][0-9a-f]", "");
        out = out.replaceAll(String.valueOf((char) 194), "");
        return out.replaceAll("[\u00a7][0-9a-f]", "");
    }

    /*
     * Retrieves the last color code @param msg @return
     */
    /**
     * @param msg
     * @return
     */
    public static String getLastColorCode(String msg)
    {
        msg = msg.replaceAll(String.valueOf((char) 194), "").trim();

        if (msg.length() < 2) {
            return "";
        }

        String one = msg.substring(msg.length() - 2, msg.length() - 1);
        String two = msg.substring(msg.length() - 1);

        if (one.equals("\u00a7")) {
            return one + two;
        }

        if (one.equals("&")) {
            return Helper.toColor(two);
        }


        return "";
    }

    /**
     * Cleans up the tag from color codes and makes it lowercase
     *
     * @param tag
     * @return
     */
    public static String cleanTag(String tag)
    {
        return stripColors(tag).toLowerCase();
    }

    /**
     * Removes trailing separators
     *
     * @param msg
     * @param sep
     * @return
     */
    public static String stripTrailing(String msg, String sep)
    {
        if (msg.length() < sep.length()) {
            return msg;
        }

        String out = msg;
        String first = msg.substring(0, sep.length());
        String last = msg.substring(msg.length() - sep.length(), msg.length());

        if (first.equals(sep)) {
            out = msg.substring(sep.length());
        }

        if (last.equals(sep)) {
            out = msg.substring(0, msg.length() - sep.length());
        }

        return out;
    }

    /**
     * Generates page separator line
     *
     * @param sep
     * @return
     */
    public static String generatePageSeparator(String sep)
    {
        StringBuilder out = new StringBuilder();

        for (int i = 0; i < 320; i++) {
            out.append(sep);
        }
        return out.toString();
    }

    /**
     * Check whether a player is online
     *
     * @param playerName
     * @return
     */
    public static boolean isOnline(String playerName)
    {
        return Bukkit.getPlayerExact(playerName) != null;
    }

    /**
     * Remove offline players from a ClanPlayer array
     *
     * @param in
     * @return
     */
    public static List<ClanPlayer> stripOffLinePlayers(List<ClanPlayer> in)
    {
        List<ClanPlayer> out = new ArrayList<ClanPlayer>();

        for (ClanPlayer cp : in) {
            if (Bukkit.getPlayer(cp.getName()) != null) {
                out.add(cp);
            }
        }

        return out;
    }

    /**
     * Test if a url is valid
     *
     * @param strUrl
     * @return
     */
    public static boolean testURL(String strUrl)
    {
        try {
            URL url = new URL(strUrl);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.connect();

            if (urlConn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return false;
            }
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    /**
     * Escapes single quotes
     *
     * @param str
     * @return
     */
    public static String escapeQuotes(String str)
    {
        if (str == null) {
            return "";
        }
        return str.replace("'", "''");
    }

    /**
     * Returns a prettier coordinate, does not include world
     *
     * @param loc
     * @return
     */
    public static String toLocationString(Location loc)
    {
        return loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ() + " " + loc.getWorld().getName();
    }

    /**
     * Whether the two locations refer to the same block
     *
     * @param loc
     * @param loc2
     * @return
     */
    public static boolean isSameBlock(Location loc, Location loc2)
    {
        if (loc.getBlockX() == loc2.getBlockX() && loc.getBlockY() == loc2.getBlockY() && loc.getBlockZ() == loc2.getBlockZ()) {
            return true;
        }
        return false;
    }

    /**
     * Whether the two locations refer to the same location, ignoring pitch and
     * yaw
     *
     * @param loc
     * @param loc2
     * @return
     */
    public static boolean isSameLocation(Location loc, Location loc2)
    {
        if (loc.getX() == loc2.getX() && loc.getY() == loc2.getY() && loc.getZ() == loc2.getZ()) {
            return true;
        }
        return false;
    }

    /**
     * Sort hashmap by value
     *
     * @return
     */
    public static Map sortByValue(Map map)
    {
        List list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator()
        {

            @Override
            public int compare(Object o1, Object o2)
            {
                return ((Comparable) ((Map.Entry) (o2)).getValue()).compareTo(((Map.Entry) (o1)).getValue());
            }
        });

        Map result = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    /**
     * Checks if the player is Vanished
     *
     * @return
     */
    public static boolean isVanished(Player player)
    {
        if (player != null && player.hasMetadata("vanished")) {
            if (!player.getMetadata("vanished").isEmpty()) {
                return player.getMetadata("vanished").get(0).asBoolean();
            }
        }
        return false;
    }
}
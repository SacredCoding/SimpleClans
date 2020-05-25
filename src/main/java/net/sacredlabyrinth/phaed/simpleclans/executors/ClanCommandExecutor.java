package net.sacredlabyrinth.phaed.simpleclans.executors;

import java.text.MessageFormat;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.sacredlabyrinth.phaed.simpleclans.ChatBlock;
import net.sacredlabyrinth.phaed.simpleclans.Helper;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;
import net.sacredlabyrinth.phaed.simpleclans.commands.AlliancesCommand;
import net.sacredlabyrinth.phaed.simpleclans.commands.AllyCommand;
import net.sacredlabyrinth.phaed.simpleclans.commands.BanCommand;
import net.sacredlabyrinth.phaed.simpleclans.commands.BankCommand;
import net.sacredlabyrinth.phaed.simpleclans.commands.BbCommand;
import net.sacredlabyrinth.phaed.simpleclans.commands.ClanffCommand;
import net.sacredlabyrinth.phaed.simpleclans.commands.CoordsCommand;
import net.sacredlabyrinth.phaed.simpleclans.commands.CreateCommand;
import net.sacredlabyrinth.phaed.simpleclans.commands.DemoteCommand;
import net.sacredlabyrinth.phaed.simpleclans.commands.DescriptionCommand;
import net.sacredlabyrinth.phaed.simpleclans.commands.DisbandCommand;
import net.sacredlabyrinth.phaed.simpleclans.commands.FeeCommand;
import net.sacredlabyrinth.phaed.simpleclans.commands.FfCommand;
import net.sacredlabyrinth.phaed.simpleclans.commands.GlobalffCommand;
import net.sacredlabyrinth.phaed.simpleclans.commands.HomeCommand;
import net.sacredlabyrinth.phaed.simpleclans.commands.InviteCommand;
import net.sacredlabyrinth.phaed.simpleclans.commands.KickCommand;
import net.sacredlabyrinth.phaed.simpleclans.commands.KillsCommand;
import net.sacredlabyrinth.phaed.simpleclans.commands.LeaderboardCommand;
import net.sacredlabyrinth.phaed.simpleclans.commands.ListCommand;
import net.sacredlabyrinth.phaed.simpleclans.commands.LookupCommand;
import net.sacredlabyrinth.phaed.simpleclans.commands.MenuCommand;
import net.sacredlabyrinth.phaed.simpleclans.commands.ModtagCommand;
import net.sacredlabyrinth.phaed.simpleclans.commands.MostKilledCommand;
import net.sacredlabyrinth.phaed.simpleclans.commands.PlaceCommand;
import net.sacredlabyrinth.phaed.simpleclans.commands.ProfileCommand;
import net.sacredlabyrinth.phaed.simpleclans.commands.PromoteCommand;
import net.sacredlabyrinth.phaed.simpleclans.commands.PurgeCommand;
import net.sacredlabyrinth.phaed.simpleclans.commands.RankCommand;
import net.sacredlabyrinth.phaed.simpleclans.commands.ReloadCommand;
import net.sacredlabyrinth.phaed.simpleclans.commands.ResetKDRCommand;
import net.sacredlabyrinth.phaed.simpleclans.commands.ResignCommand;
import net.sacredlabyrinth.phaed.simpleclans.commands.RivalCommand;
import net.sacredlabyrinth.phaed.simpleclans.commands.RivalriesCommand;
import net.sacredlabyrinth.phaed.simpleclans.commands.RosterCommand;
import net.sacredlabyrinth.phaed.simpleclans.commands.SetRankCommand;
import net.sacredlabyrinth.phaed.simpleclans.commands.StatsCommand;
import net.sacredlabyrinth.phaed.simpleclans.commands.ToggleCommand;
import net.sacredlabyrinth.phaed.simpleclans.commands.TrustCommand;
import net.sacredlabyrinth.phaed.simpleclans.commands.UnbanCommand;
import net.sacredlabyrinth.phaed.simpleclans.commands.UntrustCommand;
import net.sacredlabyrinth.phaed.simpleclans.commands.VerifyCommand;
import net.sacredlabyrinth.phaed.simpleclans.commands.VitalsCommand;
import net.sacredlabyrinth.phaed.simpleclans.commands.WarCommand;
import net.sacredlabyrinth.phaed.simpleclans.ui.InventoryDrawer;
import net.sacredlabyrinth.phaed.simpleclans.ui.frames.MainFrame;
import org.jetbrains.annotations.NotNull;

import static net.sacredlabyrinth.phaed.simpleclans.SimpleClans.lang;

/**
 * @author phaed
 */
public final class ClanCommandExecutor implements CommandExecutor {
    private final SimpleClans plugin;
    private final CreateCommand createCommand;
    private final ListCommand listCommand;
    private final ProfileCommand profileCommand;
    private final RosterCommand rosterCommand;
    private final LookupCommand lookupCommand;
    private final LeaderboardCommand leaderboardCommand;
    private final AlliancesCommand alliancesCommand;
    private final RivalriesCommand rivalriesCommand;
    private final VitalsCommand vitalsCommand;
    private final CoordsCommand coordsCommand;
    private final StatsCommand statsCommand;
    private final AllyCommand allyCommand;
    private final RivalCommand rivalCommand;
    private final BbCommand bbCommand;
    private final ModtagCommand modtagCommand;
    private final ToggleCommand toggleCommand;
    private final InviteCommand inviteCommand;
    private final KickCommand kickCommand;
    private final TrustCommand trustCommand;
    private final UntrustCommand untrustCommand;
    private final PromoteCommand promoteCommand;
    private final DemoteCommand demoteCommand;
    private final ClanffCommand clanffCommand;
    private final FfCommand ffCommand;
    private final ResignCommand resignCommand;
    private final DisbandCommand disbandCommand;
    private final VerifyCommand verifyCommand;
    private final BanCommand banCommand;
    private final FeeCommand feeCommand;
    private final UnbanCommand unbanCommand;
    private final ReloadCommand reloadCommand;
    private final GlobalffCommand globalffCommand;
    private final MenuCommand menuCommand;
    private final WarCommand warCommand;
    private final HomeCommand homeCommand;
    private final KillsCommand killsCommand;
    private final MostKilledCommand mostKilledCommand;
    private final SetRankCommand setRankCommand;
    private final BankCommand bankCommand;
    private final PlaceCommand placeCommand;
    private final ResetKDRCommand resetKDRCommand;
    private final PurgeCommand purgeCommand;
    private final DescriptionCommand descriptionCommand;
    private final RankCommand rankCommand;

    /**
     *
     */
    public ClanCommandExecutor() {
        plugin = SimpleClans.getInstance();
        menuCommand = new MenuCommand();
        createCommand = new CreateCommand();
        listCommand = new ListCommand();
        profileCommand = new ProfileCommand();
        rosterCommand = new RosterCommand();
        lookupCommand = new LookupCommand();
        leaderboardCommand = new LeaderboardCommand();
        alliancesCommand = new AlliancesCommand();
        rivalriesCommand = new RivalriesCommand();
        vitalsCommand = new VitalsCommand();
        coordsCommand = new CoordsCommand();
        statsCommand = new StatsCommand();
        allyCommand = new AllyCommand();
        rivalCommand = new RivalCommand();
        bbCommand = new BbCommand();
        modtagCommand = new ModtagCommand();
        toggleCommand = new ToggleCommand();
        inviteCommand = new InviteCommand();
        kickCommand = new KickCommand();
        trustCommand = new TrustCommand();
        untrustCommand = new UntrustCommand();
        promoteCommand = new PromoteCommand();
        demoteCommand = new DemoteCommand();
        clanffCommand = new ClanffCommand();
        ffCommand = new FfCommand();
        resignCommand = new ResignCommand();
        disbandCommand = new DisbandCommand();
        verifyCommand = new VerifyCommand();
        banCommand = new BanCommand();
        unbanCommand = new UnbanCommand();
        reloadCommand = new ReloadCommand();
        globalffCommand = new GlobalffCommand();
        warCommand = new WarCommand();
        homeCommand = new HomeCommand();
        killsCommand = new KillsCommand();
        mostKilledCommand = new MostKilledCommand();
        setRankCommand = new SetRankCommand();
        bankCommand = new BankCommand();
        placeCommand = new PlaceCommand();
        resetKDRCommand = new ResetKDRCommand();
        feeCommand = new FeeCommand();
        purgeCommand = new PurgeCommand();
        descriptionCommand = new DescriptionCommand();
        rankCommand = new RankCommand();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command command, String s, String[] args) {
        try {
            if (sender instanceof Player) {
                Player player = (Player) sender;

                if (plugin.getSettingsManager().isBlacklistedWorld(player.getLocation().getWorld().getName())) {
                    return false;
                }

                if (plugin.getSettingsManager().isBanned(player.getUniqueId())) {
                    ChatBlock.sendMessage(player, ChatColor.RED + lang("banned"));
                    return false;
                }

                if (args.length == 0) {
                	if (plugin.getSettingsManager().isEnableGUI()) {
                		try {
                			InventoryDrawer.open(new MainFrame(player));
                		} catch (NoSuchFieldError e) {
                		    menuCommand.execute(player);
                		    plugin.getServer().getConsoleSender().sendMessage(lang("gui.not.supported"));
                			plugin.getSettingsManager().setEnableGUI(false);
                			plugin.getSettingsManager().save();
                		}
                	} else {
                        menuCommand.execute(player);
                	}
                } else {
                    String subcommand = args[0];
                    String[] subargs = Helper.removeFirst(args);
                    
                    if (subcommand.equalsIgnoreCase(lang("create.command")) || subcommand.equalsIgnoreCase("create")) {
                        createCommand.execute(player, subargs);
                    } else if (subcommand.equalsIgnoreCase(lang("list.command")) || subcommand.equalsIgnoreCase("list")) {
                        listCommand.execute(player, subargs);
                    } else if (subcommand.equalsIgnoreCase(lang("bank.command")) || subcommand.equalsIgnoreCase("bank")) {
                        bankCommand.execute(player, subargs);
                    } else if (subcommand.equalsIgnoreCase(lang("profile.command")) || subcommand.equalsIgnoreCase("profile")) {
                        profileCommand.execute(player, subargs);
                    } else if (subcommand.equalsIgnoreCase(lang("roster.command")) || subcommand.equalsIgnoreCase("roster")) {
                        rosterCommand.execute(player, subargs);
                    } else if (subcommand.equalsIgnoreCase(lang("lookup.command")) || subcommand.equalsIgnoreCase("lookup")) {
                        lookupCommand.execute(player, subargs);
                    } else if (subcommand.equalsIgnoreCase(lang("home.command")) || subcommand.equalsIgnoreCase("home")) {
                        homeCommand.execute(player, subargs);
                    } else if (subcommand.equalsIgnoreCase(lang("leaderboard.command")) || subcommand.equalsIgnoreCase("leaderboard")) {
                        leaderboardCommand.execute(player, subargs);
                    } else if (subcommand.equalsIgnoreCase(lang("alliances.command")) || subcommand.equalsIgnoreCase("alliances")) {
                        alliancesCommand.execute(player, subargs);
                    } else if (subcommand.equalsIgnoreCase(lang("rivalries.command")) || subcommand.equalsIgnoreCase("rivalries")) {
                        rivalriesCommand.execute(player, subargs);
                    } else if (subcommand.equalsIgnoreCase(lang("vitals.command")) || subcommand.equalsIgnoreCase("vitals")) {
                        vitalsCommand.execute(player, subargs);
                    } else if (subcommand.equalsIgnoreCase(lang("coords.command")) || subcommand.equalsIgnoreCase("coords")) {
                        coordsCommand.execute(player, subargs);
                    } else if (subcommand.equalsIgnoreCase(lang("stats.command")) || subcommand.equalsIgnoreCase("stats")) {
                        statsCommand.execute(player, subargs);
                    } else if (subcommand.equalsIgnoreCase(lang("ally.command")) || subcommand.equalsIgnoreCase("ally")) {
                        allyCommand.execute(player, subargs);
                    } else if (subcommand.equalsIgnoreCase(lang("rival.command")) || subcommand.equalsIgnoreCase("rival")) {
                        rivalCommand.execute(player, subargs);
                    } else if (subcommand.equalsIgnoreCase(lang("bb.command")) || subcommand.equalsIgnoreCase("bb")) {
                        bbCommand.execute(player, subargs);
                    } else if (subcommand.equalsIgnoreCase(lang("modtag.command")) || subcommand.equalsIgnoreCase("modtag")) {
                        modtagCommand.execute(player, subargs);
                    } else if (subcommand.equalsIgnoreCase(lang("toggle.command")) || subcommand.equalsIgnoreCase("toggle")) {
                        toggleCommand.execute(player, subargs);
                    } else if (subcommand.equalsIgnoreCase(lang("invite.command")) || subcommand.equalsIgnoreCase("invite")) {
                        inviteCommand.execute(player, subargs);
                    } else if (subcommand.equalsIgnoreCase(lang("kick.command")) || subcommand.equalsIgnoreCase("kick")) {
                        kickCommand.execute(player, subargs);
                    } else if (subcommand.equalsIgnoreCase(lang("trust.command")) || subcommand.equalsIgnoreCase("trust")) {
                        trustCommand.execute(player, subargs);
                    } else if (subcommand.equalsIgnoreCase(lang("untrust.command")) || subcommand.equalsIgnoreCase("unstrust")) {
                        untrustCommand.execute(player, subargs);
                    } else if (subcommand.equalsIgnoreCase(lang("promote.command")) || subcommand.equalsIgnoreCase("promote")) {
                        promoteCommand.execute(player, subargs);
                    } else if (subcommand.equalsIgnoreCase(lang("demote.command")) || subcommand.equalsIgnoreCase("demote")) {
                        demoteCommand.execute(player, subargs);
                    } else if (subcommand.equalsIgnoreCase(lang("clanff.command")) || subcommand.equalsIgnoreCase("canff")) {
                        clanffCommand.execute(player, subargs);
                    } else if (subcommand.equalsIgnoreCase(lang("ff.command")) || subcommand.equalsIgnoreCase("ff")) {
                        ffCommand.execute(player, subargs);
                    } else if (subcommand.equalsIgnoreCase(lang("resign.command")) || subcommand.equalsIgnoreCase("resign")) {
                        resignCommand.execute(player, subargs);
                    } else if (subcommand.equalsIgnoreCase(lang("disband.command")) || subcommand.equalsIgnoreCase("disband")) {
                        disbandCommand.execute(player, subargs);
                    } else if (subcommand.equalsIgnoreCase(lang("verify.command")) || subcommand.equalsIgnoreCase("verify")) {
                        verifyCommand.execute(player, subargs);
                    } else if (subcommand.equalsIgnoreCase(lang("ban.command")) || subcommand.equalsIgnoreCase("ban")) {
                        banCommand.execute(player, subargs);
                    } else if (subcommand.equalsIgnoreCase(lang("unban.command")) || subcommand.equalsIgnoreCase("unban")) {
                        unbanCommand.execute(player, subargs);
                    } else if (subcommand.equalsIgnoreCase(lang("reload.command")) || subcommand.equalsIgnoreCase("reload")) {
                        reloadCommand.execute(player, subargs);
                    } else if (subcommand.equalsIgnoreCase(lang("globalff.command")) || subcommand.equalsIgnoreCase("globalff")) {
                        globalffCommand.execute(player, subargs);
                    } else if (subcommand.equalsIgnoreCase(lang("war.command")) || subcommand.equalsIgnoreCase("war")) {
                        warCommand.execute(player, subargs);
                    } else if (subcommand.equalsIgnoreCase(lang("kills.command")) || subcommand.equalsIgnoreCase("kills")) {
                        killsCommand.execute(player, subargs);
                    } else if (subcommand.equalsIgnoreCase(lang("mostkilled.command")) || subcommand.equalsIgnoreCase("mostkilled")) {
                        mostKilledCommand.execute(player, subargs);
                    } else if (subcommand.equalsIgnoreCase(lang("setrank.command")) || subcommand.equalsIgnoreCase("setrank")) {
                        setRankCommand.execute(player, subargs);
                    } else if (subcommand.equalsIgnoreCase(lang("place.command")) || subcommand.equalsIgnoreCase("place")) {
                        placeCommand.execute(player, subargs);
                    } else if (subcommand.equalsIgnoreCase(lang("resetkdr.command")) || subcommand.equalsIgnoreCase("resetkdr")) {
                        resetKDRCommand.execute(player, subargs);
                    } else if (subcommand.equalsIgnoreCase(lang("fee.command")) || subcommand.equalsIgnoreCase("fee")) {
                        feeCommand.execute(player, subargs);
                    } else if (subcommand.equalsIgnoreCase(lang("purge.command")) || subcommand.equalsIgnoreCase("purge")){
                        purgeCommand.execute(player, subargs);
                    } else if (subcommand.equalsIgnoreCase(lang("description.command")) || subcommand.equalsIgnoreCase("description")) {
                    	descriptionCommand.execute(player, subargs);
                    } else if (subcommand.equalsIgnoreCase(lang("rank.command")) || subcommand.equalsIgnoreCase("rank")) {
                    	rankCommand.execute(player, subargs);
                    } else if (subcommand.equalsIgnoreCase(lang("help.command")) || subcommand.equalsIgnoreCase("help")) {
                    	menuCommand.execute(player);
                    } else {   
                        ChatBlock.sendMessage(player, ChatColor.RED + lang("does.not.match"));
                    }
                }
            } else {
                if (args.length == 0) {
                    menuCommand.executeSender(sender);
                } else {
                    String subcommand = args[0];
                    String[] subargs = Helper.removeFirst(args);

                    if (subcommand.equalsIgnoreCase(lang("verify.command")) || subcommand.equalsIgnoreCase("verify")) {
                        verifyCommand.execute(sender, subargs);
                    } else if (subcommand.equalsIgnoreCase(lang("reload.command")) || subcommand.equalsIgnoreCase("reload")) {
                        reloadCommand.execute(sender, subargs);
                    } else if (subcommand.equalsIgnoreCase(lang("place.command")) || subcommand.equalsIgnoreCase("place")) {
                        placeCommand.execute(sender, subargs);
                    } else if (subcommand.equalsIgnoreCase(lang("globalff.command")) || subcommand.equalsIgnoreCase("globalff")) {
                        globalffCommand.execute(sender, subargs);
                    } else if (subcommand.equalsIgnoreCase(lang("purge.command")) || subcommand.equalsIgnoreCase("purge")) {
                        purgeCommand.execute(sender, subargs);
                    } else {
                        ChatBlock.sendMessage(sender, ChatColor.RED + lang("does.not.match"));
                    }
                }
            }
        } catch (Exception ex) {
            SimpleClans.getInstance().getServer().getConsoleSender().sendMessage(ChatColor.RED + MessageFormat.format(lang("simpleclans.command.failure"), ex.getMessage()));
            for (StackTraceElement el : ex.getStackTrace()) {
                System.out.print(el.toString());
            }
        }

        return false;
    }
}

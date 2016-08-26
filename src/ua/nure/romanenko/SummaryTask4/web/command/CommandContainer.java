package ua.nure.romanenko.SummaryTask4.web.command;

import org.apache.log4j.Logger;

import java.util.Map;
import java.util.TreeMap;

/**
 * Holder for all commands.<br/>
 */
public class CommandContainer {

    private static final Logger LOG = Logger.getLogger(CommandContainer.class);

    private static Map<String, Command> commands = new TreeMap<String, Command>();

    static {
        // common commands
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("viewSettings", new ViewSettingsCommand());
        commands.put("noCommand", new NoCommand());


        // client commands
        commands.put("listCatalog", new ListCatalogCommand());
        commands.put("makeAnOrder",new MakeAnOrderCommand());
        commands.put("private_office", new PrivateOfficeCommand());
        commands.put("filterCatalog", new FilterCatalogCommand());
        commands.put("confirmOrder", new ConfirmOrderCommand());

        // admin commands
        commands.put("listOrders", new ListOrdersCommand());
        commands.put("adminListOrders", new AdminListOrdersCommand());
        commands.put("doHotUnHot", new DoHotUnHotCommand());
        commands.put("adminListCatalog", new AdminListCatalogCommand());
        commands.put("discount", new DiscountSettingsCommand());

        LOG.debug("Command container was successfully initialized");
        LOG.trace("Number of commands --> " + commands.size());
    }

    /**
     * Returns command object with the given name.
     *
     * @param commandName Name of the command.
     * @return Command object.
     */
    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            LOG.trace("Command not found, name --> " + commandName);
            return commands.get("noCommand");
        }

        return commands.get(commandName);
    }

}
package ua.nure.romanenko.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.romanenko.SummaryTask4.Path;
import ua.nure.romanenko.SummaryTask4.db.dao.CatalogItemDAO;
import ua.nure.romanenko.SummaryTask4.db.dao.UserDAO;
import ua.nure.romanenko.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * Ban unBan command
 * Created by Роман on 27.08.2016.
 */
public class BanUnBanCommand extends Command {
    private static final long serialVersionUID = 9847694849593L;

    private static final Logger LOG = Logger.getLogger(BanUnBanCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        LOG.debug("BanUnBanCommand starts");

        String[] ban = request.getParameterValues("ban");
        String[] unBan = request.getParameterValues("unBan");

        LOG.trace("Found ban list --> " + Arrays.toString(ban));
        LOG.trace("Found unBan list --> " + Arrays.toString(unBan));

        LOG.trace("Result of ban method --> " + UserDAO.banUsers(ban));
        LOG.trace("Result of unBan method --> " + UserDAO.unBanUsers(unBan));

        LOG.debug("BanUnBanCommand starts");
        return Path.COMMAND_SHOW_ALL_USERS;

    }
}

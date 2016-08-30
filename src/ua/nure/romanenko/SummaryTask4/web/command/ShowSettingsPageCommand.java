package ua.nure.romanenko.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.romanenko.SummaryTask4.Path;
import ua.nure.romanenko.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Show Settings Page Command
 * Created by Роман on 30.08.2016.
 */
public class ShowSettingsPageCommand extends Command {
    private static final long serialVersionUID = 486036589475267L;

    private static final Logger LOG = Logger.getLogger(ShowSettingsPageCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        LOG.trace("ShowSettingsPageCommand starts");

        LOG.trace("ShowSettingsPageCommand finished");
        return Path.PAGE_SETTINGS;
    }
}

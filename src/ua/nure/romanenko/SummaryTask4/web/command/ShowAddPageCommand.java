package ua.nure.romanenko.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.romanenko.SummaryTask4.Path;
import ua.nure.romanenko.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Shows the add page
 * Created by Роман on 27.08.2016.
 */
public class ShowAddPageCommand extends Command {
    private static final long serialVersionUID = -8465037459837473L;

    private static final Logger LOG = Logger.getLogger(ShowAddPageCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        LOG.debug("ShowAddPageCommand starts");

        LOG.debug("ShowAddPageCommand finished");

        return Path.PAGE_ADD_CATALOG_ITEM;
    }

}

package ua.nure.romanenko.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.romanenko.SummaryTask4.Path;
import ua.nure.romanenko.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

/**
 * Update settings command
 * Created by Роман on 26.08.2016.
 */
public class UpdateSettingsCommand extends Command {

    private static final long serialVersionUID = 83412543286316546L;

    private static final Logger LOG = Logger.getLogger(FilterCatalogCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        LOG.trace("UpdateSettingsCommand starts");
// TODO: 26.08.2016 доделать UpdateSettingsCommand
//request.getLocale();
        Locale locale = new Locale(request.getParameter("language"));
        LOG.trace("find locale --> "+locale);

//        request.setAttribute("locale",locale);
//        response.setLocale(locale);


        return Path.PAGE_SETTINGS;
    }
}

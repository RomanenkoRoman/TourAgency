package ua.nure.romanenko.SummaryTask4.web.command;

import com.sun.org.apache.xml.internal.resolver.Catalog;
import org.apache.log4j.Logger;
import ua.nure.romanenko.SummaryTask4.Path;
import ua.nure.romanenko.SummaryTask4.db.DBManager;
import ua.nure.romanenko.SummaryTask4.db.dao.CatalogItemDAO;
import ua.nure.romanenko.SummaryTask4.db.entity.CatalogItem;
import ua.nure.romanenko.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Do hot or un hot statuses command
 * Created by Роман on 23.08.2016.
 */
public class DoHotUnHotCommand extends Command {

    private static final long serialVersionUID = 5437365813123L;

    private static final Logger LOG = Logger.getLogger(AdminListOrdersCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("DoHotCommand starts");

        String[] hot = request.getParameterValues("doHot");
        String[] unHot = request.getParameterValues("doUnHot");


        LOG.trace("Found hot list --> " + Arrays.toString(hot));
        LOG.trace("Found unHot list --> " + Arrays.toString(unHot));

        LOG.trace("Result of doHot method --> " + CatalogItemDAO.doHot(hot));
        LOG.trace("Result of doUnHot method --> " + CatalogItemDAO.doUnHot(unHot));


        LOG.debug("DoHotCommand finished");
        return Path.COMMAND_ADMIN_LIST_CATALOG;
    }
}

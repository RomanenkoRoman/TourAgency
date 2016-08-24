package ua.nure.romanenko.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.romanenko.SummaryTask4.Path;
import ua.nure.romanenko.SummaryTask4.db.DBManager;
import ua.nure.romanenko.SummaryTask4.db.entity.CatalogItem;
import ua.nure.romanenko.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 * Created by Роман on 22.08.2016.
 */
public class AdminListCatalogCommand extends Command{

    private static final long serialVersionUID = 321965128653123L;

    private static final Logger LOG = Logger.getLogger(AdminListCatalogCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("AdminListCatalogCommand starts");

        DBManager manager = DBManager.getInstance();
        List<CatalogItem> catalogItems = manager.findCatalogItems();
        LOG.trace("Find in db catalog items --> "+catalogItems);

        request.setAttribute("catalogItems", catalogItems);

        LOG.debug("AdminListCatalogCommand finished");
        return Path.PAGE_ADMIN_LIST_CATALOG_HOT;
    }
}

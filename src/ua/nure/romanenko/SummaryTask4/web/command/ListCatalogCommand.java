package ua.nure.romanenko.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.romanenko.SummaryTask4.Path;
import ua.nure.romanenko.SummaryTask4.db.DBManager;
import ua.nure.romanenko.SummaryTask4.db.entity.CatalogItem;
import ua.nure.romanenko.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Lists menu items.
 */
public class ListCatalogCommand extends Command {

	private static final long serialVersionUID = 7732286214029478505L;

	private static final Logger LOG = Logger.getLogger(ListCatalogCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			AppException {
		
		LOG.debug("Command starts");
		HttpSession session = request.getSession();
		// get menu items list
		List<CatalogItem> catalogItems = DBManager.getInstance().findCatalogItems();
		LOG.trace("Found in DB: CatalogItemsList --> " + catalogItems);
		
		// sort menu by category
//		Collections.sort(catalogItems, new Comparator<CatalogItem>() {
//			public int compare(CatalogItem o1, CatalogItem o2) {
//				return
//						(o1.getCategoryId() - o2.getCategoryId());
//			}
//		});
		
		// put menu items list to the request
		session.setAttribute("catalogItems", catalogItems);
		LOG.trace("Set the request attribute: catalogItems --> " + catalogItems);
		
		LOG.debug("Command finished");
		return Path.PAGE_LIST_CATALOG;
	}

}
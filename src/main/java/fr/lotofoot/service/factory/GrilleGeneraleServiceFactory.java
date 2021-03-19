package fr.lotofoot.service.factory;


import fr.lotofoot.service.GrilleGeneraleService;
import fr.lotofoot.service.impl.GrilleGeneraleServiceImpl;

public class GrilleGeneraleServiceFactory {

	private static GrilleGeneraleService grilleGeneraleService = null;
	
	public static GrilleGeneraleService getGrilleGeneraleService() {
		if (grilleGeneraleService == null) {
		    grilleGeneraleService = new GrilleGeneraleServiceImpl();
		}
		return grilleGeneraleService;
	}

}
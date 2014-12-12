package pl.radek.dvd.logic.builder;

import org.springframework.orm.hibernate3.HibernateTemplate;
import pl.radek.dvd.dto.ListDataRequest;

/**
 * User: Sola
 * Date: 2014-07-29
 * Time: 12:45
 */

public final class ChoiceFiltreQueryFactory {

    private ChoiceFiltreQueryFactory() {
    }

    public static MultiFiltreChoice getMultiFiltreChoice(ListDataRequest listDataRequest, HibernateTemplate hibernateTemplate) {
        //logika dla wyboru Query Filtra, np jesli mamy dane wartosci to MovieFiltreChoice, a jesli inne to ClientFiltreChoice
        /*if (true) {

        } else {

        }*/

        return new MovieFiltreChoice(listDataRequest, hibernateTemplate);
    }

    public static MultiFiltreChoice getMultiFiltreChoice(ListDataRequest listDataRequest, HibernateTemplate hibernateTemplate, String dtoName) {
        MultiFiltreChoice multiFiltreChoice = null;

        //logika dla wyboru Query Filtra, np jesli mamy dane wartosci to MovieFiltreChoice, a jesli inne to ClientFiltreChoice
        if (dtoName.equals("moviesData")) {
            multiFiltreChoice = new MovieFiltreChoice(listDataRequest, hibernateTemplate);
        } else if (dtoName.equals("moviesRentData")) {
            multiFiltreChoice = new MovieRentFilterChoice(listDataRequest, hibernateTemplate);
        } else if (dtoName.equals("MovieNotReturnedDto")) {
            multiFiltreChoice = new MovieNotReturnedFilterChoice(listDataRequest, hibernateTemplate);
        } else if (dtoName.equals("TopHitsDto")) {
            multiFiltreChoice = new TopHitsFilterChoice(listDataRequest, hibernateTemplate);
        }

        return multiFiltreChoice;
    }
}

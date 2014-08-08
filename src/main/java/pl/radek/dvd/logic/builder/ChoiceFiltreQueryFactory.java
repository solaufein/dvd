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
}

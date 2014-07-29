package pl.radek.dvd.logic.builder;

import org.springframework.orm.hibernate3.HibernateTemplate;
import pl.radek.dvd.dto.ListDataRequest;

/**
 * User: Sola
 * Date: 2014-07-29
 * Time: 12:45
 */

public class ChoiceFiltreQueryFactory {
    private MultiFiltreChoice multiFiltreChoice;

    public MultiFiltreChoice getMultiFiltreChoice(ListDataRequest listDataRequest, HibernateTemplate hibernateTemplate) {
        //logika dla wyboru Query Filtra, np jesli mamy dane wartosci to MovieFiltreChoice, a jesli inne to ClientFiltreChoice
        /*if (true) {

        } else {

        }*/

        multiFiltreChoice = new MovieFiltreChoice(listDataRequest, hibernateTemplate);
        return multiFiltreChoice;
    }
}

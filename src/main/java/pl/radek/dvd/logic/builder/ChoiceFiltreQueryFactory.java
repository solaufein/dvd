package pl.radek.dvd.logic.builder;

import org.springframework.orm.hibernate3.HibernateTemplate;
import pl.radek.dvd.dto.ListDataRequest;

public final class ChoiceFiltreQueryFactory {

    private ChoiceFiltreQueryFactory() {
    }

    public static MultiFiltreChoice getMultiFiltreChoice(ListDataRequest listDataRequest, HibernateTemplate hibernateTemplate) {

        return new MovieFiltreChoice(listDataRequest, hibernateTemplate);
    }

    public static MultiFiltreChoice getMultiFiltreChoice(ListDataRequest listDataRequest, HibernateTemplate hibernateTemplate, String dtoName) {
        MultiFiltreChoice multiFiltreChoice = null;

        //logic to choose Query Filter
        if (dtoName.equals("moviesData")) {
            multiFiltreChoice = new MovieFiltreChoice(listDataRequest, hibernateTemplate);
        } else if (dtoName.equals("moviesRentData")) {
            multiFiltreChoice = new MovieRentFilterChoice(listDataRequest, hibernateTemplate);
        } else if (dtoName.equals("MovieNotReturnedDto")) {
            multiFiltreChoice = new MovieNotReturnedFilterChoice(listDataRequest, hibernateTemplate);
        } else if (dtoName.equals("TopHitsDto")) {
            multiFiltreChoice = new TopHitsFilterChoice(listDataRequest, hibernateTemplate);
        } else if (dtoName.equals("IncomeDto")) {
            multiFiltreChoice = new IncomeDtoFilterChoice(listDataRequest, hibernateTemplate);
        } else if (dtoName.equals("RentPromotionDto")) {
            multiFiltreChoice = new RentPromotionDtoFilterChoice(listDataRequest, hibernateTemplate);
        }

        return multiFiltreChoice;
    }
}

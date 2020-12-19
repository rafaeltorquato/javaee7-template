package study.business.domain.model.person;

public interface PersonCountSummaryDao {

    PersonCountSummary save(PersonCountSummary personCountSummary);

    PersonCountSummary findById(Long id);

}

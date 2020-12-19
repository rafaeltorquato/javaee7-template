package study.business.domain.model.summary;

public interface PersonCountSummaryDao {

    PersonCountSummary save(PersonCountSummary personCountSummary);

    PersonCountSummary findById(Long id);

}

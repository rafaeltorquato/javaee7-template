package study.business.domain.model.summary;

public interface PersonCountSummaryDao {

    PersonCountSummary save(PersonCountSummary summary);

    PersonCountSummary findById(Long id);

    void delete(PersonCountSummary summary);
}

package study.business.application.jobs.person;

import java.io.Serializable;

public class PersonCountSummaryCheckpoint implements Serializable {

    private int index = 0;

    public int getIndex() {
        return index;
    }

    public void next() {
        index = index + 1;
    }

}

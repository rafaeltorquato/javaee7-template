package study.business.application.jobs.person.listener;

import javax.batch.api.chunk.listener.ChunkListener;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Dependent
@Named("ChunkListenerImpl")
public class ChunkListenerImpl implements ChunkListener {

    @Override
    public void beforeChunk() throws Exception {

    }

    @Override
    public void onError(Exception ex) throws Exception {

    }

    @Override
    public void afterChunk() throws Exception {

    }
}

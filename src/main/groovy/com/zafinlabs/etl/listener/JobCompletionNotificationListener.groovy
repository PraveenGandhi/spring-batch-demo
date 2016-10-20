package com.zafinlabs.etl.listener

import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.listener.JobExecutionListenerSupport
import org.springframework.stereotype.Component

/**
 * Created by zcoe342 on 20-10-2016.
 */
@Component
class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    void afterJob(JobExecution jobExecution) {
        println 'Done'
    }
}

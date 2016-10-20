package com.zafinlabs.etl.writer

import com.zafinlabs.etl.domain.AccountMaster
import com.zafinlabs.etl.repository.AccountMasterRepository
import org.springframework.batch.item.ItemWriter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * Created by zcoe342 on 20-10-2016.
 */
@Component
class AccountMasterOracleTableWriter implements ItemWriter<AccountMaster>{

    @Autowired
    AccountMasterRepository accountMasterRepository

    void write(List<? extends AccountMaster> items) throws Exception {
        items.each {
            accountMasterRepository.save it
        }
    }
}

package com.zafinlabs.etl.processor

import com.zafinlabs.etl.domain.AccountMaster
import org.springframework.batch.item.ItemProcessor
import org.springframework.stereotype.Component

/**
 * Created by zcoe342 on 20-10-2016.
 */
@Component
class AccountMasterEventProcessor implements ItemProcessor<AccountMaster, AccountMaster> {

    AccountMaster process(final AccountMaster accountMaster) throws Exception {
        new AccountMaster(relationshipNumber: "REL_${accountMaster.relationshipNumber.trim()}",
                accountNumber: "ACCT_${accountMaster.accountNumber.trim()}",
                type: accountMaster.type.trim())
    }
}
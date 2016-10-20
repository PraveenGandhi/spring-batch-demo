package com.zafinlabs.etl.repository

import com.zafinlabs.etl.domain.AccountMaster
import org.springframework.data.repository.CrudRepository

/**
 * Created by zcoe342 on 20-10-2016.
 */
interface AccountMasterRepository extends CrudRepository<AccountMaster, Long> {}

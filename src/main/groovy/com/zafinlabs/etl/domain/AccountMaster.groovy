package com.zafinlabs.etl.domain

import javax.persistence.*

/**
 * Created by zcoe342 on 20-10-2016.
 */
@Entity
class AccountMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ACC_MASTER")
    @SequenceGenerator(name = "SEQ_ACC_MASTER", sequenceName = "SEQ_ACC_MASTER", allocationSize = 1)
    Long id
    String accountNumber
    String relationshipNumber
    String type
}

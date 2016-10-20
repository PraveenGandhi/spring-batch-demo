package com.zafinlabs.etl.config

import com.zafinlabs.etl.domain.AccountMaster
import com.zafinlabs.etl.listener.JobCompletionNotificationListener
import com.zafinlabs.etl.processor.AccountMasterEventProcessor
import com.zafinlabs.etl.writer.AccountMasterOracleTableWriter
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.item.excel.RowMapper
import org.springframework.batch.item.excel.poi.PoiItemReader
import org.springframework.batch.item.excel.support.rowset.RowSet
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource

/**
 * Created by zcoe342 on 20-10-2016.
 */
@Configuration
class BatchConfiguration {

    @Autowired JobBuilderFactory jobBuilderFactory
    @Autowired StepBuilderFactory stepBuilderFactory
    @Autowired AccountMasterEventProcessor accountMasterEventProcessor
    @Autowired AccountMasterOracleTableWriter accountMasterOracleTableWriter
    @Autowired JobCompletionNotificationListener jobCompletionNotificationListener

    @Bean
    def accountMasterETLJob() {
        jobBuilderFactory.get("AccountMaster ETL Job")
                .incrementer(new RunIdIncrementer())
                .listener(jobCompletionNotificationListener)
                .flow(etlStep())
                .end()
                .build()
    }

    @Bean
    def etlStep() {
        stepBuilderFactory.get("Extract -> Transform -> Load").<AccountMaster, AccountMaster> chunk(10000)
                .reader(excelAccountMasterReader())     //  Extract
                .processor(accountMasterEventProcessor) //  Transform
                .writer(accountMasterOracleTableWriter) //  Load
                .build()
    }

    @Bean
    def excelAccountMasterReader() {
        PoiItemReader<AccountMaster> reader = new PoiItemReader<>()
        reader.setLinesToSkip(1)//headings
        reader.setResource(new ClassPathResource("ACCOUNT_MASTER.xlsx"))
        reader.setRowMapper(excelRowMapper())
        reader
    }

    @Bean
    def excelRowMapper() {
        new RowMapper<AccountMaster>() {
            AccountMaster mapRow(RowSet rowSet) throws Exception {
                new AccountMaster(relationshipNumber: rowSet.getColumnValue(0),
                        accountNumber: rowSet.getColumnValue(1),
                        type: rowSet.getColumnValue(2))
            }
        }
    }

}

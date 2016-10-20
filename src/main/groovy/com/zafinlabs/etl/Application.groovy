package com.zafinlabs.etl

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
@EnableBatchProcessing
class Application {

    static def main(args) {
        SpringApplication.run Application, args
    }
}

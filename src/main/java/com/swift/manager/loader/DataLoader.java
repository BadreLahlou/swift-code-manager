package com.swift.manager.loader;

import com.swift.manager.entity.SwiftCode;
import com.swift.manager.repository.SwiftCodeRepository;
import com.swift.manager.util.CsvParserUtil;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {
    private final SwiftCodeRepository repository;

    public DataLoader(SwiftCodeRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        if (repository.count() == 0) {
            List<SwiftCode> swiftCodes = CsvParserUtil.parseCsv("swift_codes.csv");
            repository.saveAll(swiftCodes);
        }
    }
}
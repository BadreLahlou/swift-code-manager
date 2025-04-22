package com.swift.manager.loader;

import java.util.List;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.swift.manager.entity.SwiftCode;
import com.swift.manager.repository.SwiftCodeRepository;
import com.swift.manager.util.CsvParserUtil;

@Component
@DependsOn("entityManagerFactory")
@Profile("!test")
public class DataLoader {
    private final SwiftCodeRepository repository;

    public DataLoader(SwiftCodeRepository repository) {
        this.repository = repository;
    }

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void loadData() {
        if (repository.count() == 0) {
            List<SwiftCode> swiftCodes = CsvParserUtil.parseCsv("data/swift_codes.csv");
            repository.saveAll(swiftCodes);
        }
    }
}
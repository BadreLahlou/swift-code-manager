package com.swift.manager.service;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.swift.manager.dto.request.SwiftCodeRequestDto;
import com.swift.manager.entity.SwiftCode;
import com.swift.manager.repository.SwiftCodeRepository;
import com.swift.manager.service.impl.SwiftCodeServiceImpl;

@ExtendWith(MockitoExtension.class)
@SuppressWarnings({"unused", "ResultOfMethodCallIgnored"})
class SwiftCodeServiceTest {
    @Mock
    private SwiftCodeRepository repository;

    @InjectMocks
    private SwiftCodeServiceImpl service;

    private SwiftCode swiftCode;
    private SwiftCodeRequestDto request;

    @BeforeEach
    void setUp() {
        swiftCode = new SwiftCode();
        swiftCode.setSwiftCode("DEUTDEFF");
        swiftCode.setAddress("Frankfurt");
        swiftCode.setBankName("Deutsche Bank");
        swiftCode.setCountryISO2("DE");
        swiftCode.setCountryName("Germany");
        swiftCode.setHeadquarter(true);
        swiftCode.setHeadquarterCode("DEUTDEFF");

        request = new SwiftCodeRequestDto();
        request.setSwiftCode("DEUTDEFF");
        request.setAddress("Frankfurt");
        request.setBankName("Deutsche Bank");
        request.setCountryISO2("DE");
        request.setCountryName("Germany");
        request.setHeadquarter(true);
        request.setHeadquarterCode("DEUTDEFF");
    }

    @Test
    void getSwiftCodeDetailsFound() {
        when(repository.findById("DEUTDEFF")).thenReturn(Optional.of(swiftCode));
        Object result = service.getSwiftCodeDetails("DEUTDEFF");
        assertNotNull(result);
    }

    @Test
    void getSwiftCodeDetailsNotFound() {
        when(repository.findById("INVALID")).thenReturn(Optional.empty());
        Exception ex = Assertions.assertThrows(Exception.class, () -> service.getSwiftCodeDetails("INVALID"));
        Assertions.assertNotNull(ex);
    }

    @Test
    void addSwiftCode() {
        when(repository.save(any(SwiftCode.class))).thenReturn(swiftCode);
        String result = service.addSwiftCode(request);
        assertNotNull(result);
    }

    @Test
    void findByCountry() {
        when(repository.findByCountryISO2("DE")).thenReturn(List.of(swiftCode));
        Object result = service.findByCountry("DE");
        assertNotNull(result);
    }
}
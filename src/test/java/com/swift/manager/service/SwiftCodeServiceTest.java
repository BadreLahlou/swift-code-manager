package com.swift.manager.service;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.swift.manager.dto.request.SwiftCodeRequestDto;
import com.swift.manager.dto.response.SwiftCodeResponseDto;
import com.swift.manager.entity.SwiftCode;
import com.swift.manager.repository.SwiftCodeRepository;
import com.swift.manager.service.impl.SwiftCodeServiceImpl;

@ExtendWith(MockitoExtension.class)
class SwiftCodeServiceTest {
    @Mock
    private SwiftCodeRepository repository;

    @InjectMocks
    private SwiftCodeServiceImpl service;

    private SwiftCode swiftCode;
    private SwiftCodeRequestDto request;

    @BeforeEach
    @SuppressWarnings("unused")
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
        when(repository.findBySwiftCode("DEUTDEFF")).thenReturn(Optional.of(swiftCode));
        Object result = service.getSwiftCodeDetails("DEUTDEFF");
        assertNotNull(result);
    }

    @Test
    void getSwiftCodeDetailsNotFound() {
        when(repository.findBySwiftCode("INVALID")).thenReturn(Optional.empty());
        Exception ex = assertThrows(Exception.class, () -> service.getSwiftCodeDetails("INVALID"));
        assertNotNull(ex);
    }

    @Test
    void addSwiftCode() {
        when(repository.save(any(SwiftCode.class))).thenReturn(swiftCode);
        SwiftCodeResponseDto result = service.addSwiftCode(request);
        assertNotNull(result);
    }

    @Test
    void findByCountry() {
        when(repository.findByCountryISO2("DE")).thenReturn(List.of(swiftCode));
        Object result = service.findByCountry("DE");
        assertNotNull(result);
    }

    @Test
    void deleteSwiftCodeSuccess() {
        when(repository.findBySwiftCode("DEUTDEFF")).thenReturn(Optional.of(swiftCode));
        String result = service.deleteSwiftCode("DEUTDEFF");
        assertNotNull(result);
    }

    @Test
    void deleteSwiftCodeNotFound() {
        when(repository.findBySwiftCode("INVALID")).thenReturn(Optional.empty());
        Exception ex = assertThrows(Exception.class, () -> service.deleteSwiftCode("INVALID"));
        assertNotNull(ex);
    }

    @Test
    void findBranches() {
        SwiftCode branch = new SwiftCode();
        branch.setSwiftCode("DEUTDEMM");
        branch.setAddress("Munich");
        branch.setBankName("Deutsche Bank");
        branch.setCountryISO2("DE");
        branch.setCountryName("Germany");
        branch.setHeadquarter(false);
        branch.setHeadquarterCode("DEUTDEFF");
        when(repository.findBranchesByHeadquarter("DEUTDEFF")).thenReturn(List.of(branch));
        var result = service.findBranches("DEUTDEFF");
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals("DEUTDEMM", result.get(0).getSwiftCode());
    }
}
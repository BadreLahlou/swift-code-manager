package com.swift.manager.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swift.manager.dto.request.SwiftCodeRequestDto;
import com.swift.manager.entity.SwiftCode;
import com.swift.manager.repository.SwiftCodeRepository;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@ActiveProfiles("test")
@SuppressWarnings({"unused", "resource"})
class SwiftCodeControllerIntegrationTest {
    @Container
    @SuppressWarnings("unused")
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine")
        .withDatabaseName("swiftdb")
        .withUsername("swiftuser")
        .withPassword("swiftpass");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SwiftCodeRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
        SwiftCode code = new SwiftCode();
        code.setSwiftCode("DEUTDEFF");
        code.setAddress("Frankfurt");
        code.setBankName("Deutsche Bank");
        code.setCountryISO2("DE");
        code.setCountryName("Germany");
        code.setHeadquarter(true);
        code.setHeadquarterCode("DEUTDEFF");
        repository.save(code);
    }

    @Test
    void getSwiftCodeFound() throws Exception {
        mockMvc.perform(get("/v1/swift-codes/DEUTDEFF")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.swiftCode").value("DEUTDEFF"));
    }

    @Test
    void getSwiftCodeNotFound() throws Exception {
        mockMvc.perform(get("/v1/swift-codes/INVALID")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.errorCode").value("SWIFT_CODE_NOT_FOUND"));
    }

    @Test
    void addSwiftCode() throws Exception {
        SwiftCodeRequestDto request = new SwiftCodeRequestDto();
        request.setSwiftCode("BOFAUS3N");
        request.setAddress("New York");
        request.setBankName("Bank of America");
        request.setCountryISO2("US");
        request.setCountryName("United States");
        request.setHeadquarter(true);
        request.setHeadquarterCode("BOFAUS3N");

        mockMvc.perform(post("/v1/swift-codes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.swiftCode").value("BOFAUS3N"));
    }

    @Test
    void getSwiftCodesByCountry() throws Exception {
        mockMvc.perform(get("/v1/swift-codes/country/DE")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.countryISO2").value("DE"))
            .andExpect(jsonPath("$.swiftCodes").isArray());
    }

    @Test
    void deleteSwiftCodeSuccess() throws Exception {
        mockMvc.perform(delete("/v1/swift-codes/DEUTDEFF"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.message").value("SWIFT code deleted successfully."));
    }

    @Test
    void deleteSwiftCodeNotFound() throws Exception {
        mockMvc.perform(delete("/v1/swift-codes/INVALID"))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.errorCode").value("SWIFT_CODE_NOT_FOUND"));
    }

    @Test
    void addSwiftCodeInvalidInput() throws Exception {
        SwiftCodeRequestDto request = new SwiftCodeRequestDto();
        // Missing required fields
        mockMvc.perform(post("/v1/swift-codes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.errorCode").value("INVALID_INPUT"));
    }

    @Test
    void getBranchesForHeadquarter() throws Exception {
        // Add a branch for the headquarter
        SwiftCode branch = new SwiftCode();
        branch.setSwiftCode("DEUTDEMM");
        branch.setAddress("Munich");
        branch.setBankName("Deutsche Bank");
        branch.setCountryISO2("DE");
        branch.setCountryName("Germany");
        branch.setHeadquarter(false);
        branch.setHeadquarterCode("DEUTDEFF");
        repository.save(branch);

        mockMvc.perform(get("/v1/swift-codes/branches/DEUTDEFF")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].swiftCode").value("DEUTDEMM"));
    }
}
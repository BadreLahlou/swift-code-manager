package com.swift.manager.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.swift.manager.dto.request.SwiftCodeRequestDto;
import com.swift.manager.dto.response.SwiftCodeResponseDto;
import com.swift.manager.dto.response.CountrySwiftCodesResponse;
import com.swift.manager.service.SwiftCodeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/swift-codes")
@Tag(name = "SWIFT Code Management", description = "Endpoints for managing SWIFT codes")
public class SwiftCodeController {

    private final SwiftCodeService service;

    public SwiftCodeController(SwiftCodeService service) {
        this.service = service;
    }

    @Operation(summary = "Add a new SWIFT code", responses = {
        @ApiResponse(responseCode = "200", description = "SWIFT code added successfully")
    })
    @PostMapping
    public ResponseEntity<Map<String, String>> addSwiftCode(@Valid @RequestBody SwiftCodeRequestDto request) {
        String message = service.addSwiftCode(request);
        return ResponseEntity.ok(Map.of("message", message));
    }

    @Operation(summary = "Get SWIFT code details", responses = {
        @ApiResponse(responseCode = "200", description = "SWIFT code found"),
        @ApiResponse(responseCode = "404", description = "SWIFT code not found")
    })
    @GetMapping("/{swiftCode}")
    public ResponseEntity<?> getSwiftCode(
            @Parameter(description = "SWIFT code to lookup")
            @PathVariable String swiftCode) {
        Object response = service.getSwiftCodeDetails(swiftCode);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get all SWIFT codes for a country", responses = {
        @ApiResponse(responseCode = "200", description = "List of SWIFT codes for the country")
    })
    @GetMapping("/country/{countryISO2}")
    public ResponseEntity<CountrySwiftCodesResponse> getSwiftCodesByCountry(
            @Parameter(description = "Country ISO2 code")
            @PathVariable String countryISO2) {
        CountrySwiftCodesResponse response = service.findByCountry(countryISO2);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete a SWIFT code", responses = {
        @ApiResponse(responseCode = "200", description = "SWIFT code deleted successfully"),
        @ApiResponse(responseCode = "404", description = "SWIFT code not found")
    })
    @DeleteMapping("/{swiftCode}")
    public ResponseEntity<Map<String, String>> deleteSwiftCode(@PathVariable String swiftCode) {
        String message = service.deleteSwiftCode(swiftCode);
        return ResponseEntity.ok(Map.of("message", message));
    }

    @Operation(summary = "Get all branches for a headquarter SWIFT code", responses = {
        @ApiResponse(responseCode = "200", description = "List of branch SWIFT codes")
    })
    @GetMapping("/branches/{headquarterCode}")
    public ResponseEntity<List<SwiftCodeResponseDto>> getBranches(
            @Parameter(description = "Headquarter SWIFT code")
            @PathVariable String headquarterCode) {
        List<SwiftCodeResponseDto> response = service.findBranches(headquarterCode);
        return ResponseEntity.ok(response);
    }
}
package com.swift.manager.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swift.manager.dto.request.SwiftCodeRequestDto;
import com.swift.manager.dto.response.CountrySwiftCodesResponse;
import com.swift.manager.dto.response.SwiftCodeBranchDto;
import com.swift.manager.dto.response.SwiftCodeResponseDto;
import com.swift.manager.entity.SwiftCode;
import com.swift.manager.exception.SwiftCodeNotFoundException;
import com.swift.manager.repository.SwiftCodeRepository;
import com.swift.manager.service.SwiftCodeService;

@Service
public class SwiftCodeServiceImpl implements SwiftCodeService {

    private static final Logger logger = LoggerFactory.getLogger(SwiftCodeServiceImpl.class);

    private final SwiftCodeRepository repository;

    public SwiftCodeServiceImpl(SwiftCodeRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    @CacheEvict(value = {"swiftCodes", "countryCodes"}, allEntries = true)
    public SwiftCodeResponseDto addSwiftCode(SwiftCodeRequestDto request) {
        SwiftCode swiftCode = new SwiftCode();
        swiftCode.setSwiftCode(request.getSwiftCode());
        swiftCode.setAddress(request.getAddress());
        swiftCode.setBankName(request.getBankName());
        swiftCode.setCountryISO2(request.getCountryISO2().toUpperCase());
        swiftCode.setCountryName(request.getCountryName().toUpperCase());
        swiftCode.setHeadquarter(request.getHeadquarter());
        swiftCode.setHeadquarterCode(request.getHeadquarterCode());
        repository.save(swiftCode);
        logger.info("Added new SWIFT code: {}", swiftCode.getSwiftCode());
        SwiftCodeResponseDto resp = new SwiftCodeResponseDto();
        resp.setSwiftCode(swiftCode.getSwiftCode());
        resp.setAddress(swiftCode.getAddress());
        resp.setBankName(swiftCode.getBankName());
        resp.setCountryISO2(swiftCode.getCountryISO2());
        resp.setCountryName(swiftCode.getCountryName());
        resp.setHeadquarter(swiftCode.isHeadquarter());
        resp.setHeadquarterCode(swiftCode.getHeadquarterCode());
        return resp;
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "swiftCodes", key = "#swiftCode")
    public SwiftCodeResponseDto getSwiftCodeDetails(String swiftCode) {
        SwiftCode code = repository.findBySwiftCode(swiftCode)
                .orElseThrow(() -> new SwiftCodeNotFoundException("Swift code not found: " + swiftCode));
        SwiftCodeResponseDto resp = new SwiftCodeResponseDto();
        resp.setSwiftCode(code.getSwiftCode());
        resp.setAddress(code.getAddress());
        resp.setBankName(code.getBankName());
        resp.setCountryISO2(code.getCountryISO2());
        resp.setCountryName(code.getCountryName());
        resp.setHeadquarter(code.isHeadquarter());
        resp.setHeadquarterCode(code.getHeadquarterCode());
        return resp;
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "countryCodes", key = "#countryISO2")
    public CountrySwiftCodesResponse findByCountry(String countryISO2) {
        List<SwiftCode> codes = repository.findByCountryISO2(countryISO2.toUpperCase());
        CountrySwiftCodesResponse resp = new CountrySwiftCodesResponse();
        resp.setCountryISO2(countryISO2.toUpperCase());
        if (!codes.isEmpty()) {
            resp.setCountryName(codes.get(0).getCountryName());
        }
        resp.setSwiftCodes(codes.stream().map(this::mapToBranchDto).toList());
        return resp;
    }

    @Override
    @Transactional
    @CacheEvict(value = {"swiftCodes", "countryCodes"}, allEntries = true)
    public String deleteSwiftCode(String swiftCode) {
        SwiftCode code = repository.findBySwiftCode(swiftCode)
                .orElseThrow(() -> new SwiftCodeNotFoundException("Swift code not found: " + swiftCode));
        repository.delete(code);
        logger.info("Deleted SWIFT code: {}", swiftCode);
        return "SWIFT code deleted successfully.";
    }

    @Override
    @Transactional(readOnly = true)
    public List<SwiftCodeResponseDto> findBranches(String headquarterCode) {
        List<SwiftCode> branches = repository.findBranchesByHeadquarter(headquarterCode);
        return branches.stream().map(this::mapToResponseDto).toList();
    }

    private SwiftCodeBranchDto mapToBranchDto(SwiftCode entity) {
        SwiftCodeBranchDto dto = new SwiftCodeBranchDto();
        dto.setSwiftCode(entity.getSwiftCode());
        dto.setAddress(entity.getAddress());
        dto.setBankName(entity.getBankName());
        dto.setCountryISO2(entity.getCountryISO2());
        dto.setHeadquarter(entity.isHeadquarter());
        return dto;
    }

    private SwiftCodeResponseDto mapToResponseDto(SwiftCode entity) {
        SwiftCodeResponseDto response = new SwiftCodeResponseDto();
        response.setSwiftCode(entity.getSwiftCode());
        response.setAddress(entity.getAddress());
        response.setBankName(entity.getBankName());
        response.setCountryISO2(entity.getCountryISO2());
        response.setCountryName(entity.getCountryName());
        response.setHeadquarter(entity.isHeadquarter());
        response.setHeadquarterCode(entity.getHeadquarterCode());
        return response;
    }
}
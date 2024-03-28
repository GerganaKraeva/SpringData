package com.example.demo.service.impl;


import com.example.demo.data.entities.Supplier;
import com.example.demo.data.repositories.SupplierRepository;
import com.example.demo.service.SupplierService;
import com.example.demo.service.dto.exports.SupplierLocalDto;
import com.example.demo.service.dto.exports.SupplierLocalRootDto;
import com.example.demo.service.dto.imports.SupplierSeedDto;
import com.example.demo.service.dto.imports.SupplierSeedRootDto;
import com.example.demo.util.ValidationUtil;
import com.example.demo.util.XmlParser;
import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {

    private static  final String FILE_IMPORT_PATH= "src/main/resources/xml/import/suppliers.xml";
    private static  final String FILE_EXPORT_PATH= "src/main/resources/xml/export/local-supplier.xml";

    private final XmlParser xmlParser;
    private final SupplierRepository supplierRepository;

    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public SupplierServiceImpl(XmlParser xmlParser, SupplierRepository supplierRepository, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.xmlParser = xmlParser;
        this.supplierRepository = supplierRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }


    public void seedSupplier() throws JAXBException {
        if(this.supplierRepository.count()==0) {
            SupplierSeedRootDto supplierSeedRootDto = xmlParser.parse(SupplierSeedRootDto.class, FILE_IMPORT_PATH);
            for (SupplierSeedDto supplierSeedDto : supplierSeedRootDto.getSupplierSeedDtoList()) {
                if(!this.validationUtil.isValid(supplierSeedDto)){
//                    this.validationUtil.getViolations(supplierSeedDto)
//                            .forEach(v-> System.out.println(v.getMessage()));
                    System.out.println("Invalid supplier data");
                    continue;
                }
                Supplier supplier = this.modelMapper.map(supplierSeedDto, Supplier.class);
                this.supplierRepository.saveAndFlush(supplier);
            }
        }


    }

    @Override
    public void exportLocalSuppliers() throws JAXBException {
        List<SupplierLocalDto> supplierLocalDtos = this.supplierRepository.findAllByIsImporter(false)
                .stream()
                .map(s -> {
                    SupplierLocalDto dto = this.modelMapper.map(s, SupplierLocalDto.class);
                    dto.setPartsCount(s.getParts().size());
                    return dto;
                })
                .collect(Collectors.toList());
        SupplierLocalRootDto supplierLocalRootDto=new SupplierLocalRootDto();
        supplierLocalRootDto.setSupplierLocalDtoList(supplierLocalDtos);

        this.xmlParser.exportToFile(SupplierLocalRootDto.class,supplierLocalRootDto, FILE_EXPORT_PATH);

    }
}

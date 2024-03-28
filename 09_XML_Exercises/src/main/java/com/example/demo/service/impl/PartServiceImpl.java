package com.example.demo.service.impl;

import com.example.demo.data.entities.Part;
import com.example.demo.data.entities.Supplier;
import com.example.demo.data.repositories.PartRepository;
import com.example.demo.data.repositories.SupplierRepository;
import com.example.demo.service.PartService;
import com.example.demo.service.dto.imports.PartSeedDto;
import com.example.demo.service.dto.imports.PartSeedRootDto;
import com.example.demo.service.dto.imports.SupplierSeedDto;
import com.example.demo.service.dto.imports.SupplierSeedRootDto;
import com.example.demo.util.ValidationUtil;
import com.example.demo.util.XmlParser;
import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class PartServiceImpl implements PartService {
    private static  final String FILE_IMPORT_PATH= "src/main/resources/xml/import/parts.xml";

    private  final PartRepository partRepository;

   private final SupplierRepository supplierRepository;

   private final ModelMapper modelMapper;

   private final ValidationUtil validationUtil;

   private final XmlParser xmlParser;

    public PartServiceImpl( PartRepository partRepository, SupplierRepository supplierRepository, ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser) {
        this.partRepository = partRepository;
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
    }


    @Override
    public void seedParts() throws JAXBException {
            PartSeedRootDto partSeedRootDto = xmlParser.parse(PartSeedRootDto.class, FILE_IMPORT_PATH);
        for (PartSeedDto partSeedDto : partSeedRootDto.getPartSeedDtoList()) {
            if(!this.validationUtil.isValid(partSeedDto)){
//                    this.validationUtil.getViolations(supplierSeedDto)
//                            .forEach(v-> System.out.println(v.getMessage()));
                    System.out.println("Invalid part data");
                    continue;
                }
               Part part = this.modelMapper.map(partSeedDto, Part.class);
                part.setSupplier(getRandomSupplier());
                this.partRepository.saveAndFlush(part);
            }
        }

    private Supplier getRandomSupplier() {
        return this.supplierRepository
                .findById(
                        ThreadLocalRandom.current().nextLong(1,this.supplierRepository.count()+1)
                )
                .get();
    }
}

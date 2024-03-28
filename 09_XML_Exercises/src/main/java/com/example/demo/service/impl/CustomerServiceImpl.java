package com.example.demo.service.impl;

import com.example.demo.data.entities.Customer;
import com.example.demo.data.repositories.CustomerRepository;
import com.example.demo.service.CustomerService;
import com.example.demo.service.dto.CustomerSeedRootDto;
import com.example.demo.service.dto.exports.CustomerOrderedDto;
import com.example.demo.service.dto.exports.CustomerOrderedRootDto;
import com.example.demo.service.dto.exports.CustomerTotalSaleDto;
import com.example.demo.service.dto.exports.CustomersTotalSalesRootDto;
import com.example.demo.util.XmlParser;
import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private static final String FILE_IMPORT_PATH = "src/main/resources/xml/import/customers.xml";
    private static final String FILE_EXPORT_CUSTOMERS_PATH = "src/main/resources/xml/export/ordered-customers.xml";

    private static final String FILE_EXPORT_BOUGHT_CARS="src/main/resources/xml/export/customers-bought-cars.xml";
    private final CustomerRepository customerRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, XmlParser xmlParser, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedCustomers() throws JAXBException {
        if (this.customerRepository.count() == 0) {
            CustomerSeedRootDto customerSeedRootDto = this.xmlParser.parse(CustomerSeedRootDto.class, FILE_IMPORT_PATH);
            customerSeedRootDto.getCustomerSeedDtoList().forEach(c ->
                    this.customerRepository.saveAndFlush(this.modelMapper.map(c, Customer.class)));
        }
    }

    @Override
    public void exportOrderedCustomers() throws JAXBException {
        List<CustomerOrderedDto> customerOrderedDtos = this.customerRepository.findAllByOrderByBirthDateAscIsYoungDriverAsc()
                .stream()
                .map(c -> this.modelMapper.map(c, CustomerOrderedDto.class))
                .collect(Collectors.toList());
        CustomerOrderedRootDto customerOrderedRootDto = new CustomerOrderedRootDto();
        customerOrderedRootDto.setCustomerOrderedDtoList(customerOrderedDtos);
        this.xmlParser.exportToFile(CustomerOrderedRootDto.class, customerOrderedRootDto, FILE_EXPORT_CUSTOMERS_PATH);
    }

    @Override
    public void exportCustomersWithCars() throws JAXBException {
        List<CustomerTotalSaleDto> collect = this.customerRepository.findAllWithBoughtCars()
                .stream()
                .map(c -> {
                    CustomerTotalSaleDto customerTotalSaleDto = new CustomerTotalSaleDto();
                    customerTotalSaleDto.setFullName(c.getName());
                    customerTotalSaleDto.setBoughtCars(c.getSales().size());
                    double spentMoney = c.getSales()
                            .stream()
                            .mapToDouble(s -> s.getCar().getParts().stream().mapToDouble(p -> p.getPrice().doubleValue()).sum() * s.getDiscount())
                            .sum();

                    customerTotalSaleDto.setSpentMoney(BigDecimal.valueOf(spentMoney));
                    return customerTotalSaleDto;
                })
                .sorted(Comparator.comparing(CustomerTotalSaleDto::getBoughtCars).reversed()
                        .thenComparing(Comparator.comparing(CustomerTotalSaleDto::getSpentMoney).reversed()))
                .collect(Collectors.toList());
        CustomersTotalSalesRootDto customersTotalSalesRootDto=new CustomersTotalSalesRootDto();
       customersTotalSalesRootDto.setCustomerTotalSaleDtoList(collect);
       this.xmlParser.exportToFile(CustomersTotalSalesRootDto.class,customersTotalSalesRootDto,FILE_EXPORT_BOUGHT_CARS);
    }


}

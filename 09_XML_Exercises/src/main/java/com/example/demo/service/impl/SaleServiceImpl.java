package com.example.demo.service.impl;

import com.example.demo.data.entities.Car;
import com.example.demo.data.entities.Customer;
import com.example.demo.data.entities.Part;
import com.example.demo.data.entities.Sale;
import com.example.demo.data.repositories.CarRepository;
import com.example.demo.data.repositories.CustomerRepository;
import com.example.demo.data.repositories.SaleRepository;
import com.example.demo.service.SaleService;
import com.example.demo.service.dto.exports.CarDto;
import com.example.demo.service.dto.exports.SaleDiscountDto;
import com.example.demo.service.dto.exports.SaleDiscountRootDto;
import com.example.demo.util.XmlParser;
import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService {

    private static  final String FILE_EXPORT_PATH_SALES= "src/main/resources/xml/export/sales.xml";


    private final List<Double> discount = List.of(1.0, 0.95, 0.9, 0.85, 0.8, 0.7, 0.6, 0.5);
    private final SaleRepository saleRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;

    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;

    public SaleServiceImpl(SaleRepository saleRepository, CarRepository carRepository, CustomerRepository customerRepository, XmlParser xmlParser, ModelMapper modelMapper, XmlParser xmlParser1) {
        this.saleRepository = saleRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser1;
    }


    @Override
    public void seedSales() {
        if(this.saleRepository.count()==0) {
            for (int i = 0; i < 60; i++) {
               Sale sale =new Sale();
               sale.setCar(getRandomCar());
               sale.setCustomer(getRandomCustomer());
               sale.setDiscount(getRandomDiscount());
               this.saleRepository.saveAndFlush(sale);
            }
        }
    }

    @Override
    public void exportSales() throws JAXBException {
        List<SaleDiscountDto> saleDiscountDtos = this.saleRepository
                .findAll()
                .stream()
                .map(s -> {
                    SaleDiscountDto saleDiscountDto = this.modelMapper.map(s, SaleDiscountDto.class);
                    CarDto car = this.modelMapper.map(s.getCar(), CarDto.class);

                    saleDiscountDto.setCarDto(car);
                    saleDiscountDto.setCustomerName(s.getCustomer().getName());
                    saleDiscountDto.setPrice(s.getCar().getParts().stream().map(Part::getPrice).reduce(BigDecimal::add).get());
                    saleDiscountDto.setPriceWithDiscount(saleDiscountDto.getPrice().multiply(BigDecimal.valueOf(s.getDiscount())));
                    return saleDiscountDto;
                })
                .collect(Collectors.toList());

        SaleDiscountRootDto saleDiscountRootDto=new SaleDiscountRootDto();
        saleDiscountRootDto.setSaleDiscountDtoList(saleDiscountDtos);
        this.xmlParser.exportToFile(SaleDiscountRootDto.class, saleDiscountRootDto,FILE_EXPORT_PATH_SALES);

    }

    private double getRandomDiscount() {
        return discount.get(ThreadLocalRandom.current().nextInt(1,discount.size()));
    }

    private Customer getRandomCustomer() {
        return  this.customerRepository.findById(ThreadLocalRandom.current().nextLong(1, this.customerRepository.count()+1)).get();
    }

    private Car getRandomCar() {
        return  this.carRepository.findById(ThreadLocalRandom.current().nextLong(1, this.carRepository.count()+1)).get();
    }
}

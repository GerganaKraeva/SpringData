package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.json.ConstellationSeedDto;
import softuni.exam.models.entity.Constellation;
import softuni.exam.repository.ConstellationRepository;
import softuni.exam.service.ConstellationService;
import softuni.exam.util.ValidationUtil;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class ConstellationServiceImpl implements ConstellationService {
    private final String PATH_CONSTELLATIONS_JSON="src/main/resources/files/json/constellations.json";

    private final ConstellationRepository constellationRepository;

    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;



    public ConstellationServiceImpl(ConstellationRepository constellationRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.constellationRepository = constellationRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }


    @Override
    public boolean areImported() {
        return this.constellationRepository.count()>0;
    }

    @Override
    public String readConstellationsFromFile() throws IOException {
        return new String(Files.readString(Path.of(PATH_CONSTELLATIONS_JSON)));
    }

    @Override
    public String importConstellations() throws IOException {
       StringBuilder sb =new StringBuilder();

        ConstellationSeedDto[] constellationSeedDtos = this.gson.fromJson(new FileReader(PATH_CONSTELLATIONS_JSON), ConstellationSeedDto[].class);

        for (ConstellationSeedDto constellationSeedDto : constellationSeedDtos) {

            Optional<Constellation> optional = this.constellationRepository.findByName(constellationSeedDto.getName());

            if(!this.validationUtil.isValid(constellationSeedDto) || optional.isPresent()){
                sb.append("Invalid constellation\n");
                continue;
            }

            Constellation constellation =this.modelMapper.map(constellationSeedDto, Constellation.class);
            this.constellationRepository.saveAndFlush(constellation);
            sb.append(String.format("Successfully imported constellation %s - %s\n",constellation.getName(),constellation.getDescription()));

        }
        return sb.toString();
    }
}

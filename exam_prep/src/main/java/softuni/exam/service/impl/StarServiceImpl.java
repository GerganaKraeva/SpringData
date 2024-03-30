package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.json.ConstellationSeedDto;
import softuni.exam.models.dto.json.StarSeedDto;
import softuni.exam.models.entity.Constellation;
import softuni.exam.models.entity.Star;
import softuni.exam.models.entity.StarType;
import softuni.exam.repository.StarRepository;
import softuni.exam.service.StarService;
import softuni.exam.util.ValidationUtil;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.Optional;

@Service
public class StarServiceImpl implements StarService {
    private final String PATH_STARS_JSON="src/main/resources/files/json/stars.json";

    private final StarRepository starRepository;

    private final Gson gson;

    private final ModelMapper modelMapper;

    private final ValidationUtil validationUtil;

    public StarServiceImpl(StarRepository starRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.starRepository = starRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }


    @Override
    public boolean areImported() {
        return this.starRepository.count()>0;
    }

    @Override
    public String readStarsFileContent() throws IOException {
        return new String(Files.readString(Path.of(PATH_STARS_JSON)));
    }

    @Override
    public String importStars() throws IOException {

        StringBuilder sb =new StringBuilder();

        StarSeedDto[] starSeedDtos = this.gson.fromJson(new FileReader(PATH_STARS_JSON), StarSeedDto[].class);


        for (StarSeedDto starSeedDto : starSeedDtos) {

            Optional<Star> optional = this.starRepository.findByName(starSeedDto.getName());

            if(!this.validationUtil.isValid(starSeedDto) || optional.isPresent()){
                sb.append("Invalid star\n");
                continue;
            }

            Star star = this.modelMapper.map(starSeedDto, Star.class);
            this.starRepository.saveAndFlush(star);
            sb.append(String.format("Successfully imported star %s - %.2f light years\n",star.getName(),star.getLightYears()));

        }
        return sb.toString();
    }

    @Override
    public String exportStars() {
        return null;
    }
}

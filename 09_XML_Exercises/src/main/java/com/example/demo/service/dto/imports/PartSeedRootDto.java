package com.example.demo.service.dto.imports;

import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "parts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartSeedRootDto implements Serializable {
    @XmlElement(name = "part")
    private List<PartSeedDto> partSeedDtoList;

    public List<PartSeedDto> getPartSeedDtoList() {
        return partSeedDtoList;
    }

    public void setPartSeedDtoList(List<PartSeedDto> partSeedDtoList) {
        this.partSeedDtoList = partSeedDtoList;
    }
}

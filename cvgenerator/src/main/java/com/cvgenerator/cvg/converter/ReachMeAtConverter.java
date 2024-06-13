package com.cvgenerator.cvg.converter;

import com.cvgenerator.cvg.dto.ReachMeAtDto;
import com.cvgenerator.cvg.entity.BasicInformation;
import com.cvgenerator.cvg.entity.ReachMeAt;
import com.cvgenerator.cvg.repo.BasicInformationRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class ReachMeAtConverter extends AbstractConverter<ReachMeAtDto, ReachMeAt> {
    private final BasicInformationRepo basicInformationRepo;

    public ReachMeAtConverter(BasicInformationRepo basicInformationRepo) {
        this.basicInformationRepo = basicInformationRepo;
    }

    @Override
    public ReachMeAtDto toDto(ReachMeAt reachMeAt) {
        return ReachMeAtDto
                .builder()
                .reachMeAtId(reachMeAt.getReachMeAtId())
                .basicInformationId(reachMeAt.getBasicInformation().getId())
                .contactType(reachMeAt.getContactType())
                .details(reachMeAt.getDetails())
                .build();
    }

    @Override
    public ReachMeAt toEntity(ReachMeAtDto reachMeAtDto) {
        ReachMeAt entity = new ReachMeAt();
        entity.setReachMeAtId(reachMeAtDto.getReachMeAtId());
        entity.setContactType(reachMeAtDto.getContactType());
        entity.setDetails(reachMeAtDto.getDetails());
        Optional<BasicInformation> optionalBasicInformation = basicInformationRepo.findById(reachMeAtDto.getBasicInformationId());
        if (optionalBasicInformation.isPresent()) {
            BasicInformation basicInformation = optionalBasicInformation.get();
            entity.setBasicInformation(basicInformation);
        } else {
            log.error("BasicInformation not found");
            throw new RuntimeException("Basic information not found !!");
        }
        return entity;
    }
}

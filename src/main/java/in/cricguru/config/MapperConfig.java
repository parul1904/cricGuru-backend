package in.cricguru.config;

import in.cricguru.mapper.DreamPlayerTeamMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public DreamPlayerTeamMapper dreamPlayerTeamMapper() {
        return Mappers.getMapper(DreamPlayerTeamMapper.class);
    }
}
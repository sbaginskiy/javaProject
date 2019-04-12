package software.jevera.domain.dto;


import org.mapstruct.Mapper;
import software.jevera.domain.OnceTimeEvent;

@Mapper(componentModel = "spring")
public interface EventMapper {

    OnceTimeEvent toOnceTimeEvent(OnceEventDto onceEventDto);

}

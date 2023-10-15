package programacion3.parcial2.universidad.mapping.mappers;

import programacion3.parcial2.universidad.mapping.dto.EstudianteDto;
import programacion3.parcial2.universidad.model.Estudiante;

import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UniversidadMapper {

    UniversidadMapper INSTANCE = Mappers.getMapper(UniversidadMapper.class);

    @Named("estudianteToEstudianteDto")
    EstudianteDto estudianteToEstudianteDto(Estudiante estudiante);

    Estudiante estudianteDtoToEstudiante(EstudianteDto estudianteDto);

    @IterableMapping(qualifiedByName = "estudianteToEstudianteDto")
    List<EstudianteDto> getEstudianteDto(List<Estudiante> listaEstudiantes);
}

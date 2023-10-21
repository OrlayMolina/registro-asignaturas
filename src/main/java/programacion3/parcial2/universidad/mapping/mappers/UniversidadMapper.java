package programacion3.parcial2.universidad.mapping.mappers;

import programacion3.parcial2.universidad.mapping.dto.*;
import programacion3.parcial2.universidad.model.*;

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

    @Named("profesorToProfesorDto")
    ProfesorDto profesorToProfesorDto(Profesor profesor);

    Profesor profesorDtoToProfesor(ProfesorDto profesorDto);

    @IterableMapping(qualifiedByName = "profesorToProfesorDto")
    List<ProfesorDto> getProfesorDto(List<Profesor> listaProfesores);

    @Named("materiaToMateriaDto")
    MateriaDto materiaToMateriaDto(Materia materia);

    Materia materiaDtoToMateria(MateriaDto materiaDto);

    @IterableMapping(qualifiedByName = "materiaToMateriaDto")
    List<MateriaDto> getMateriaDto(List<Materia> listaMaterias);

    @Named("asignacionToAsignacionDto")
    AsignacionDto asignacionToAsignacionDto(Asignacion asignacion);

    Asignacion asignacionDtoToAsignacion(AsignacionDto asignacionDto);

    @IterableMapping(qualifiedByName = "asignacionToAsignacionDto")
    List<AsignacionDto> getAsignacionDto(List<Asignacion> listaAsignaciones);

    @Named("cursoToCursoDto")
    CursoDto cursoToCursoDto(Curso curso);

    Curso cursoDtoToCurso(CursoDto cursoDto);

    @IterableMapping(qualifiedByName = "cursoToCursoDto")
    List<CursoDto> getCursoDto(List<Curso> listaCursos);
}

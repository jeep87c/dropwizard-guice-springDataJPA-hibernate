package com.dropwizard.seed.modules.absence.api.resource;

import com.dropwizard.seed.modules.absence.api.dto.AbsenceCreateRequestV1;
import com.dropwizard.seed.modules.absence.api.dto.AbsenceResponseV1;
import com.dropwizard.seed.modules.absence.api.mapper.AbsenceMapper;
import com.dropwizard.seed.modules.absence.api.mapper.ReasonMapper;
import com.dropwizard.seed.modules.absence.dal.repository.AbsenceRepository;
import com.dropwizard.seed.modules.absence.dal.repository.ReasonRepository;

import com.dropwizard.seed.modules.absence.domain.Absence;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.net.URI;
import java.util.UUID;
import java.util.stream.Collectors;

@Path(AbsenceResource.PATH_ABSENCE)
@Produces(MediaType.APPLICATION_JSON)
public class AbsenceResource {

  static final String PATH_ABSENCE = "/absences";
  private final AbsenceMapper absenceMapper;
  private final AbsenceRepository absenceRepository;
  private final ReasonRepository reasonRepository;
  private final ReasonMapper reasonMapper;

  @Inject
  public AbsenceResource(AbsenceMapper absenceMapper,
                         AbsenceRepository absenceRepository,
                         ReasonRepository reasonRepository,
                         ReasonMapper reasonMapper) {

    this.absenceMapper = absenceMapper;
    this.absenceRepository = absenceRepository;
    this.reasonRepository = reasonRepository;
    this.reasonMapper = reasonMapper;
  }

  @GET
  public Response findAll() {
    return Response.ok().entity(absenceRepository.findAll()
                                                 .parallelStream()
                                                 .map(absenceMapper::map)
                                                 .collect(Collectors.toList())).build();
  }

  @POST
  @Consumes({MediaType.APPLICATION_JSON})
  public Response create(@NotNull @Valid AbsenceCreateRequestV1 absenceCreateRequestV1) {
    try {
      persist(absenceCreateRequestV1);
      AbsenceResponseV1 newAbsence = absenceMapper.map(persist(absenceCreateRequestV1));

      return Response.created(URI.create(String.format("%s/%s", PATH_ABSENCE, newAbsence.getId())))
                     .entity(newAbsence)
                     .build();
    } catch (IllegalStateException e) {
      return Response.status(Status.CONFLICT).build();
    }
  }

  private Absence persist(AbsenceCreateRequestV1 absenceCreateRequestV1) {
    if (absenceRepository.existingAbsenceBetweenDates(absenceCreateRequestV1.getStartsOn(),
                                                       absenceCreateRequestV1.getEndsOn())) {
      throw new IllegalStateException();
    }
    return absenceRepository.save(absenceMapper.map(absenceCreateRequestV1));
  }

  @GET
  @Path("/reasons")
  public Response findAllReason() {
    return Response.ok()
                   .entity(reasonRepository.findAll().stream().map(reasonMapper::map).collect(Collectors.toList()))
                   .build();
  }

  @GET
  @Path("/reasons/{id}")
  public Response findReasonById(@PathParam("id") String id) {
    UUID uuid;

    try {
      uuid = UUID.fromString(id);
    } catch (IllegalArgumentException exception) {
      return Response.status(Status.BAD_REQUEST).build();
    }

    return Response.ok().entity(reasonRepository.findById(uuid).map(reasonMapper::map)).build();
  }
}

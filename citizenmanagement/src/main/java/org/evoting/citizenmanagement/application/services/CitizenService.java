package org.evoting.citizenmanagement.application.services;

import org.evoting.citizenmanagement.application.dto.CitizenDto;
import org.evoting.citizenmanagement.domain.model.citizen.Citizen;
import reactor.core.publisher.Mono;

public interface CitizenService {

    Mono<Citizen> createCitizen(CitizenDto citizenDto);

    Mono<Boolean> login(CitizenDto citizenDto);

    Mono<Citizen> findByEmail(String email);
}
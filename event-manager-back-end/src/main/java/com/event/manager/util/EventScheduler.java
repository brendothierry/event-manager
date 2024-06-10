package com.event.manager.util;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.event.manager.model.EventModel;

@Component
public class EventScheduler {

	@Value("${url.endpoint.findAll}")
	private String urlEndpointFindAll;
	@Value("${url.endpoint.updateEvent}")
	private String urlEndpointUpdateEvent;

	@Autowired
	private RestTemplate restTemplate;

	@Scheduled(fixedRate = 86400000) // Executa a cada 24 horas (86400000 ms) -- Para executar a cada 1 minuto (60000
									// ms)
	public void checkAndUpdateEventStatus() {
		System.err.println("Buscando eventos ");
		ResponseEntity<List<EventModel>> response = restTemplate.exchange(urlEndpointFindAll, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<EventModel>>() {
				});

		if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
			List<EventModel> events = response.getBody();
			for (EventModel event : events) {
				if (event.getEndDate().isBefore(LocalDate.now()) && event.getActive()) {

					ResponseEntity<EventModel> responseUpdate = restTemplate.exchange(urlEndpointUpdateEvent,
							HttpMethod.PUT, new HttpEntity<>(event), EventModel.class);

					if (responseUpdate.getStatusCode().is2xxSuccessful()) {
						EventModel updatedEvent = responseUpdate.getBody();
						System.err.println("Evento atualizado: " + updatedEvent);
					} else {
						System.err.println("Erro ao atualizar o evento: " + responseUpdate.getStatusCode());
					}
				}
			}
		} else {
			System.err.println("Erro ao buscar eventos: " + response.getStatusCode());
		}
	}
}

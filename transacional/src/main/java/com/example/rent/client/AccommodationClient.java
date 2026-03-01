package com.example.rent.client;

import com.example.rent.dto.AccommodationDetailsDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class AccommodationClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccommodationClient.class);

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public AccommodationClient(RestTemplate restTemplate,
                               @Value("${external.accommodation.base-url:http://localhost:8080}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    public AccommodationDetailsDto getAccommodationById(String accommodationId) {
        String url = baseUrl.endsWith("/")
                ? baseUrl + "accommodations/" + accommodationId
                : baseUrl + "/accommodations/" + accommodationId;
        try {
            HttpHeaders headers = new HttpHeaders();
            String authorization = resolveAuthorizationHeader();
            if (authorization != null && !authorization.isBlank()) {
                headers.set(HttpHeaders.AUTHORIZATION, authorization);
                LOGGER.info("Calling accommodation service with authorization header. accommodationId={}, url={}", accommodationId, url);
            } else {
                LOGGER.warn("Calling accommodation service without authorization header. accommodationId={}, url={}", accommodationId, url);
            }
            HttpEntity<Void> entity = new HttpEntity<>(headers);
            ResponseEntity<AccommodationDetailsDto> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    AccommodationDetailsDto.class
            );
            LOGGER.info("Accommodation service response. accommodationId={}, status={}", accommodationId, response.getStatusCode());
            return response.getBody();
        } catch (RestClientException ex) {
            LOGGER.error("Accommodation service call failed. accommodationId={}, url={}, message={}", accommodationId, url, ex.getMessage());
            return null;
        }
    }

    private String resolveAuthorizationHeader() {
        var attributes = RequestContextHolder.getRequestAttributes();
        if (attributes instanceof ServletRequestAttributes servletAttributes) {
            return servletAttributes.getRequest().getHeader(HttpHeaders.AUTHORIZATION);
        }
        return null;
    }
}

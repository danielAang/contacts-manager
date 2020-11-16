package br.com.dan.contactsimporter.jobs.imp.person.processor;

import java.util.HashMap;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.dan.contactsimporter.dtos.viacep.ViaCepData;
import br.com.dan.contactsimporter.jobs.imp.person.exceptions.InvalidZipCodeException;
import br.com.dan.contactsimporter.models.Address;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UpdateCityAddressProcessor implements ItemProcessor<Address, Address> {

    @Override
    public Address process(Address address) throws Exception {
        String zipCode = address.getZipCode();
        if (isZipCodeValid(zipCode)) {
            ViaCepData viaCepData = getAddressInfo(zipCode);
            address.setCity(viaCepData.getLocalidade());
            address.setState(viaCepData.getUf());
            address.setDistrict(viaCepData.getBairro());
            address.setStreet(viaCepData.getLogradouro());
            address.setUpdated(Boolean.TRUE);
            return address;
        } else {
            throw new InvalidZipCodeException(String.format("Invalid zipCode %s", zipCode));
        }
    }

    private ViaCepData getAddressInfo(String zipCode) throws InvalidZipCodeException {
        RestTemplate restTemplate = new RestTemplate();
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("zipCode", zipCode);
        try {
            ResponseEntity<ViaCepData> responseEntity = restTemplate.getForEntity("https://viacep.com.br/ws/{zipCode}/json/", ViaCepData.class, parameters);
            if (HttpStatus.BAD_REQUEST.equals(responseEntity.getStatusCode())) {
                log.error("Invalid zipCode {}", zipCode);
            }
            ViaCepData viaCepData = responseEntity.getBody();
            if (viaCepData.getErro()) {
                log.warn("zipCode {} not found", zipCode);
            }
            return viaCepData;            
        } catch (RestClientException e) {
            log.error("Failed to get address information for zipCode {}", zipCode);
            throw new InvalidZipCodeException(String.format("Invalid zipCode %s", zipCode));
        }
    }

    private boolean isZipCodeValid(String zipCode) {
        if (!StringUtils.hasText(zipCode)) {
            return false;
        }
        String trimmedZipCode = StringUtils.trimAllWhitespace(zipCode);
        return trimmedZipCode.matches("[0-9]+");
    }
    
}

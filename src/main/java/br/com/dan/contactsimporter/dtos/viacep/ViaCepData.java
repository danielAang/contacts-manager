package br.com.dan.contactsimporter.dtos.viacep;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ViaCepData {
    
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private Long codIbge;
    private Long gia;
    private Integer ddd;
    private Integer siafi;
    @Builder.Default
    private Boolean erro = Boolean.FALSE;

}

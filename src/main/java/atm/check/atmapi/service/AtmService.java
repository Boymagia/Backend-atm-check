package atm.check.atmapi.service;

import atm.check.atmapi.dto.AtmDTO;
import atm.check.atmapi.model.Atm;
import atm.check.atmapi.repository.AtmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AtmService {

    private final AtmRepository atmRepository;

    @Autowired
    public AtmService(AtmRepository atmRepository) {
        this.atmRepository = atmRepository;
    }

    /**
     * Cria um novo ATM.
     * @param atmDto O DTO com os dados do ATM a ser criado.
     * @return O DTO do ATM recém-criado.
     */
    public AtmDTO createAtm(AtmDTO atmDto) {
        // Mapeia o DTO para a entidade
        Atm atm = new Atm();
        atm.setLocalizacao(atmDto.getLocalizacao());
        atm.setLatitude(atmDto.getLatitude());
        atm.setLongitude(atmDto.getLongitude());
        atm.setDinheiro(atmDto.getDinheiro());
        atm.setPapel(atmDto.getPapel());
        atm.setLevantamentoSemCartao(atmDto.getLevantamentoSemCartao());
        atm.setSistema(atmDto.getSistema());

        // Salva a entidade no banco de dados
        Atm savedAtm = atmRepository.save(atm);

        // Retorna o DTO da entidade salva
        return new AtmDTO(savedAtm);
    }

    /**
     * Retorna uma lista de todos os ATMs.
     * @return Uma lista de ATMDTOs.
     */
    public List<AtmDTO> getAllAtms() {
        return atmRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Busca um ATM pelo seu ID.
     * @param id O ID do ATM.
     * @return Um Optional contendo o ATMDTO se encontrado, ou vazio se não.
     */
    public Optional<AtmDTO> getAtmById(Integer id) {
        return atmRepository.findById(id).map(this::mapToDTO);
    }

    /**
     * Atualiza um ATM existente.
     *
     * CORREÇÃO: Adicionada a lógica de verificação de NULL (patch) para garantir
     * que a localização só seja atualizada se o valor for enviado no DTO,
     * prevenindo o erro 500 se o campo for NOT NULL na base de dados.
     *
     * @param id O ID do ATM a ser atualizado.
     * @param atmDto O DTO com os dados de atualização.
     * @return Um Optional contendo o ATMDTO atualizado, ou vazio se o ATM não for encontrado.
     */
    public Optional<AtmDTO> updateAtm(Integer id, AtmDTO atmDto) {
        return atmRepository.findById(id).map(existingAtm -> {

            // 1. Campos de localização: APENAS atualiza se não forem NULL no DTO.
            if (atmDto.getLocalizacao() != null) {
                existingAtm.setLocalizacao(atmDto.getLocalizacao());
            }
            if (atmDto.getLatitude() != null) {
                existingAtm.setLatitude(atmDto.getLatitude());
            }
            if (atmDto.getLongitude() != null) {
                existingAtm.setLongitude(atmDto.getLongitude());
            }

            // 2. Campos de status: Estes são 'int' e devem ser sempre atualizados,
            // pois mesmo que venham como 0 (se omitidos e não houver validação),
            // eles representam o novo status (cheio/vazio/ligado/desligado).
            existingAtm.setDinheiro(atmDto.getDinheiro());
            existingAtm.setPapel(atmDto.getPapel());
            existingAtm.setLevantamentoSemCartao(atmDto.getLevantamentoSemCartao());
            existingAtm.setSistema(atmDto.getSistema());

            return mapToDTO(atmRepository.save(existingAtm));
        });
    }

    /**
     * Exclui um ATM pelo seu ID.
     * @param id O ID do ATM a ser excluído.
     */
    public void deleteAtm(Integer id) {
        atmRepository.deleteById(id);
    }

    /**
     * Método auxiliar para mapear a entidade Atm para o DTO.
     * @param atm A entidade Atm.
     * @return O ATMDTO correspondente.
     */
    private AtmDTO mapToDTO(Atm atm) {
        return new AtmDTO(atm);
    }
}

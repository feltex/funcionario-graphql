package br.com.feltex.funcionario.graphql;

import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FuncionarioService {

  private final Map<String, Funcionario> funcionarios = new HashMap<>();

  public Funcionario criar(String nome, Departamento departamento) {
    var funcionario = new Funcionario(UUID.randomUUID().toString(), nome, departamento);
    funcionarios.put(funcionario.id(), funcionario);
    return funcionario;
  }

  public List<Funcionario> listar() {
    return funcionarios.values().stream().toList();
  }

  @PostConstruct
  private void startUp() {
    funcionarios.put("185cd8a6-ccf9-4e0b-88cd-a8fc640a6fd2",
        new Funcionario("185cd8a6-ccf9-4e0b-88cd-a8fc640a6fd2", "Jose da Silva", Departamento.RH));
    funcionarios.put("83b82f23-62dc-4bde-9c68-f7ea9a1846cc",
        new Funcionario("83b82f23-62dc-4bde-9c68-f7ea9a1846cc", "Maria da Silva", Departamento.FINANCEIRO));
    funcionarios.put("3e3f0a5c-9389-4c3d-8bcf-7a3e15ca8362",
        new Funcionario("3e3f0a5c-9389-4c3d-8bcf-7a3e15ca8362", "Antonio Santo", Departamento.DIRETORIA));
    funcionarios.put("d2250a56-5131-4310-b68c-8403fa3ea586",
        new Funcionario("d2250a56-5131-4310-b68c-8403fa3ea586", "Paula Silveira", Departamento.DIRETORIA));
    funcionarios.put("c39b261e-b1d3-4c15-baa8-20e7d64d706f",
        new Funcionario("c39b261e-b1d3-4c15-baa8-20e7d64d706f", "Saulo Martins", Departamento.TI));
  }

  public Optional<Funcionario> buscarPorId(String id) {
    log.info("Buscando Funcionário id={} ", id);
    return Optional.ofNullable(funcionarios.get(id));
  }

  public Funcionario deletar(String id){
    var funcionarioEncontrado = buscarPorId(id)
        .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado!"));

    funcionarios.remove(id);
    return funcionarioEncontrado;
  }

  public Funcionario atualizar(final String id, final String nome, final Departamento departamento){
    log.info("Atualizando Funcionário id={}", id);
    if(funcionarios.containsKey(id)){
      funcionarios.put(id, new Funcionario(id, nome, departamento));
      return  funcionarios.get(id);
    }
    throw  new IllegalArgumentException("Funcionário não encontrado!");
  }

}

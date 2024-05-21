package br.com.feltex.funcionario.graphql;

import java.util.List;
import java.util.Optional;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class FuncionarioController {

  private final FuncionarioService funcionarioService;

  public FuncionarioController(FuncionarioService funcionarioService) {
    this.funcionarioService = funcionarioService;
  }

  @MutationMapping
  public Funcionario criar(@Argument String nome, @Argument Departamento departamento) {
    return funcionarioService.criar(nome, departamento);
  }

  @QueryMapping
  public List<Funcionario> listar() {
    return funcionarioService.listar();
  }

  @QueryMapping
  public Optional<Funcionario> buscarPorId(@Argument final String id) {
    return funcionarioService.buscarPorId(id);
  }

  @MutationMapping
  public Funcionario atualizar(@Argument String id, @Argument String nome, @Argument Departamento departamento) {
    return funcionarioService.atualizar(id, nome, departamento);
  }

  @MutationMapping
  public Funcionario deletar(@Argument String id) {
    return funcionarioService.deletar(id);
  }


}

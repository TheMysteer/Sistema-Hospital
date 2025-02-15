/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicos;

import dao.DAOFactory;
import dao.PacienteDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Paciente;

/**
 *
 * @author senacead
 */
/*
A classe PacienteServicos representa a camada de serviços da aplicação, ela utiliza a classe PacienteDAO para realizar
operações de leitura e escrita no banco de dados.
 */
public class PacienteServicos {
    
    // Método para cadastrar um paciente com validações
    public void cadastrarPaciente(Paciente pac) throws SQLException {
        // Validação do campo obrigatório "Nome"
        if (pac.getNome() == null || pac.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }
        // Verificação de CPF duplicado
        if (cpfJaCadastrado(pac.getCpf())) {
            throw new IllegalArgumentException("CPF já cadastrado");
        }
        // Busca da Fábrica um objeto PacienteDAO
        PacienteDAO pacDAO = DAOFactory.getPacienteDAO();
        // Chamando o método de cadastro no DAO
        pacDAO.cadastrarPaciente(pac);
    }
    
    // Método auxiliar para verificar se o CPF já está cadastrado
    private boolean cpfJaCadastrado(String cpf) throws SQLException {
        PacienteDAO pacDAO = DAOFactory.getPacienteDAO();
        ArrayList<Paciente> lista = pacDAO.buscarPacientePorCpf(cpf);
        return !lista.isEmpty();
    }
    
    
    
    
    // Método para buscar um paciente por ID
    public ArrayList<Paciente> buscarPacienteFiltro(String query) throws SQLException {

        // Busca da Fábrica um obj. PacienteDAO
        PacienteDAO pacDAO = DAOFactory.getPacienteDAO();

        // Chamando método buscarPaciente para buscar o paciente pelo ID
        return pacDAO.buscarPacienteFiltro(query);
    }

    // Método para buscar todos os pacientes
    public ArrayList<Paciente> buscarPaciente() throws SQLException {

        // Busca da Fábrica um obj. PacienteDAO
        PacienteDAO pacDAO = DAOFactory.getPacienteDAO();

        // Chamando método buscarPaciente para buscar todos os pacientes
        return pacDAO.buscarPaciente();
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package servicos;

/**
 *
 * @author devmy
 */
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import modelo.Paciente;
import servicos.PacienteServicos;
import java.util.Date;
import java.sql.SQLException;

public class PacienteServicosTest {

    private PacienteServicos pacienteServicos;

    @Before
    public void setUp() {
        pacienteServicos = new PacienteServicos();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCadastroPacienteComNomeVazio() throws SQLException {
        Paciente pac = new Paciente();
        pac.setNome("");
        pac.setEndereco("Rua Teste, 123");
        pac.setDataNascimento(new Date());
        pac.setTelefone("(11) 1234-5678");
        pac.setCpf("00011122233");
        pac.setRg("RG123456");
        pac.setIdConvenio(1);
        pacienteServicos.cadastrarPaciente(pac);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCadastroPacienteComCpfDuplicado() throws SQLException {
        // Primeiro cadastro – deve ser realizado com sucesso
        Paciente pac1 = new Paciente();
        pac1.setNome("João Silva");
        pac1.setEndereco("Rua A, 123");
        pac1.setDataNascimento(new Date());
        pac1.setTelefone("(11) 1234-5678");
        pac1.setCpf("11122233344");
        pac1.setRg("RG111222");
        pac1.setIdConvenio(1);
        pacienteServicos.cadastrarPaciente(pac1);
        
        // Segundo cadastro com o mesmo CPF
        Paciente pac2 = new Paciente();
        pac2.setNome("Maria Souza");
        pac2.setEndereco("Rua B, 456");
        pac2.setDataNascimento(new Date());
        pac2.setTelefone("(11) 8765-4321");
        pac2.setCpf("11122233344"); // CPF duplicado
        pac2.setRg("RG111222");
        pac2.setIdConvenio(1);
        pacienteServicos.cadastrarPaciente(pac2);
    }
}
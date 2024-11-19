import com.example.clinicabddmaven.app.SistemaClinica;
import com.example.clinicabddmaven.dominio.Diagnostico;
import com.example.clinicabddmaven.dominio.Doctor;
import com.example.clinicabddmaven.dominio.Paciente;
import com.example.clinicabddmaven.repositorio.RepositorioPaciente;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

import static org.mockito.Mockito.*;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
//@SpringBootTest(classes = SistemaClinica.class)


public class EvolucionTextoSimpleStepDefinitions {
    private Doctor doctor;
    private String dniPaciente;
    private String informe;
    private String diagnoticoElejido;
    private SistemaClinica sistemaClinica;
    private RepositorioPaciente repositorioPaciente;
    private Paciente pacienteResultante;

    @Before
    public void setUp(){
        this.doctor = null;
        this.dniPaciente = null;
        this.diagnoticoElejido = null;
        this.pacienteResultante = null;
        this.repositorioPaciente = mock(RepositorioPaciente.class);
        sistemaClinica = new SistemaClinica(repositorioPaciente);
    }

    @Given("el medico {string} ha iniciado sesion")
    public void elMedicoHaIniciadoSesion(String nombreDoctor) {
        doctor = new Doctor(nombreDoctor);
    }

    @And("ha buscado la historia clinica del paciente {string} que posee los siguientes diagnosticos:")
    public void haBuscadoLaHistoriaClinicaDelPacienteQuePoseeLosSiguientesDiagnosticos(String dniPaciente, List<String> diagnosticos) {
        this.dniPaciente = dniPaciente;
        Paciente paciente = new Paciente(dniPaciente,"Juan Doe",diagnosticos);
        repositorioPaciente = mock(RepositorioPaciente.class);
        when(repositorioPaciente.buscaPaciente(dniPaciente)).thenReturn(Optional.of(paciente));
    }

    @When("el doctor escribe para el paciente previamente buscado un informe sobre el diagnostico {string} que dice {string}")
    public void elDoctorEscribeParaElPacientePreviamenteBuscadoUnInformeSobreElDiagnosticoQueDice(String nombreDiagnostico, String informe) {
        diagnoticoElejido = nombreDiagnostico;
        this.informe = informe;
    }

    @And("el doctor guarda la evolucion.")
    public void elDoctorGuardaLaEvolucion() {
        pacienteResultante = sistemaClinica.agregarEvolucion(doctor,dniPaciente,diagnoticoElejido,informe);
    }

    @Then("se registra la evolucion en la historia clinica del paciente con el diagnostico, la descripcion y el medico.")
    public void seRegistraLaEvolucionEnLaHistoriaClinicaDelPacienteConElDiagnosticoLaDescripcionYElMedico() {
        Diagnostico diagnostico = pacienteResultante.buscarDiagnostico(diagnoticoElejido);
        assertThat(diagnostico.tieneEvolucion(doctor,informe)).isTrue();
        verify(repositorioPaciente,times(1)).actualizarPaciente(any());
    }
}
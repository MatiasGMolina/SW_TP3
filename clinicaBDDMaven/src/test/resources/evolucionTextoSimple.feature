Feature: Agregar una nueva evolución con diagnóstico previo.

  Background: El medico visualiza una historia clinica del paciente.
    Given el medico "Dr. Joaquin Sarmiento" ha iniciado sesion
    And ha buscado la historia clinica del paciente "123456" que posee los siguientes diagnosticos:
      |Angina   |
      |Gastritis|
      |Dengue    |
      |Covid     |

  Scenario: 1- El medico agrega una evolucion con texto libre.
    When el doctor escribe para el paciente previamente buscado un informe sobre el diagnostico "Angina" que dice "El paciente presenta los sintomas de una angina viral"
    And el doctor guarda la evolucion.
    Then se registra la evolucion en la historia clinica del paciente con el diagnostico, la descripcion y el medico.
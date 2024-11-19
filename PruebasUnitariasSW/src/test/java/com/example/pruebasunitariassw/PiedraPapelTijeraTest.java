package com.example.pruebasunitariassw;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PiedraPapelTijeraTest {

    PiedraPapelTijera juego = new PiedraPapelTijera();
    @Test
    public void testEmpates() {

        assertEquals("Empate", juego.jugar(PiedraPapelTijera.Jugada.PIEDRA, PiedraPapelTijera.Jugada.PIEDRA));
        assertEquals("Empate", juego.jugar(PiedraPapelTijera.Jugada.PAPEL, PiedraPapelTijera.Jugada.PAPEL));
        assertEquals("Empate", juego.jugar(PiedraPapelTijera.Jugada.TIJERA, PiedraPapelTijera.Jugada.TIJERA));
    }
    @Test
    void testJugador1Gana() {
        assertEquals("Jugador 1", juego.jugar(PiedraPapelTijera.Jugada.PIEDRA, PiedraPapelTijera.Jugada.TIJERA));
        assertEquals("Jugador 1", juego.jugar(PiedraPapelTijera.Jugada.PAPEL, PiedraPapelTijera.Jugada.PIEDRA));
        assertEquals("Jugador 1", juego.jugar(PiedraPapelTijera.Jugada.TIJERA, PiedraPapelTijera.Jugada.PAPEL));
    }
    @Test
    void testJugador2Gana() {
        assertEquals("Jugador 2", juego.jugar(PiedraPapelTijera.Jugada.PIEDRA, PiedraPapelTijera.Jugada.PAPEL));
        assertEquals("Jugador 2", juego.jugar(PiedraPapelTijera.Jugada.PAPEL, PiedraPapelTijera.Jugada.TIJERA));
        assertEquals("Jugador 2", juego.jugar(PiedraPapelTijera.Jugada.TIJERA, PiedraPapelTijera.Jugada.PIEDRA));
    }

    @Test
        void testAmbosJugadoresNull() {
            Exception exception = assertThrows(NullPointerException.class, ()->{juego.jugar(null, null);});
            assertNotNull(exception.getMessage());
        }
}
package com.gestionplus.app.modelo
data class Habitacion(
    var numeroHabitacion: Int,
    var numeroTotalCamas: Int,
    val tipoHabitacion: TipoHabitacion,
    val huespedes: MutableList<Huesped> = mutableListOf()
) {
    //method for validate if a guest is hosted
    fun validateGuestAsset(gues: Huesped): Boolean {
        return huespedes.contains(gues)
    }

    fun addGuest(guest: Huesped): Boolean {
        if (totalBeds() >= 1) {
            huespedes.add(guest)
            return true;
        } else {
            return false;
        }

    }

    //method for get total beds
    fun totalBeds(): Int = numeroTotalCamas - huespedes.size

    fun status(): String {
        var estado = "";
        if (tipoHabitacion == TipoHabitacion.PRIVADA) {
            estado = if (numeroTotalCamas == totalBeds()) "Disponible" else "Ocupada"
        } else {
            estado = if (totalBeds() > 0) "Disponible" else "Ocupada"
        }
        val datos =
            if (estado.equals("Disponible")) "tiene ${totalBeds()} camas disponibles y es $tipoHabitacion" else ""
        return "La habitacion $numeroHabitacion esta $estado $datos"
    }

    fun statusHuesped(): String {
        if (huespedes.isEmpty()){
            return "";
        }
        return huespedes.joinToString("\n") { "the guest with id ${it.id} is hosted in ${numeroHabitacion}\n" }
    }

}

package com.gestionplus.app.modelo

class Hotel {
    private val habitaciones = mutableListOf<Habitacion>()

    private val huespedes = mutableListOf<Huesped>()

    //method to create rooms
    fun addRoom(beds: Int, type: TipoHabitacion) {
        val numberRoom = habitaciones.size + 1;
        habitaciones.add(Habitacion(numberRoom, beds, type))
    }

    //method to create Guest
    fun createGuest(cedula: String, name: String, lastName: String): Huesped? {
        huespedes.find { it.id == cedula }?.let {
            if (!validateInHotel(it)) {
                return it
            }else{
                print("this guest is hosted in hotel")
                return null
            }
        }
        val newHuesped: Huesped? = Huesped.createGuest(cedula, name, lastName)
        if (newHuesped != null) {
            huespedes.add(newHuesped)
            return newHuesped
        }
        return null
    }

    //method for validate if a guest is hosted in hotel
    fun validateInHotel(guest: Huesped): Boolean {
        for (habitacion in habitaciones) {
            val isHosted = habitacion.validateGuestAsset(guest)
            if (isHosted) {
                return isHosted
            }
        }
        return false
    }

    //method for get the rooms with n beds or more
    fun getRoomsWithNBeds(beds: Int, typeRoom: TipoHabitacion): List<Habitacion> {
        return when (typeRoom) {
            TipoHabitacion.COMPARTIDA -> {
                habitaciones.filter {
                    it.tipoHabitacion == typeRoom &&
                            (it.totalBeds() >= beds)
                }
            }

            TipoHabitacion.PRIVADA -> {
                habitaciones.filter {
                    it.tipoHabitacion == typeRoom &&
                            (it.huespedes.size == 0) &&
                            it.totalBeds() >= beds
                }
            }

        }


    }

    //method for hosted a guest in room
    fun hostedGuest(guest: Huesped, room: Int): Boolean {
        return habitaciones.find { it.numeroHabitacion == room }?.let { it.addGuest(guest) } ?: false
    }

    fun getAllRooms(): List<Habitacion> {
        return habitaciones.toList() // devuelve una copia inmutable
    }

    fun getAllStatusGuests(): String {
        val data = habitaciones.joinToString("\n") { it.statusHuesped() }
        if (data.trim().isEmpty()) {
            return "has no guest register"
        }
        return data
    }

    fun getAllStatusRooms(): String {
        val data = habitaciones.joinToString("\n") { it.status() }
        if (data.trim().isEmpty()) {
            return "has no beds register"
        }
        return data
    }
}
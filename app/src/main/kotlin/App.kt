package com.gestionplus.app
import com.gestionplus.app.modelo.Hotel
import com.gestionplus.app.modelo.Huesped
import com.gestionplus.app.modelo.TipoHabitacion

fun main() {
    val hotel = Hotel()
    hotel.addRoom(5, TipoHabitacion.COMPARTIDA)
    hotel.addRoom(3, TipoHabitacion.PRIVADA)
    hotel.addRoom(8, TipoHabitacion.COMPARTIDA)
    hotel.addRoom(2, TipoHabitacion.PRIVADA)
    while (true) {
        val menssageOfWelcome =
            "hello, what do yo want to do\n1.-Register guest \n2.-administration panel\n3.-Exit\nEnter the number of option"
        println(menssageOfWelcome)
        val optionNumber = readln().toIntOrNull()
        if (optionNumber != null) {
            when (optionNumber) {
                1 -> {
                    val newHuesped = createGuest(false, hotel)
                    if (newHuesped != null) {
                        val messageOfAskAboutYourCompanions = "how many companions do you have?"
                        println(messageOfAskAboutYourCompanions)
                        val numberOfCompanions = readln().toIntOrNull()
                        if (numberOfCompanions != null) {
                            val typeRoom = optionsTypeRoom()
                            hotel.getRoomsWithNBeds(
                                numberOfCompanions + 1,
                                typeRoom
                            ).let {
                                println("we have the following rooms")
                                it.forEach { println("Room ${it.numeroHabitacion}") }
                            }
                            println("which room do you want?")
                            readln().toIntOrNull()?.let {
                                hotel.hostedGuest(newHuesped, it)
                                addCompanions(it, numberOfCompanions, hotel)
                            }
                            for (habitacion in hotel.getAllRooms()) {
                                println(habitacion)
                            }
                        } else {
                            println("Please enter a number but nothing else")

                        }
                    }
                }

                2 -> {
                    if (login()){
                        optionsAdmin(hotel)
                    }
                }
                3 -> {
                    println("bye")
                    break
                }
            }
        } else {
            println("Please enter a number but nothing else")
        }
    }
}


fun createGuest(companion: Boolean, hotel: Hotel): Huesped {
    while (true) {
        println("enter the id of the ${if (companion) "companion" else "guest"}")
        val id = readln()
        println("enter the name of the ${if (companion) "companion" else "guest"}")
        val name = readln()
        println("enter the lastname of the ${if (companion) "companion" else "guest"}")
        val lastname = readln()
        hotel.createGuest(id, name, lastname)?.let {
            return it
        }
    }
}

fun addCompanions(numberRoom: Int, numberOfCompanions: Int, hotel: Hotel) {
    for (i in 1..numberOfCompanions) {
        val companion = createGuest(true, hotel)
        hotel.hostedGuest(companion, numberRoom)
    }
}

fun optionsTypeRoom(): TipoHabitacion {
    while (true) {
        println("Enter what type room do you want?\n1.-Private\n2.-Shared")
        val numberOfOptionTypeRoom = readln().toIntOrNull()
        if (numberOfOptionTypeRoom != null) {
            when (numberOfOptionTypeRoom) {
                1 -> return TipoHabitacion.PRIVADA
                2 -> return TipoHabitacion.COMPARTIDA
            }
            println("Enter a number of option valid")
        } else {
            println("Please enter a number but nothing else")
        }
    }
}

fun numberOfBeds(): Int {
    while (true) {
        print("enter the number of beds for the room min 1")
        val number = readln().toIntOrNull()
        if ((number != null) && (number >= 1)) {
            return number
        } else {
            println("Please enter a number but nothing else")
        }
    }
}

fun optionsAdmin(hotel: Hotel) {
    while (true) {

        print("what do yo want to do\n1.-Register a room\n2.-report of rooms with status\n3.-history of guest\n4.-Go back\n")
        readln().toIntOrNull()?.let {
            if (it < 5 && it > 1) {
                when (it) {
                    1 -> {
                        var numberOfBeds = numberOfBeds()
                        val typeRoom = optionsTypeRoom()
                        hotel.addRoom(numberOfBeds, typeRoom)
                    }

                    2 -> {
                        println(hotel.getAllStatusRooms())
                    }

                    3 -> {
                        println(hotel.getAllStatusGuests())
                    }

                    4 -> {
                        break
                    }
                }
            } else {
                println("Enter a number of option valid")
            }
        }
    }
}

fun login(): Boolean {
    for (i in 1..3) {
        println("enter the password")
        val password = readln()
        if (password == "admin123") {
            return true
        }
    }
    println("access denied")
    return false
}
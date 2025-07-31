package com.gestionplus.app.modelo

data class Huesped(val id: String, val name: String, val lastName: String) {
    companion object {

        //method for create the guest, this return a guest or null according if your id is valid
        fun createGuest(id: String, name: String, lastName: String): Huesped? {
            if (idIsValid(id)) {
                return Huesped(id, name, lastName)
            }
            println("Please enter a guest with id valid")
            return null;
        }

        //method for validated id
        fun idIsValid(id: String): Boolean {
            //This sum is the result obtained from the multiplication of the algorithm to validate the ID (2;1)
            var sum = 0
            var opp = true;
            for (i in id.substring(0, id.length - 1)) {
                val number = i.digitToIntOrNull()
                if (number != null) {
                    if (opp) {
                        var valMultipli = (number * 2)
                        //If the result of the multiplication is two digits, they are added together; otherwise the number is added directly
                        if (valMultipli.toString().length > 1) {
                            valMultipli =
                                valMultipli.toString()[0].digitToInt() + valMultipli.toString()[1].digitToInt()
                        }
                        sum += valMultipli
                    } else sum += number
                    opp = !opp
                } else {
                    return false;
                }
            }
            val numberValid=10-(sum%10);
            return numberValid==id[id.length-1].digitToIntOrNull()
        }
    }
}

package ar.edu.unahur.obj2.vendedores

class ClienteInseguro {
    fun puedeSerAtendidoPor(vendedor: Vendedor):Boolean{
        return vendedor.esVersatil() && vendedor.esFirme()
    }
}
class ClienteDetallista{
    fun puedeSerAtendidoPor(vendedor: Vendedor):Boolean{
        return vendedor.certificacionesDeProducto()>=3
    }
}
class ClienteHumanista{
    fun puedeSerAtendidoPor(vendedor: Vendedor):Boolean{
        return vendedor.esPersonaFisica()
    }
}
package ar.edu.unahur.obj2.vendedores

class CentroDeDistribucion(val ciudadEnLaQueEsta: Ciudad){
    val vendedores= mutableListOf<Vendedor>()
    fun agregarVendedores(vendedor: Vendedor) {
        check(vendedores.contains(vendedor)) { "ya esta este vendedor" }
        vendedores.add(vendedor)
    }

    fun vendedorEstrella(): Vendedor? {
        return vendedores.maxBy { v -> v.puntajeCertificaciones() }
    }

    fun puedeCubrir(ciudad: Ciudad): Boolean {

        return vendedores.any({ v -> v.puedeTrabajarEn(ciudad) })
    }

    fun vendedoresGenericos(): List<Vendedor> {
        return vendedores.filter { v -> v.tieneAlMenosUnaCertificacionQueNoEsSobreProductos() }
    }

    fun esRobusto(): Boolean {
        return vendedores.count({ v -> v.esFirme() }) >= 3
    }

    fun repartirCertificacion(certificacionAOtorgar: Certificacion) {
        vendedores.forEach({ v -> v.agregarCertificacion(certificacionAOtorgar) })
    }
}


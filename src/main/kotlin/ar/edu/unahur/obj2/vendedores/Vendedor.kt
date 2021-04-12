package ar.edu.unahur.obj2.vendedores



abstract class Vendedor {
  // Acá es obligatorio poner el tipo de la lista, porque como está vacía no lo puede inferir.
  // Además, a una MutableList se le pueden agregar elementos
  val certificaciones = mutableListOf<Certificacion>()
  // Definimos el método abstracto.
  // Como no vamos a implementarlo acá, es necesario explicitar qué devuelve.
  abstract fun puedeTrabajarEn(ciudad: Ciudad): Boolean
  abstract fun esInfluyente():Boolean

  // En las funciones declaradas con = no es necesario explicitar el tipo
  fun esVersatil() =
    certificaciones.size >= 3
      && this.certificacionesDeProducto() >= 1
      && this.otrasCertificaciones() >= 1

  // Si el tipo no está declarado y la función no devuelve nada, se asume Unit (es decir, vacío)
  fun agregarCertificacion(certificacion: Certificacion) {
    certificaciones.add(certificacion)
  }

  fun esFirme() = this.puntajeCertificaciones() >= 30

  fun certificacionesDeProducto() = certificaciones.count { it.esSobreProductos }
  fun otrasCertificaciones() = certificaciones.count { !it.esSobreProductos }

  fun puntajeCertificaciones() = certificaciones.sumBy { c -> c.puntos }
  fun tieneAlMenosUnaCertificacionQueNoEsSobreProductos()=
    certificaciones.any({c-> c.esSobreProductos})
  fun tieneAfinidad(centro:CentroDeDistribucion): Boolean {
    return this.puedeTrabajarEn(centro.ciudadEnLaQueEsta)
  }
  fun esCandidato(centro:CentroDeDistribucion):Boolean{
    return this.esVersatil() && this.tieneAfinidad(centro)
  }
  abstract fun esPersonaFisica():Boolean
}


// En los parámetros, es obligatorio poner el tipo
class VendedorFijo(val ciudadEnLaQueVive: Ciudad) : Vendedor() {
  override fun puedeTrabajarEn(ciudad: Ciudad): Boolean {
    return ciudad == ciudadEnLaQueVive
  }

  override fun esInfluyente(): Boolean {
    return false
  }
  override fun esPersonaFisica():Boolean {
    return true
  }

}

// A este tipo de List no se le pueden agregar elementos una vez definida
class Viajante(val provinciasHabilitadas: List<Provincia>) : Vendedor() {
  override fun puedeTrabajarEn(ciudad: Ciudad): Boolean {
    return provinciasHabilitadas.contains(ciudad.provincia)
  }

  override fun esInfluyente(): Boolean {
    return provinciasHabilitadas.sumBy{provincia ->provincia.poblacion}>=10000000
  }
  override fun esPersonaFisica():Boolean{
    return true
  }
}

class ComercioCorresponsal(val ciudadesConSucursales: List<Ciudad>) : Vendedor() {
  override fun puedeTrabajarEn(ciudad: Ciudad): Boolean {
    return ciudadesConSucursales.contains(ciudad)
  }

  override fun esInfluyente(): Boolean {
    return ciudadesConSucursales.size>=5 || ciudadesConSucursales.map{ciudad->ciudad.provincia}.toSet().size>3
  }
  override fun esPersonaFisica()=false
}



package ar.edu.unahur.obj2.vendedores

import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue

class VendedorTest: DescribeSpec({
    val misiones=Provincia(poblacion = 1300000)
    val sanIgnacio =Ciudad(provincia = misiones)
    describe ("Vendedor fijo") {
    val obera =Ciudad(provincia = misiones)
    val vendedorFijo =VendedorFijo(ciudadEnLaQueVive = obera)

    it ("puedeTrabajarEn su ciudad de origen") {
    vendedorFijo.puedeTrabajarEn(obera).shouldBeTrue()
}

    it("puedeTrabajarEn otra ciudad") {
    vendedorFijo.puedeTrabajarEn(sanIgnacio).shouldBeFalse()
}
        it("esInfluyente"){
            vendedorFijo.esInfluyente().shouldBeFalse()
        }
}
    describe ("Viajante") {
    val cordoba=Provincia(poblacion = 2000000)
    val villaDolores =Ciudad(provincia = cordoba)
    val viajante =Viajante(listOf(misiones))


    it("puedeTrabajarEn una ciudad que pertenece a una provincia habilitada") {
    viajante.puedeTrabajarEn(sanIgnacio).shouldBeTrue()
}

    it("una ciudad que no pertenece a una provincia habilitada") {
    viajante.puedeTrabajarEn(villaDolores).shouldBeFalse()
}
        it("esInfluyente"){
            viajante.esInfluyente().shouldBeFalse()
        }
}
    describe("comercio corresponsal"){

        val laRioja=Provincia(poblacion = 150000)
        val lala =Ciudad(provincia = laRioja)
        val lala1 =Ciudad(provincia = laRioja)
        val lala2 =Ciudad(provincia = laRioja)
        val lala3 =Ciudad(provincia = laRioja)
        val lala4 =Ciudad(provincia = laRioja)
        val comercioCorresponsal =ComercioCorresponsal(listOf(lala,lala1,lala2,lala3,lala4))
        it("esInfluyente"){
            comercioCorresponsal.esInfluyente().shouldBeTrue()
        }
    }
    })
class CentroDistribucionTest: DescribeSpec({
    val buenosaires=Provincia(poblacion = 500000000)
    val bsas=Ciudad(provincia = buenosaires)
    val centro = CentroDeDistribucion(ciudadEnLaQueEsta = bsas)
    describe("centroDistribuNoPuedeAgregar") {
        val jorge = VendedorFijo(ciudadEnLaQueVive = bsas)
        val pepe=VendedorFijo(ciudadEnLaQueVive = bsas)
        centro.agregarVendedores(jorge)
        it("nopermiteagregar") {
            shouldThrowAny {
                centro.agregarVendedores(jorge)
            }
        }
    }

})


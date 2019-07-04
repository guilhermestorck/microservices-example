package com.ranefare.plancatalogservice.db.assemblers

interface EntityAssembler<E, D> {

    fun assemble(entity: E): D
}

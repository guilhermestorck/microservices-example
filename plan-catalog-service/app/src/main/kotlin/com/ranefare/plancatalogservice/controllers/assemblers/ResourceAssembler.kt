package com.ranefare.plancatalogservice.controllers.assemblers

interface ResourceAssembler<in R, out D> {

    fun assemble(resource: R): D
}